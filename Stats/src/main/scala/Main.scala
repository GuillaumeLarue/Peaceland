import org.apache.log4j.{Level, Logger}
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
    val finalDf = getCol(decodeDf)
    finalDf.printSchema()
    finalDf.show(50, truncate = false)


    // Question 1: how many reports on average per day ?
    def AverageNumberReportByDay(df: DataFrame): Double = {
      val tmpDf = df.select(date_format(col("timestamp"), "yyyy-MM-dd")
        .alias("day"))
        .groupBy("day")
        .count()
        .groupBy("count")
        .avg()
      tmpDf.select(mean("avg(count)"))
        .take(1)(0)
        .getAs[Double]("avg(avg(count))")
    }

    val averageNumberReportByDay = AverageNumberReportByDay(finalDf)

    //Question 2: angry evening, morning or night ?
    def TimeToBeAngry(df: DataFrame): DataFrame = {
      df.filter(col("citizenPeacescore") <= 20)
        .groupBy(hour(col("timestamp")).alias("dayDour"))
        .count()
        .alias("tot")
    }

    val dfTimeAtAngry = TimeToBeAngry(finalDf)

    // Question 3: what is the day with the biggest number of angry people ?
    def DayWithBiggestAngry(df: DataFrame): DataFrame = {
      df.withColumn("dayofweek", dayofweek(col("timestamp")))
        .filter(col("citizenPeacescore") <= 20)
        .groupBy("dayofweek")
        .count()
    }


    val dfDayWithBiggestAngry = DayWithBiggestAngry(finalDf)

    // Question 4: what is the ratio of alert ?
    def RatioAlert(df: DataFrame): Float = {
      df.filter(col("citizenPeacescore") <= 20).count() / df.count().toFloat
    }

    val ratioAlert = RatioAlert(finalDf)

    // Question 5: what is the average of Peacescore ?
    def AveragePeaceScore(df: DataFrame): DataFrame = {
      df
        //.groupBy(col("citizenId"))
        .select(mean("citizenPeacescore"))
        .alias("averageCitizenPeacescore")
    }

    val dfAveragePeaceScore = AveragePeaceScore(finalDf)


    println("Question 1: how many reports on average per day ? ")
    print(averageNumberReportByDay)
    println()
    println()

    println("Question 2: angry evening, morning or night ?")
    dfTimeAtAngry.show(false)


    println("Question 3: what is the day with the biggest number of angry people ?")
    dfDayWithBiggestAngry.show(false)

    println("Question 4: what is the ratio of alert ?")
    println(ratioAlert)
    println()

    println("Question 5: what is the average of Peacescore ?")
    dfAveragePeaceScore.show(false)

  }
}