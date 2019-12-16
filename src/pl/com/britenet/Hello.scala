package pl.com.britenet

object Hello {

  def main(args: Array[String]): Unit = {
    println("Hello world")

    val parent = new Node(1, "A", List())

    parent.addChild(new Node(2, "AA", List()))

    parent.print()
  }
}
