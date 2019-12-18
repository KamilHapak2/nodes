package pl.hapak

import org.scalatest.{FlatSpec, Matchers}

// todo parametrized test
class NodeMapperTest extends FlatSpec with Matchers {

  private val nodeMapper: NodeMapper = new NodeMapper

  "NodeMapper" should "find children for all nodes" in {

    val cellDetails = List(
      NodeDetails(Node(1, "A"), 0),
      NodeDetails(Node(2, "AA"), 1),
      NodeDetails(Node(3, "AA1"), 2),
      NodeDetails(Node(4, "AA2"), 2),
      NodeDetails(Node(5, "AB"), 1),
      NodeDetails(Node(6, "B"), 0),
      NodeDetails(Node(7, "C"), 0),
      NodeDetails(Node(8, "CA"), 1),
      NodeDetails(Node(9, "CA1"), 2),
      NodeDetails(Node(10, "CA2"), 2),
      NodeDetails(Node(11, "D"), 0),
      NodeDetails(Node(12, "DA"), 1)
    )

    val mappedNodes = nodeMapper.mapNodes(cellDetails)

    mappedNodes should have size 4
    mappedNodes.head.nodes should have size 2
    mappedNodes.head.nodes.head.nodes should have size 2
    mappedNodes.head.nodes(1).nodes should have size 0
    mappedNodes(1).nodes should have size 0
    mappedNodes(2).nodes should have size 1
    mappedNodes(2).nodes.head.nodes should have size 2
    mappedNodes(3).nodes should have size 1
  }

  "NodeMapper" should "find children for all nodes 2" in {

    val cellDetails = List(
      NodeDetails(Node(1, "A"), 0),
      NodeDetails(Node(2, "AA"), 1),
      NodeDetails(Node(3, "AB"), 1),
      NodeDetails(Node(4, "AB1"), 2),
      NodeDetails(Node(5, "AB2"), 2),
      NodeDetails(Node(6, "AC"), 1),
      NodeDetails(Node(7, "B"), 0),
      NodeDetails(Node(8, "BA"), 1),
      NodeDetails(Node(9, "BB"), 1),
      NodeDetails(Node(10, "BC"), 1),
      NodeDetails(Node(11, "C"), 0),
      NodeDetails(Node(12, "CA"), 1),
      NodeDetails(Node(13, "CA1"), 2),
      NodeDetails(Node(14, "CA2"), 2),
      NodeDetails(Node(15, "CB"), 1),
      NodeDetails(Node(16, "D"), 0),
      NodeDetails(Node(17, "DA"), 1),
    )

    val mappedNodes = nodeMapper.mapNodes(cellDetails)

    mappedNodes should have size 4
    mappedNodes.head.nodes should have size 3
    mappedNodes.head.nodes.head.nodes should have size 0
    mappedNodes.head.nodes(1).nodes should have size 2
    mappedNodes.head.nodes(2).nodes should have size 0
    mappedNodes(1).nodes should have size 3
    mappedNodes(2).nodes should have size 2
    mappedNodes(2).nodes.head.nodes should have size 2
    mappedNodes(2).nodes(1).nodes should have size 0
    mappedNodes(3).nodes should have size 1
    mappedNodes(3).nodes.head.nodes should have size 0
  }

}
