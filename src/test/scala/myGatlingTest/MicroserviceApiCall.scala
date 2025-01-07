package myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import scala.util.Random
import scala.concurrent.duration.DurationInt

class MicroserviceApiCall extends Simulation {

  val httpProtocol = http
    .baseUrl("http://10.210.4.213:9080") // Replace with your base URL

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Sample War for Dockers")
      .post("/api") // Endpoint
      .header("Content-Type", "application/json") // Set Content-Type header
      .header("apikey", "edd1c9f034335f136f87ad84b625c8f1") // Add API key in header
      .body(StringBody(session => {
        val randomNumber = Random.nextInt(6) // Generate a random number between 0 and 25
        randomNumber.toString // Return the number as a string
      }))
    )

  setUp(
    scnAdvLoad.inject(
      // 5 TPS for 30 seconds
//      constantUsersPerSec(4).during(30.seconds),

      // Then 10 TPS for 5 minutes
//      constantUsersPerSec(7).during(2.minutes),

//      constantUsersPerSec(2).during(2.minutes),

      constantUsersPerSec(3).during(30.minutes),



//      constantUsersPerSec(10) during (1800 seconds)
    ).protocols(httpProtocol)
  )
}
