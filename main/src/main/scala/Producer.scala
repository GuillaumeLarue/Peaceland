import model.Message

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringSerializer}

object Producer extends App {

  val topicName = "firsttopic"

  val producerProperties = new Properties()
  producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[IntegerSerializer].getName)
  producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

  val producer = new KafkaProducer[Int, String](producerProperties)

  producer.send(new ProducerRecord[Int, String](topicName, 1, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 2, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 3, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 4, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 5, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 6, new Message().toString))

  producer.flush()
  producer.close()
}