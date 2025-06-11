package Exploration

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class DockerDB extends Simulation {
  val httpProtocol = http
    .baseUrl("http://127.0.0.1:8080")

  val scnAdvLoad = scenario("LoadTest for Docker DB")
    .exec(http("LoadTest For Adv Connector ")
      .post("/tutorials")
      .body(StringBody("{\n  \"title\":\"Spring Boot \",\n  \"description\":\"Spring Boot Connection to Psql Docker\"\n}")).asJson

    )

  setUp(
    scnAdvLoad.inject(
            constantUsersPerSec(50) during (1800 seconds)
//      constantUsersPerSec(10) during (120 seconds)
//            rampConcurrentUsers(10).to(50).during(30)
    ).protocols(httpProtocol))
}

