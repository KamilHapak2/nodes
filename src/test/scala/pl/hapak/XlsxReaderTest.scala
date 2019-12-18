package pl.hapak

import java.nio.file.Path

import org.scalatest.{FlatSpec, Matchers}

class XlsxReaderTest extends FlatSpec with Matchers {

  private val xlsxReader: XlsxReader = new XlsxReader

  "xlsxReader" should "read cell details and skip header" in {

    val path = Path.of(ClassLoader.getSystemResource("test1.xlsx").toURI)

    val cellDetails = xlsxReader.readCellDetails(path)

    cellDetails should have size 12

    cellDetails.head shouldBe NodeDetails(Node(1, "A"), 0)
    cellDetails(1) shouldBe NodeDetails(Node(2, "AA"), 1)
    cellDetails(2) shouldBe NodeDetails(Node(3, "AA1"), 2)
    cellDetails(3) shouldBe NodeDetails(Node(4, "AA2"), 2)
    cellDetails(4) shouldBe NodeDetails(Node(5, "AB"), 1)
    cellDetails(5) shouldBe NodeDetails(Node(6, "B"), 0)
    cellDetails(6) shouldBe NodeDetails(Node(7, "C"), 0)
    cellDetails(7) shouldBe NodeDetails(Node(8, "CA"), 1)
    cellDetails(8) shouldBe NodeDetails(Node(9, "CA1"), 2)
    cellDetails(9) shouldBe NodeDetails(Node(10, "CA2"), 2)
    cellDetails(10) shouldBe NodeDetails(Node(11, "D"), 0)
    cellDetails(11) shouldBe NodeDetails(Node(12, "DA"), 1)
  }

  "xlsxReader" should "read cell details and skip header 2" in {

    val path = Path.of(ClassLoader.getSystemResource("test2.xlsx").toURI)

    val cellDetails = xlsxReader.readCellDetails(path)

    cellDetails should have size 17

    cellDetails.head shouldBe NodeDetails(Node(1, "A"), 0)
    cellDetails(1) shouldBe NodeDetails(Node(2, "AA"), 1)
    cellDetails(2) shouldBe NodeDetails(Node(3, "AB"), 1)
    cellDetails(3) shouldBe NodeDetails(Node(4, "AB1"), 2)
    cellDetails(4) shouldBe NodeDetails(Node(5, "AB2"), 2)
    cellDetails(5) shouldBe NodeDetails(Node(6, "AC"), 1)
    cellDetails(6) shouldBe NodeDetails(Node(7, "B"), 0)
    cellDetails(7) shouldBe NodeDetails(Node(8, "BA"), 1)
    cellDetails(8) shouldBe NodeDetails(Node(9, "BB"), 1)
    cellDetails(9) shouldBe NodeDetails(Node(10, "BC"), 1)
    cellDetails(10) shouldBe NodeDetails(Node(11, "C"), 0)
    cellDetails(11) shouldBe NodeDetails(Node(12, "CA"), 1)
    cellDetails(12) shouldBe NodeDetails(Node(13, "CA1"), 2)
    cellDetails(13) shouldBe NodeDetails(Node(14, "CA2"), 2)
    cellDetails(14) shouldBe NodeDetails(Node(15, "CB"), 1)
    cellDetails(15) shouldBe NodeDetails(Node(16, "D"), 0)
    cellDetails(16) shouldBe NodeDetails(Node(17, "DA"), 1)
  }
}
