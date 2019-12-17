package pl.hapak

import java.nio.file.Path


object Main {

  def main(args: Array[String]): Unit = {

    val reader = new XlsxReader
    val cells = reader.readCellDetails(Path.of(args.head))

    val mapper = new NodeMapper

    val nodes = mapper.mapNodes(cells)

    nodes.foreach(node => node.print()) // todo print as JSON
  }
}
