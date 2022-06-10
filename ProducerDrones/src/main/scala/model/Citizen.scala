package model

case class Citizen(id: Long, score: Int, long: Double, lat: Double) {
  def this() = this(
    scala.util.Random.nextInt(500000),
    scala.util.Random.nextInt(100),
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000
  )

  def this(id: Long) = this(
    id,
    scala.util.Random.nextInt(100),
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000
  )

  def this(id: Long, score: Int) = this(
    id,
    score,
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000
  )

  override def toString: String = "id:" + id + "; score:" + score + "; long: " + long + "; lat:" + lat

  def toCsv: String = id + ";" + score + ";" + long + ";" + lat

  def toJson: String = {
    s"""
       {
         "id": $id,
         "score": $score,
         "long": $long,
         "lat": $lat
       }
     """
  }

  override def equals(obj: Any): Boolean = obj match {
    case citizen: Citizen => citizen.id == id
    case _ => false
  }
}
