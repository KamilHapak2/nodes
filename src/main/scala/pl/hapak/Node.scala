package pl.hapak

class Node(val id: Int, val name: String, var nodes: List[Node] = List()) {

  def addChild(node: Node): Unit = {
    nodes = nodes ++ List(node)
  }

  def print():Unit = {
    println(id, name)
    nodes.foreach(f => f.print());
  }
}