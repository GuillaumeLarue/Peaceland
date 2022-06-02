import Consumer.consumer

import java.time.Duration
import org.apache.kafka.clients.consumer.ConsumerConfig._

import java.util.Properties
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import java.util

object Consumer extends App {

  val consumerProperties = new Properties()
  consumerProperties.setProperty(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092") //It s possible to add URL but here is a local POC
  consumerProperties.setProperty(GROUP_ID_CONFIG, "firstid")
  consumerProperties.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, classOf[IntegerDeserializer].getName)
  consumerProperties.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
  consumerProperties.setProperty(AUTO_OFFSET_RESET_CONFIG, "earliest")
  consumerProperties.setProperty(ENABLE_AUTO_COMMIT_CONFIG, "false")

  val consumer = new KafkaConsumer[Int, String](consumerProperties)
  consumer.subscribe(util.Arrays.asList("firsttopic"))

  println("| Key | Message | Partition | Offset |")

  keep_consumer(consumer)

  def keep_consumer(consumer : KafkaConsumer[Int,String]): KafkaConsumer[Int,String] = {
    val polledRecords: ConsumerRecords[Int, String] = consumer.poll(Duration.ofSeconds(1))
    consumer.commitSync()
    if (!polledRecords.isEmpty) {
      println(s"Polled ${polledRecords.count()} records")
      val recordIterator = polledRecords.iterator()
      while (recordIterator.hasNext) {
        val record: ConsumerRecord[Int, String] = recordIterator.next()
        println(s"| ${record.key()} | ${record.value()} | ${record.partition()} | ${record.offset()} |")
      }
    }
    keep_consumer(consumer)
  }

}