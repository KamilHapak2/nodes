package pl.hapak

import java.nio.file.Path

import org.apache.poi.ss.usermodel.{Cell, CellType, Row, WorkbookFactory}

import scala.jdk.CollectionConverters._

case class NodeDetails(node: Node, level: Int)

class XlsxReader {

  def readCellDetails(path: Path): List[NodeDetails] = {

    val rows = readRows(path)
    val nodeRows = rows.filter(row => isNotAHeader(row))

    val cellsAndLevels: List[(Cell, Int)] = getCellsAndTheirLevels(nodeRows)

    cellsAndLevels.map(c => NodeDetails(Node(c._1.getRow.getRowNum, c._1.getStringCellValue), c._2))
  }

  private def getCellsAndTheirLevels(nodeRows: List[Row]) = {
    val cellsAndLevels = nodeRows.map(row => row.cellIterator().asScala.toList.zipWithIndex.find {
      case (cell, _) => cell.getCellType == CellType.STRING
    }.get)
    cellsAndLevels
  }

  private def readRows(path: Path): List[Row] = {
    val workbook = WorkbookFactory.create(path.toFile)
    workbook.getSheetAt(0).iterator().asScala.toList
  }

  private def isNotAHeader(row: Row): Boolean = !row.getCell(0).getStringCellValue.equals("Poziom 1")
}
