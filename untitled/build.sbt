ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "kafka-client",
    version := "0.1",
    scalaVersion := "2.12.8",
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.1"
  )
