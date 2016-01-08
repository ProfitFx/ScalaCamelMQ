name := "ScalaCamelMQ"

version := "1.0"

scalaVersion := "2.11.2"

val cv = "2.13.2"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.apache.camel" % "camel-core" %   cv,        //"2.16.1",
  "org.apache.camel" % "camel-scala" % cv,
  "org.apache.camel" % "camel-http4" % cv,
  "org.apache.camel" % "camel-jms" % cv,// "2.13.2",
  "org.apache.camel" % "camel-jxpath" % cv,
  "org.apache.camel" % "camel-sql" % cv,
  "org.apache.camel" % "camel-jdbc" % cv,
  "org.apache.httpcomponents" % "httpmime" % "4.3.5",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.apache.commons" % "commons-dbcp2" % "2.1.1",
  "org.apache.activemq" % "activemq-core" % "5.7.0")


libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1207"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"



