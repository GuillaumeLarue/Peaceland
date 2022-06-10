package model

case class Drone(id: Long, battery: Int, long: Double, lat: Double) {
  def this() = this(
    scala.util.Random.nextInt(500000),
    100,
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000
  )

  def this(id: Long) = this(
    id,
    100,
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000
  )

  override def toString: String = "id:" + id + ";battery:" + battery + "; long: " + long + "; lat:" + lat

  def toCsv: String = id + ";" + battery + ";" + long + ";" + lat

  def toJson: String = {
    s"""
       {
         "id": $id,
         "battery": $battery
         "long": $long,
         "lat": $lat
       }
     """
  }

  override def equals(obj: Any): Boolean = obj match {
    case drone: Drone => drone.id == id
    case _ => false
  }
}
