package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import java.util.UUID
import scala.concurrent.duration.DurationInt
class ApiCall extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080/")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Sample War for Dockers ")
      .get("square/5")

    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(10000) during (1 seconds)
//      rampConcurrentUsers(10).to(30).during(300)
    ).protocols(httpProtocol))
}

