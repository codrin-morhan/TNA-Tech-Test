import java.io.{File, PrintWriter}
import scala.collection.mutable.ListBuffer

object CsvModifier extends App {

  updateRowValues("test.csv", "origin", "Londom", "London")

  def updateRowValues(csvFileName: String, column: String, oldValue: String, newValue: String): Unit = {
    val bufferedSource = io.Source.fromFile(csvFileName)
    var updatedFileContents = new ListBuffer[String]()

    val headers = bufferedSource.getLines.take(1).toList.head.split(",").map(_.trim)
    val columnNumber = headers.indexOf(column.trim)

    for (line <- bufferedSource.getLines) {
      val cols = line.split(",")
      cols.update(columnNumber, cols(columnNumber).replaceAll(oldValue, newValue))
      updatedFileContents += cols.mkString(",")
    }
    bufferedSource.close

    val writer=new PrintWriter(new File("test.csv"))
    writer.write(updatedFileContents.mkString("\n"))
    writer.close()
  }
}
