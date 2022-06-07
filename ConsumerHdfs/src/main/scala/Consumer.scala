import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import java.time.Duration
import java.util
import java.util.Properties
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.IterableHasAsScala

import org.apache.spark.sql.SparkSession

object Consumer extends App {

  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate()

  println("First SparkContext:")
  println("APP Name :" + spark.sparkContext.appName)
  println("Master :" + spark.sparkContext.master)

  val props: Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[IntegerDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")

  val consumer: KafkaConsumer[Int, String] = new KafkaConsumer[Int, String](props)
  consumer.subscribe(util.Arrays.asList("firsttopic"))

  whiletrueconsumer(consumer)
  println("| Key | Message | Partition | Offset |")

  @tailrec def whiletrueconsumer(consumer: KafkaConsumer[Int, String]): Unit = {
    val records: ConsumerRecords[Int, String] = consumer.poll(Duration.ofSeconds(1))
    records.asScala.foreach { record =>
      println(s"| ${record.key()} | ${record.value()} | ${record.partition()} | ${record.offset()} |")
    }
    consumer.commitSync()
    whiletrueconsumer(consumer)
  }
}