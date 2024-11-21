package myGatlingTest


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import javafx.util.Duration.seconds

import scala.concurrent.duration.DurationInt

class cassandraLoadTest extends Simulation {

  val httpProtocol = http
    .baseUrl("http://10.210.4.178:8080")
  val scnLoad = scenario("LoadTest")
    .exec(http("LoadTestCassancdra")
      .post("/senddata")
      .header("Content-Type", "text/plain")
      .body(StringBody(""""""))
    )


  setUp(
    scnLoad.inject(
            			nothingFor(5 seconds),
      //      			rampUsers(2) during (10 seconds),
      constantUsersPerSec(500) during (100 seconds)
    ).protocols(httpProtocol)
  )


}
