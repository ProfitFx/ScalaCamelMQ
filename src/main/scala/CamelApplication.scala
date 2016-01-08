import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.commons.dbcp2.BasicDataSource
import javax.sql.DataSource
import org.apache.camel.component.jms.JmsComponent
import org.apache.camel.impl.{SimpleRegistry, DefaultCamelContext}
import org.apache.camel.component.sql.SqlComponent
import org.apache.camel.component.jdbc.JdbcComponent
//import com.ibm.mq.jms._


object CamelApplication {

  def main(args: Array[String]) = {
    val ds = new BasicDataSource
    val rg = new SimpleRegistry
    ds.setDriverClassName("org.postgresql.Driver")
    //ds.setDriverClassName("com.mysql.jdbc.Driver")
    ds.setUrl("jdbc:postgresql://192.168.3.38:5432/test")
    ds.setUsername("postgres")
    ds.setPassword("624205")
    rg.put("postgresTest", ds)
    val context = new DefaultCamelContext(rg)
    context.setUseMDCLogging(true)
    context.addRoutes(new simpleJdbc())
    context.start()
    Thread.currentThread.join()
    //BasicDataSource
  }
}

//Old routes
//  val connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.3.38:61616")
//context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory))
//context.addRoutes(new JMSRead())

//context.addRoutes(new XMLFromFileProcessing())
//context.addRoutes(new JsonPost())
//context.addRoutes(new FileUpload())
//context.addRoutes(new XMLFromFileToFile())

// websphereMQ
//val connectionFactory = new MQQueueConnectionFactory
//connectionFactory.setHostName("impop-gw-by1.dev.centre-it.com")
//connectionFactory.setPort(1414)
//connectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP)
//connectionFactory.setQueueManager("BY.IISVVT.QM")
//connectionFactory.setChannel("APP.SVRCONN")
