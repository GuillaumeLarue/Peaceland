package model

import java.time.LocalDateTime

class Message(val timestampArg: LocalDateTime = LocalDateTime.now(),
              val peacewatcherIdArg: Long = scala.util.Random.nextInt(500000),
              val peacewatcherLongArg: Double = (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
              val peacewatcherLatArg: Double = (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000,
              val citoyenIdArg: Long = scala.util.Random.nextInt(8000000),
              val citoyenPeacescoreArg: Int = scala.util.Random.nextInt(100)) {
  override def toString: String = {
    "timestampArg:"+ timestampArg + "; peacewatcherID:"+ peacewatcherIdArg + "; peacewatcherLongArg:"+ peacewatcherLongArg + "; peacewatcherLatArg:" + peacewatcherLatArg + "; citoyenIdArg:"+ citoyenIdArg + "; citoyenPeacescoreArg:" + citoyenPeacescoreArg
  }
}
