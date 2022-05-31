ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / libraryDependencies ++= Seq (
  "org.apache.kafka" % "kafka-clients" % "3.1.0",
  "com.typesafe.play" %% "play" % "2.8.15"
)

lazy val root = (project in file("."))
  .settings(
    name := "sending"
  )

