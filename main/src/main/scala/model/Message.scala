package model

import java.time.LocalDateTime


case class Message(timestamp: LocalDateTime,
                   peacewatcherId: Long,
                   peacewatcherLong: Double,
                   peacewatcherLat: Double,
                   citizenId: Long,
                   citizenPeacescore: Int) {

  def this() = this(LocalDateTime.now(),
    scala.util.Random.nextInt(500000),
    (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
    (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000,
    scala.util.Random.nextInt(8000000),
    scala.util.Random.nextInt(100)
  )

  override def toString: String = {
    "timestamp:" + timestamp +
      "; peacewatcherID:" + peacewatcherId +
      "; peacewatcherLong:" + peacewatcherLong +
      "; peacewatcherLat:" + peacewatcherLat +
      "; citizenId:" + citizenId +
      "; citizenPeacescore:" + citizenPeacescore

  def toCSV: String = {
    timestampArg + ";" + peacewatcherIdArg + ";" + peacewatcherLongArg + ";" + peacewatcherLatArg + ";" + citoyenIdArg + ";" + citoyenPeacescoreArg

  }
}
