/**
  * Created by EnoT on 05.01.2016.
  */

import org.apache.camel.LoggingLevel._
import org.apache.camel.component.http4.HttpOperationFailedException
import org.apache.camel.{Exchange, Processor}
import org.apache.camel.scala.dsl.builder.RouteBuilder

class XMLFromFileToFile extends RouteBuilder {


  """file:target/xmlIn/?include=.*\.xml&keepLastModified=true""" ==> {

        val myProcessor = (exchange: Exchange) => {
      val body = xml.XML.loadString(exchange.getIn.getBody(classOf[String]))
      val mID = java.util.UUID.randomUUID()
      val rID = body \\ "mID"
      val xprinter = new scala.xml.PrettyPrinter(200, 4)
      val outXml = xprinter.format(<xml><mID>{mID}</mID><refID>{rID}</refID></xml>)
      exchange.getOut.setBody(outXml)
      exchange.getOut.setHeader(Exchange.FILE_NAME, mID + "out.xml")
    }


    id("upload xml")
    to("log:xml")
    to("file:target/xmlOut")
    process(myProcessor) to ("file:target/xmlOutProc")
   // process(_.in = "123") to ("file:target/xmlOut")
  }


}
