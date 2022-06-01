import model.Message

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object Main {
  def main(args: Array[String]): Unit = {
    println("Begin!")
    val props: Properties = new Properties()

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])

    val key = "akey"
    val value = "adata"

    val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](props)

    val record = new ProducerRecord[String, String]("atopic", key, value)
    producer.send(record)

    val laptop1 = new Message(citoyenIdArg = 1324123412, citoyenPeacescoreArg = 13)


    producer.close()

    println("End")
  }
}