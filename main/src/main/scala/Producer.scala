import model.Message
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.{IntegerSerializer, StringSerializer}

import java.util.Properties
import scala.annotation.tailrec


object Producer extends App {

  val topicName = "firsttopic"

  val props: Properties = new Properties()
  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[IntegerSerializer])
  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

  val producer: KafkaProducer[Int, String] = new KafkaProducer[Int, String](props)

  val nbrMessage: Int = 25

  @tailrec
  def whileTrue(producer: KafkaProducer[Int, String], n: Int): Unit = n match {
    case 0 => producer.close()
    case _ =>
      producer.send(new ProducerRecord[Int, String](topicName, nbrMessage - n + 1, new Message().toString))
      producer.flush()
      whileTrue(producer, n - 1)
  }

  whileTrue(producer, nbrMessage)
}