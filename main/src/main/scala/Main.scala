import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer
import org.codehaus.jackson.map.deser.std.StringDeserializer

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

    producer.close()

    /*
    // Code for consumer (see in the powerpoint given by the professor)
    val props2: Properties = new Properties()
    props2.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props2.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    props2.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    props2.put(ConsumerConfig.GROUP_ID_CONFIG, "myconsumergroup")

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props2)
    #consumer.subscribe(List("my_topic_1", "my_topic_2").asJava)
    */
    println("End")
  }
}