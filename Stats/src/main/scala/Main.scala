import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    val path: String = "/tmp/hdfs/part-00000-7debf7b8-c570-4bcd-bde8-21079c7116a1-c000.json"

    val spark = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExample")
      .config("spark.driver.bindAddress", "127.0.0.1")
      .getOrCreate()

    val df = spark.read
      .json(path = path).select("value")
    df.printSchema()
    df.show(false)

    val decoded_df = df
      .withColumn(
        "value",
        when(
          base64(unbase64(col("value"))) === col("value"),
          unbase64(col("value")).cast("string")
        ).otherwise(col("value"))
      )
    decoded_df.printSchema()
    decoded_df.show(false)

    val mul_col_df = decoded_df
      .withColumn("timestamp", split(col("value"), ";").getItem(0))
      .withColumn("peacewatcherID", split(col("value"), ";").getItem(1))
      .withColumn("peacewatcherLong", split(col("value"), ";").getItem(2))
      .withColumn("peacewatcherLat", split(col("value"), ";").getItem(3))
      .withColumn("citizenId", split(col("value"), ";").getItem(4))
      .withColumn("citizenPeacescore", split(col("value"), ";").getItem(5))
      .drop("value")

    mul_col_df.show(false)


    def getListOfFiles(dir: String): List[String] = {
      val file = new File(dir)
      file.listFiles.filter(_.isFile)
        .filter(_.getName.endsWith(".json"))
        .map(_.getPath).toList
    }

    println(getListOfFiles("/tmp/hdfs"))
  }
}