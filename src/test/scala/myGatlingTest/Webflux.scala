package myGatlingTest



import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import java.util.UUID
import scala.concurrent.duration.DurationInt
class Webflux extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:8080")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Webflux ")
      .get("/api/tutorials")

    )

  setUp(
    scnAdvLoad.inject(
//      constantUsersPerSec(1000) during (10 seconds)
            rampConcurrentUsers(300).to(1000).during(10)
    ).protocols(httpProtocol))
}

