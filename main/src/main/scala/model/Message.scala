package model
import kafka.utils.Json
import org.scala_tools.time.Imports._

class Message(var timestamp_arg: DateTime = DateTime.now(),
              var n_arg: Int = 1, var lat_min_arg: Double = -90.0,
              var lat_max_arg: Double = 90.0,
              var lon_min_arg: Double = -180.0, var lon_max_arg: Double = 180.0,
              var peacewatcher_long_arg: Double = (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
              var peacewatcher_lat_arg: Double = (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000,
              var citoyen_id_arg: Long = scala.util.Random.nextInt(8000000), var citoyen_peacescore_arg: Int = scala.util.Random.nextInt(100)) {
  val timestamp: DateTime = timestamp_arg
  val n: Int = n_arg
  val lat_min: Double = lat_min_arg
  val lat_max: Double = lat_max_arg
  val lon_min: Double = lon_min_arg
  val lon_max: Double = lon_max_arg
  val peacewatcher_long: Double = peacewatcher_long_arg
  val peacewatcher_lat: Double = peacewatcher_lat_arg
  val citoyen_id: Long = citoyen_id_arg
  val citoyen_peacescore: Int = citoyen_peacescore_arg
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
