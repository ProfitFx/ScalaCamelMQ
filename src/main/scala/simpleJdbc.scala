import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
  * Created by EnoT on 07.01.2016.
  */
class simpleJdbc extends RouteBuilder{
  """file:target/in/?include=.*\.xml&keepLastModified=true""" ==> {
    id("postgresTest")
    log("input")
    to("jdbc:postgresTest")
    to("log:jdbc")
   // to("file:target/out/")

  }
}
