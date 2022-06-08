import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import java.time.Duration
import java.util
import java.util.Properties
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.IterableHasAsScala
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

object Consumer extends App {

  val spark = SparkSession.builder()
    .master("local[4]")
    .appName("SparkByExample")
    .config("spark.driver.bindAddress", "127.0.0.5")
    .getOrCreate()


  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "firsttopic")
    .option("startingOffsets","earliest")
    .load()

  df.writeStream
    .trigger(Trigger.ProcessingTime("15 seconds"))
    .outputMode("update")
    .format("console")
    .start()
    .awaitTermination()

  /* Useful to push in hdfs
  df.writeStream
      .trigger(Trigger.ProcessingTime("15 seconds"))
      .format("parquet") //voir si ya plus adapté
      .option("path", "file:/home/trabet/peaceland/hdfs") //local mais fonctionne exactement pareille ex: "hdfs//namenode:namenode-port/tmp/datalake/RidesRaw"
      .option("checkpointLocation", "file:/home/trabet/peaceland/checkpoint") //ex: "hdfs//namenode:namenode-port/tmp/checkpoints/RidesRaw"
      .outputMode("append")
      .start()*/




}