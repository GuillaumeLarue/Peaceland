import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{FloatType, IntegerType, TimestampType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object Main {
  def main(args: Array[String]): Unit = {
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
        .
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

    def getCol(decoded_df: DataFrame): DataFrame = {
      decoded_df
        .withColumn("timestamp", split(col("value"), ";").getItem(0).cast(TimestampType))
        .withColumn("peacewatcherID", split(col("value"), ";").getItem(1).cast(IntegerType))
        .withColumn("peacewatcherLong", split(col("value"), ";").getItem(2).cast(FloatType))
        .withColumn("peacewatcherLat", split(col("value"), ";").getItem(3).cast(FloatType))
        .withColumn("citizenId", split(col("value"), ";").getItem(4).cast(IntegerType))
        .withColumn("citizenPeacescore", split(col("value"), ";").getItem(5).cast(IntegerType))
        .drop("value")
    }

    val df = readFile("/tmp/hdfs/")
    val decode_df = decodeFile(df)
    val final_df = getCol(decode_df)
    final_df.printSchema()
    final_df.show(50, truncate = false)

    /*
    Question 1: how many reports on average per day ?
    TODO Question 2: angry evening, morning or night ?
    Question 3: what is the day with the biggest number of angry people ?
    TODO Question 4: what is the ratio of alert ?
    TODO Question 5: what is the average of Peacescore ?
     */

    // Question 1: how many reports on average per day ?
    def AverageNumberReportByDay(df: DataFrame): DataFrame = {
      df.select(date_format(col("timestamp"), "yyyy-MM-dd")
        .alias("day"))
        .groupBy("day")
        .count()
        .groupBy("count")
        .avg()
      // .take(1)(0)
      // .getAs[Float]("avg")
    }
    // println(AverageNumberReportByDay(final_df))
    AverageNumberReportByDay(final_df).show(false)

    // Question 3: what is the day with the biggest number of angry people ?
    def DayWithBiggestAngry(df: DataFrame): DataFrame = {
      df.withColumn("dayofweek", dayofweek(col("timestamp")))
        .filter(col("citizenPeacescore") <= 20)
        .groupBy("dayofweek")
        .count()
    }

    DayWithBiggestAngry(final_df).show(false)

    // Question 5: what is the average of Peacescore ?
    // def AveragePeaceScore(df : DataFrame)
  }
}