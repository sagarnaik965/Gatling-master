package Exploration

import io.gatling.core.Predef._
import io.gatling.http.Predef.http

class NodeAppTest extends Simulation {
  val httpProtocol = http
    .baseUrl("http://127.0.0.1:8080")
  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Adv Connector ")
      .post("/api/todos")
      .body(StringBody("{\n    \"title\":\"node\",\n    \"description\":\"node test api\",\n    \"completed\":\"false\"\n}")).asJson
    )
  setUp(
    scnAdvLoad.inject(
//            constantUsersPerSec(3) during (10 seconds)
      rampConcurrentUsers(10).to(100).during(60)

    ).protocols(httpProtocol))
}

