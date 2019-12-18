package pl.hapak

case class NodeDetails(node: Node, level: Int)

case class Node(id: Int, name: String, nodes: List[Node] = List()) {

  def print(): Unit = {
    println(id, name)
    nodes.foreach(f => f.print())
  }
}