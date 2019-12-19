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

    mappedNodes should have size 4
    mappedNodes.head.children should have size 2
    mappedNodes.head.children.head.children should have size 2
    mappedNodes.head.children(1).children should have size 0
    mappedNodes(1).children should have size 0
    mappedNodes(2).children should have size 1
    mappedNodes(2).children.head.children should have size 2
    mappedNodes(3).children should have size 1
  }
}
