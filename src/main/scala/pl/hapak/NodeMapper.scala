package pl.hapak

class NodeMapper {

  def mapNodes(nodes: List[NodeDetails]): List[Node] = {

    val nodesFromRootLevel = findNodesFromLevel(nodes, 0)
    assignChildren(nodesFromRootLevel, nodes, 0)
  }

  // todo zamienic na private jak już będzie można usunąć testy
  def findNodesFromLevel(nodes: List[NodeDetails], level: Int): List[NodeDetails] = nodes.filter(node => node.level == level)

  def findNodeDescendants(parent: NodeDetails, allNodes: List[NodeDetails]): List[NodeDetails] = {

    val maybeNextParent = allNodes.find(node => node.node.id > parent.node.id && node.level == parent.level)
    if (maybeNextParent.isEmpty) {
      return allNodes.filter(node => node.node.id > parent.node.id)
    }
    val idOfNextNodeAtSameLevel = maybeNextParent.get.node.id
    allNodes.filter(node => node.level > parent.level && node.node.id < idOfNextNodeAtSameLevel && node.node.id > parent.node.id)
  }

  def findChildren(parent: NodeDetails, nodes: List[NodeDetails]): List[NodeDetails] = {
    val descendants = findNodeDescendants(parent, nodes)
    descendants.filter(node => node.level == parent.level + 1)
  }

  def assignChildren(parents: List[NodeDetails], allNodes: List[NodeDetails], lv: Int): List[Node] = {
    parents
      .map(parent => Node(parent.node.id, parent.node.name,
        assignChildren(findChildren(parent, allNodes), allNodes, lv + 1)))
  }

}
