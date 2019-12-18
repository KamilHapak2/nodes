package pl.hapak

import java.nio.file.Path

import net.liftweb.json.{DefaultFormats, Serialization}

object Main {

  def main(args: Array[String]): Unit = {

    if(args.isEmpty) {
      throw new RuntimeException("Path to xlsx file required as 1st param!")
    }

    val reader = new XlsxReader
    val cells = reader.readCellDetails(Path.of(args.head))
    val mapper = new NodeMapper
    val nodes = mapper.mapNodes(cells)

    implicit val formats: DefaultFormats.type = DefaultFormats
    println(Serialization.writePretty(nodes))
  }
}
