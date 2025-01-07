package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import java.util.UUID
import scala.concurrent.duration.DurationInt
class ApiCall extends Simulation {
  val httpProtocol = http
    .baseUrl(" http://192.168.68.160:8082")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Sample War for Dockers ")
//      .get("/greet/skywalking")
//      .get("/alert/load")
      .get("/")

    )

  setUp(
    scnAdvLoad.inject(
//      constantUsersPerSec(1) during (30 seconds)
      rampConcurrentUsers(10).to(30).during(300)
    ).protocols(httpProtocol))
}

