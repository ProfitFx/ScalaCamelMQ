import javax.sql.rowset.Predicate

import org.apache.camel.Exchange
import org.apache.camel.scala.dsl.builder.RouteBuilder

/**
  * Created by EnoT on 06.01.2016.
  */
class XMLFromFileProcessing extends RouteBuilder {

  val xprinter = new scala.xml.PrettyPrinter(200, 4)
  val routFile = scala.io.Source.fromFile("routes.csv").getLines()
  val addrMap = scala.collection.mutable.Map[String, String]()
  routFile.foreach(l =>  addrMap += (l.split(",")(0) -> l.split(",")(1)))

  """file:target/in/?include=.*\.xml&keepLastModified=true""" ==> {

    val myProcessor = (exchange: Exchange) => {
      val body = xml.XML.loadString(exchange.getIn.getBody(classOf[String]))
      val mapRoute = (body \\ "action").text
      val to = addrMap.getOrElse(mapRoute,"file:target/actionError")
      exchange.getOut.setHeader("action",to)
      exchange.getOut.setBody(xprinter.format(body))
      //to("file:target/xmlOutProc")
    }

    id("inputFile")
    log("Read file")
    //to("file:target/route1")
    //log("Write file")
    process(myProcessor)
    recipients(_.in("action"))


//    choice{
//      when (_.in("action") == "error"){
//        to("file:target/route1")
//        log("Write good file")}
//      otherwise {to("file:target/error")
//        log("Write error file")
//      }
//    }
  //  act1route
    //  process(myProcessor) to("log:route")



  }
}
