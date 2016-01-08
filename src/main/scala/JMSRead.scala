/**
  * Created by EnoT on 03.01.2016.
  */

import javax.sql.rowset.Predicate

import org.apache.camel.{Exchange, Processor}
import org.apache.camel.scala.dsl.builder.RouteBuilder
import org.apache.camel.scala.dsl.languages.Languages


//
//"direct:c" ==> {  to("mock:c")  recipients(jxpath("./in/body/destination"))


class JMSRead extends RouteBuilder with Languages{

    val routFile = scala.io.Source.fromFile("routes.csv").getLines()
    val addrMap = scala.collection.mutable.Map[String, String]()
    routFile.foreach(l =>  addrMap += (l.split(",")(0) -> l.split(",")(1)))


  """test-jms:queue:CIT1.TEST.IN""" ==> {


    id("jms_id")
    log("Incoming message")
    to("file:target/incoming")
    //to ("bean:TestBean")
    to("direct:resp1", "direct:resp2")
  }


  """direct:resp1""" ==> {
    val myProcessor = (exchange: Exchange) => {
      val body = xml.XML.loadString(exchange.getIn.getBody(classOf[String]))
      val mID = java.util.UUID.randomUUID()
      val rID = (body \\ "mID").text
      val xprinter = new scala.xml.PrettyPrinter(200, 4)
      val outXml = xprinter.format(<xml><mID>{mID}</mID> <refID>{rID}</refID></xml>)
      exchange.getOut.setBody(outXml)
    }
    process(myProcessor) to ("test-jms:queue:CIT1.TEST.OUT")
  }

  """direct:resp2""" ==> {
    val myProcessor = (exchange: Exchange) => {
      val body = xml.XML.loadString(exchange.getIn.getBody(classOf[String]))
      val mID = java.util.UUID.randomUUID()
      val rID = (body \\ "mID").text
      val xprinter = new scala.xml.PrettyPrinter(200, 4)
      val outXml = xprinter.format(<xml><mID>{mID}</mID><refID>{rID}</refID><body>someBody</body></xml>)
      exchange.getOut.setBody(outXml)
    }

    delay(2 second)
    process(myProcessor) to ("test-jms:queue:CIT1.TEST.OUT")
  }
}

//class TestBean extends Processor{
//def process (input: Exchange){
//    val ster = "_____________" + input.getIn.getBody + "_____________"
//  println(ster)
// }
//}


