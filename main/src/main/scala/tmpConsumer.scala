import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util
import java.util.Properties
import scala.jdk.CollectionConverters.IterableHasAsScala

object tmpConsumer extends App {
  val props: Properties = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
  props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
  props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")

  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(util.Arrays.asList("atopic", "my_topic_2"))

  while (true) {
    val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofSeconds(1))
    records.asScala.foreach { record =>
      println(s"offset= ${record.offset()}, key = ${record.key()}, value = ${record.value()}")
    }
    consumer.commitSync()
  }
}