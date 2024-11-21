package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import java.util.UUID
import scala.concurrent.duration.DurationInt
class ApiCall extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8082")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Sample War for Dockers ")
//      .get("/greet/skywalking")
      .get("/alert/load")

    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(50) during (900 seconds)
//      rampConcurrentUsers(300).to(1000).during(120)
    ).protocols(httpProtocol))
}

