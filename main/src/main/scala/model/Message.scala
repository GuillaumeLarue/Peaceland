package model

import java.time.LocalDateTime

class Message(val timestampArg: LocalDateTime = LocalDateTime.now(),
              val NArg: Int = 1,
              val latMinArg: Double = -90.0,
              val latMaxArg: Double = 90.0,
              val lonMinArg: Double = -180.0,
              val lon_max_arg: Double = 180.0,
              val peacewatcherLongArg: Double = (-180 * 10000 + scala.util.Random.nextInt(360 * 10000).toFloat) / 10000,
              val peacewatcherLatArg: Double = (-90 * 10000 + scala.util.Random.nextInt(180 * 10000).toFloat) / 10000,
              val citoyenIdArg: Long = scala.util.Random.nextInt(8000000),
              val citoyenPeacescoreArg: Int = scala.util.Random.nextInt(100)) {}