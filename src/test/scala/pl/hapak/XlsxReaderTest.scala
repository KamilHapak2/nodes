package pl.hapak

import java.nio.file.Path

import org.scalatest.{FlatSpec, Matchers}

// todo dokładniejsze asercje
class XlsxReaderTest extends FlatSpec with Matchers {

  private val xlsxReader: XlsxReader = new XlsxReader

  "xlsxReader" should "read cell details and skip header" in {

    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)

    val cellDetails = xlsxReader.readCellDetails(path)

    cellDetails.size shouldBe 12
  }

  "xlsxReader" should "read cell details and skip header 2" in {

    val path = Path.of(ClassLoader.getSystemResource("test2.xlsx").toURI)

    val cellDetails = xlsxReader.readCellDetails(path)

    cellDetails.size shouldBe 17
  }

}
