package pl.hapak

import java.nio.file.Path

import org.scalatest.{FlatSpec, Matchers}

class NodesITest extends FlatSpec with Matchers {

  private val xlsxReader: XlsxReader = new XlsxReader
  private val nodeMapper: NodeMapper = new NodeMapper

  "reader and mapper" should "read cell details and map nodes children" in {

    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)

    val cellDetails = xlsxReader.readCellDetails(path)
    val mappedNodes = nodeMapper.mapNodes(cellDetails)

    mappedNodes.size shouldBe 4
    mappedNodes.head.nodes.size shouldBe 2
    mappedNodes.head.nodes.head.nodes.size shouldBe 2
    mappedNodes.head.nodes(1).nodes.size shouldBe 0
    mappedNodes(1).nodes.size shouldBe 0
    mappedNodes(2).nodes.size shouldBe 1
    mappedNodes(2).nodes.head.nodes.size shouldBe 2
    mappedNodes(3).nodes.size shouldBe 1
  }
}
