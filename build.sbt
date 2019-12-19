name := "nodesTask"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.apache.poi" % "poi" % "4.1.1"
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "4.1.1"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.11",
  "com.typesafe.akka" %% "akka-stream" % "2.5.26",
  "ch.megard" %% "akka-http-cors" % "0.4.2"
)
