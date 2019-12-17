package pl.hapak

import java.nio.file.Path

import org.scalatest.{FlatSpec, Matchers}

// todo test params
class NodeMapperTest extends FlatSpec with Matchers {

  private val xlsxReader: XlsxReader = new XlsxReader
  private val nodeMapper: NodeMapper = new NodeMapper

  "NodeMapper" should " find nodes from level 0" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val nodesFromLevel0 = nodeMapper.findNodesFromLevel(cellDetails, 0)

    nodesFromLevel0.size shouldBe 4
  }

  "NodeMapper" should " find nodes from level 1" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val nodesFromLevel0 = nodeMapper.findNodesFromLevel(cellDetails, 1)

    nodesFromLevel0.size shouldBe 4
  }

  "NodeMapper" should " find nodes from level 2" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val nodesFromLevel0 = nodeMapper.findNodesFromLevel(cellDetails, 2)

    nodesFromLevel0.size shouldBe 4
  }

  "NodeMapper" should "find node descendants" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val descendants = nodeMapper.findNodeDescendants(cellDetails.head, cellDetails)

    descendants.size shouldBe 4
  }

  "NodeMapper" should "find node descendants2" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val descendants = nodeMapper.findNodeDescendants(cellDetails(10), cellDetails)

    descendants.size shouldBe 1
  }

  "NodeMapper" should "find children" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val children = nodeMapper.findChildren(cellDetails(5), cellDetails)

    children.size shouldBe 0
  }

  "NodeMapper" should "find children for all nodes" in {

    // todo zamienić te dwie linie na listę NodeDetails
    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)
    val cellDetails = xlsxReader.getCellDetails(path)

    val mappedNodes = nodeMapper.mapNodes(cellDetails)

    mappedNodes.size shouldBe 4
    mappedNodes.head.nodes.size shouldBe 2
    mappedNodes.head.nodes.head.nodes.size shouldBe 2
    mappedNodes.head.nodes(1).nodes.size shouldBe 0
    mappedNodes(1).nodes.size shouldBe 0
    mappedNodes(2).nodes.size shouldBe 1
    mappedNodes(2).nodes.head.nodes.size shouldBe 2
    mappedNodes(3).nodes.size shouldBe 1
  }
}
