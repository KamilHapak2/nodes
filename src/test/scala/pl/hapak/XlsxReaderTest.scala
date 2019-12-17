package pl.hapak

import java.nio.file.Path

import org.scalatest.{FlatSpec, Matchers}

class XlsxReaderTest extends FlatSpec with Matchers {

  private val xlsxReader: XlsxReader = new XlsxReader

  "xlsxReader" should "read cell details and skip header" in {

    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)

    val cellDetails = xlsxReader.getCellDetails(path)

    cellDetails.size shouldBe 12
  }

}
