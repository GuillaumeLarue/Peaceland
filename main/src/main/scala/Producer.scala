import model.{Citizen, Drone, Message}
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
  val nbrDrone: Int = 10
  val nbrCitizen: Int = 15

  def generateDrone(n: Int): List[Drone] = {
    @tailrec
    def genDrone(acc: List[Drone], n: Int): List[Drone] = n match {
      case 0 => acc
      case _ => genDrone(new Drone(nbrDrone - n + 1) :: acc, n - 1)
    }

    genDrone(Nil, n)
  }

  def generateCitizen(n: Int): List[Citizen] = {
    @tailrec
    def genCitizen(acc: List[Citizen], n: Int): List[Citizen] = n match {
      case 0 => acc
      case _ => genCitizen(new Citizen(nbrCitizen - n + 1) :: acc, n - 1)
    }

    genCitizen(Nil, n)
  }

  @tailrec
  def whileTrue(producer: KafkaProducer[Int, String], n: Int): Unit = n match {
    case 0 => producer.close()
    case _ =>
      producer.send(new ProducerRecord[Int, String](topicName, nbrMessage - n + 1, new Message().toCSV))
      producer.flush()
      whileTrue(producer, n - 1)
  }

  whileTrue(producer, nbrMessage)
}