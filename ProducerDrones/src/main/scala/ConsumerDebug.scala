import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, KafkaConsumer}
import org.apache.kafka.common.serialization.{IntegerDeserializer, StringDeserializer}

import java.time.Duration
import java.util
import java.util.Properties
import scala.annotation.tailrec

object ConsumerDebug extends App {
  val props: Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[IntegerDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")

  val consumer: KafkaConsumer[Int, String] = new KafkaConsumer[Int, String](props)
  consumer.subscribe(util.Arrays.asList("firsttopic"))


  println("| Key | Message | Partition | Offset |")

  @tailrec
  def forEachIterator(recordsIterator: util.Iterator[ConsumerRecord[Int, String]]): Unit = {
    if (recordsIterator.hasNext) {
      val record = recordsIterator.next()
      println(s"| ${record.key()} | ${record.value()} | ${record.partition()} | ${record.offset()} |")
      forEachIterator(recordsIterator)
    }
  }

  @tailrec
  def whileTrue(consumer: KafkaConsumer[Int, String]): Unit = {
    val recordsIterator: util.Iterator[ConsumerRecord[Int, String]] = consumer.poll(Duration.ofSeconds(1)).iterator()
    forEachIterator(recordsIterator)
    consumer.commitSync()
    whileTrue(consumer)
  }
  whileTrue(consumer)
}