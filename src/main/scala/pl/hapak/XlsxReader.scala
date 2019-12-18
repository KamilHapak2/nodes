package pl.hapak

import java.nio.file.{Files, Path}

import org.apache.poi.ss.usermodel.{Cell, CellType, Row, WorkbookFactory}

import scala.jdk.CollectionConverters._

class XlsxReader {

  private val headerFirstCellText = "Poziom 1"

  def readCellDetails(path: Path): List[NodeDetails] = {

    if (Files.notExists(path)) {
      throw new RuntimeException(path + " does not exist!")
    }

    val rows = readRows(path)
    val nodeRows = rows.filter(row => isNotAHeader(row))

    val cellsAndLevels: List[(Cell, Int)] = getCellsAndTheirLevels(nodeRows)

    cellsAndLevels.map(c => NodeDetails(Node(c._1.getRow.getRowNum, c._1.getStringCellValue), c._2))
  }

  private def getCellsAndTheirLevels(nodeRows: List[Row]) = {
    val cellsAndLevels = nodeRows.map(row => row.cellIterator().asScala.toList.zipWithIndex.find {
      case (cell, _) => isNotEmptyStringCell(cell)
    }.get)
    cellsAndLevels
  }

  private def isNotEmptyStringCell(cell: Cell) = {
    cell.getCellType == CellType.STRING && cell.getStringCellValue.nonEmpty
  }

  private def readRows(path: Path): List[Row] = {
    val workbook = WorkbookFactory.create(path.toFile)
    workbook.getSheetAt(0).iterator().asScala.toList
  }

  private def isNotAHeader(row: Row): Boolean = {
    val cell = row.getCell(0)

    val value = cell match {
      case null => ""
      case cell => cell.getStringCellValue
    }
    !value.equals(headerFirstCellText)
  }
}
