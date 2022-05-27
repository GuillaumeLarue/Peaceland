import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val props: Properties = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer: KafkaProducer[String,String] = new KafkaProducer[String, String](props)
    println("Hello world!")
  }
}