package pl.com.britenet

class Node(val id: Int, val name: String, var nodes: List[Node]) {

  def addChild(node: Node): Unit = {
    nodes = nodes ++ List(node)
  }

  def print(): Unit = {
    println(id, name)
    nodes.foreach(f => f.print())
  }
}
