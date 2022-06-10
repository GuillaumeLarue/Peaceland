import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{FloatType, IntegerType, TimestampType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Main {
  def main(args: Array[String]): Unit = {

    Logger.getRootLogger.setLevel(Level.OFF)

    val spark = SparkSession.builder()
      .master("local[4]")
      .appName("SparkByExample")
      .config("spark.driver.bindAddress", "127.0.0.1")
      .getOrCreate()

    def readFile(path: String): DataFrame = {
      spark.read
        .format("json")
        .option("mergeSchema", "true")
        .load(path + "*.json")
        .select("value")
    }

    def decodeFile(df: DataFrame): DataFrame = {
      df
        .withColumn(
          "value",
          when(
            base64(unbase64(col("value"))) === col("value"),
            unbase64(col("value")).cast("string")
          ).otherwise(col("value"))
        )
    }

    def getCol(decodedDf: DataFrame): DataFrame = {
      decodedDf
        .withColumn("timestamp", split(col("value"), ";").getItem(0).cast(TimestampType))
        .withColumn("peacewatcherID", split(col("value"), ";").getItem(1).cast(IntegerType))
        .withColumn("peacewatcherLong", split(col("value"), ";").getItem(2).cast(FloatType))
        .withColumn("peacewatcherLat", split(col("value"), ";").getItem(3).cast(FloatType))
        .withColumn("citizenId", split(col("value"), ";").getItem(4).cast(IntegerType))
        .withColumn("citizenPeacescore", split(col("value"), ";").getItem(5).cast(IntegerType))
        .drop("value")
    }

    val df = readFile("/tmp/hdfs/")
    val decodeDf = decodeFile(df)
    val finalDf = getCol(decodeDf).filter(col("citizenPeacescore") <= 20)
    finalDf.printSchema()
    finalDf.show(50, truncate = false)

    finalDf.write.json("/tmp/dfJson")

  }
}