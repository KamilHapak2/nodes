package pl.hapak

import org.scalatest.{FlatSpec, Matchers}

class NodesSpec extends FlatSpec with Matchers {


  "A Node" should "be able to add nodes as child nodes" in {
    val nodeA = new Node(1, "A")
    val nodeAA = new Node(2, "AA")
    nodeA.addChild(nodeAA)

    nodeA.nodes.size shouldBe 1
  }

}
