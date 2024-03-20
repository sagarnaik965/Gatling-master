package computerdatabase;

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import javafx.util.Duration.seconds

import scala.concurrent.duration.DurationInt

class ConnectorLoadTest extends Simulation{

  val httpProtocol = http
//    .baseUrl("http://10.210.9.109:8082")
    .baseUrl("http://localhost:8080")
//  val csvFeeder = csv("data/loadData.csv")

  val csvFeeder = csv("dummy.csv").circular



  val scnLoad = scenario("LoadTest")
    .feed(csvFeeder)
//    .exec(getSpecificGame())
    .exec(http("LoadTestHome for #{aadhaar}")
      .post("/Stub/inputs")
      .header("Content-Type", "text/plain")
      .body(StringBody("""{ #{aadhaar},#{gender}}"""))
      //.body(StringBody("""{ 534354163142,MALE}"""))
    )


  setUp(
    scnLoad.inject(
//      			nothingFor(60 seconds),
//      atOnceUsers(10),
//      			rampUsers(2) during (10 seconds),
      			constantUsersPerSec(300) during (60 seconds)
    ).protocols(httpProtocol)
  )


}
