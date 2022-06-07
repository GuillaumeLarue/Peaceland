ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / libraryDependencies ++= Seq (
  "org.apache.kafka" % "kafka-clients" % "3.1.0",
  "org.apache.spark" %% "spark-core" % "3.2.1",
  "org.apache.spark" %% "spark-sql" % "3.2.1",
  "org.apache.spark" %% "spark-mllib" % "3.2.1"
)

lazy val root = (project in file("."))
  .settings(
    name := "ConsumerHdfs"
  )
