ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

ThisBuild / libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-sql" % "3.2.1",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.2.1"
)

lazy val root = (project in file("."))
  .settings(
    name := "ConsumerAlerts"
  )
