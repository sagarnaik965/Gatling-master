package myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import scala.util.Random
import scala.concurrent.duration.DurationInt

class MicroserviceApiCall extends Simulation {

  val httpProtocol = http
    .baseUrl("http://10.210.4.213:8081") // Replace with your base URL

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Sample War for Dockers")
      .post("/api") // Endpoint
      .header("Content-Type", "application/json") // Set Content-Type header
      .header("apikey", "edd1c9f034335f136f87ad84b625c8f1") // Add API key in header
      .body(StringBody(session => {
        val randomNumber = Random.nextInt(10) // Generate a random number between 0 and 25
        randomNumber.toString // Return the number as a string
      }))
    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(100) during (180 seconds) // Inject 50 users per second for 5 minutes
//      rampConcurrentUsers(100).to(120).during(180)
    ).protocols(httpProtocol)
  )
}
