package pl.com.britenet

object Main {

  def main(args: Array[String]): Unit = {

    val aNode = new Node(1, "A", List())

    val aaNode = new Node(2, "AA", List())
    aNode.addChild(aaNode)
    aaNode.addChild(new Node(3, "AAA", List()))
    aNode.print()
  }
}
