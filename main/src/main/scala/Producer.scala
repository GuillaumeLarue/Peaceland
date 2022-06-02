import model.Message
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringSerializer}

import java.util.Properties

object Producer extends App {

  val topicName = "firsttopic"

  val props: Properties = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[IntegerSerializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

  val producer: KafkaProducer[Int, String] = new KafkaProducer[Int, String](props)

  producer.send(new ProducerRecord[Int, String](topicName, 1, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 2, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 3, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 4, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 5, new Message().toString))
  producer.send(new ProducerRecord[Int, String](topicName, 6, new Message().toString))

  producer.flush()
  producer.close()
}