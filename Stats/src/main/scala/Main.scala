import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{base64, col, unbase64, when}
import org.apache.spark.sql.types._

object Main {
  def main(args: Array[String]): Unit = {
    val path: String = "/tmp/hdfs/part-00000-a841cd30-c81b-48c8-88da-8b2596b78379-c000.json"

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
  }
}