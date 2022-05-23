import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}

val props: Properties = new Properties()

val producer: KafkaProducer[String,String] = new KafkaProducer