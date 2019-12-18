package pl.hapak

class NodeMapper {

  def mapNodes(nodes: List[NodeDetails]): List[Node] = {

    val nodesFromRootLevel = findNodesFromLevel0(nodes)
    assignChildren(nodesFromRootLevel, nodes, 0)
  }

  private def findNodesFromLevel0(nodes: List[NodeDetails]): List[NodeDetails] = nodes.filter(node => node.level == 0)

  private def assignChildren(parents: List[NodeDetails], allNodes: List[NodeDetails], lv: Int): List[Node] = {
    parents
      .map(parent => Node(parent.node.id, parent.node.name,
        assignChildren(findChildren(parent, allNodes), allNodes, lv + 1)))
  }

  private def findChildren(parent: NodeDetails, nodes: List[NodeDetails]): List[NodeDetails] = {
    val descendants = findNodeDescendants(parent, nodes)
    descendants.filter(node => node.level == parent.level + 1)
  }

  private def findNodeDescendants(parent: NodeDetails, allNodes: List[NodeDetails]): List[NodeDetails] = {

    val maybeNextParent = allNodes.find(node => isBelow(parent, node) && isAtSameLevel(parent, node))

    if (maybeNextParent.isEmpty) {
      return allNodes.filter(node => isBelow(parent, node))
    }

    allNodes.filter(node => isAtHigherLevel(parent, node)
      && isBelow(node, maybeNextParent.get)
      && isBelow(parent, node))
  }

  private def isAtSameLevel(parent: NodeDetails, node: NodeDetails) = node.level == parent.level

  private def isBelow(thatNode: NodeDetails, thisNode: NodeDetails) = thisNode.node.id > thatNode.node.id

  private def isAtHigherLevel(thatNode: NodeDetails, thisNode: NodeDetails) = thisNode.level > thatNode.level
}
