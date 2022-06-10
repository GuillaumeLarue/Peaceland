import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger


object Consumer extends App {


  val spark = SparkSession.builder()
    .master("local[4]")
    .appName("SparkByExample")
    .config("spark.driver.bindAddress", "127.0.0.1")
    .getOrCreate()

  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "firsttopic")
    .option("startingOffsets", "earliest")
    .load()
    .select("value")


  df.writeStream
    .trigger(Trigger.ProcessingTime("15 seconds"))
    .format("json")
    // local mais fonctionne exactement pareil ex: "hdfs//namenode:namenode-port/tmp/datalake/RidesRaw"
    .option("path", "file:/tmp/hdfs")
    // ex: "hdfs//namenode:namenode-port/tmp/checkpoints/RidesRaw"
    .option("checkpointLocation", "file:/tmp/checkpoint")
    .outputMode("append")
    .start()
    .awaitTermination()
}