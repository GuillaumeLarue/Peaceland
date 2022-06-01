package model
import java.time.LocalDateTime

class Message(val timestamp_arg: LocalDateTime = LocalDateTime.now(),
              val n_arg: Int = 1, val lat_min_arg: Double = -90.0,
              val lat_max_arg: Double = 90.0,
              val lon_min_arg: Double = -180.0, val lon_max_arg: Double = 180.0,
              val peacewatcher_long_arg: Double = (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
              val peacewatcher_lat_arg: Double = (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000,
              val citoyen_id_arg: Long = scala.util.Random.nextInt(8000000), val citoyen_peacescore_arg: Int = scala.util.Random.nextInt(100)) {
}

//
//val laptop1 = new Message(citoyen_id_arg = 1324123412, citoyen_peacescore_arg = 13)
//// JSON part
//
//val residentJson: JsValue = Json.toJson(laptop1)
//
////import play.api.libs.json._
////implicit val residentReads = Json.reads[Message]
////
////import play.api.libs.json._
////import play.api.libs.functional.syntax._
////
////implicit val residentReads = (
////  (__ \ "name").read[String] and
////    (__ \ "age").read[Int] and
////    (__ \ "role").readNullable[String]
////)(Resident)
