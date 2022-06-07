package model

import java.time.LocalDateTime

class Message(val timestamp: LocalDateTime,
              val peacewatcherId: Long = scala.util.Random.nextInt(500000),
              val peacewatcherLong: Double,
              val peacewatcherLat: Double,
              val citizenId: Long,
              val citizenPeacescore: Int) {

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
  }
}
