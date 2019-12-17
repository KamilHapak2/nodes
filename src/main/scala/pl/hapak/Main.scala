package pl.hapak

object Main {


  def main(args: Array[String]): Unit = {

    val aNode = new Node(1, "A")
    val aaNode = new Node(2, "AA")
    val aaaNode = new Node(3, "AAA")

    aNode.addChild(aaNode)
    aaNode.addChild(aaaNode)

    aNode.print()
  }

}
