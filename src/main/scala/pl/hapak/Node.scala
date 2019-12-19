package pl.hapak

case class NodeDetails(node: Node, level: Int)

case class Node(id: Int, text: String, children: List[Node] = List()) {

  def print(): Unit = {
    println(id, text)
    children.foreach(f => f.print())
  }
}