package example

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ConnectorAsService17 extends Simulation {

  val httpProtocol = http
    .baseUrl("http://10.210.9.168:9090") // Replace with your API base URL
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  val jsonBody: String =
    """
      {
        "aadhaarNum": "799563939588",
        "refNum": "1400807504174854144",
        "ac": "77",
        "sa": "77",
        "lk": "a89e83f6-6d72-11ee-b962-0242ac120001",
        "tkntype": "soft",
        "url": "https://advpreprod.epramaan.in/securevault/"
      }
    """

  val scn = scenario("Load Test API")
    .exec(
      http("POST /api/getuid")
        .post("/api/getuid")
        .body(StringBody(jsonBody)).asJson
        .check(status.is(200)) // Adjust if needed
    )

  setUp(
    scn.inject(
      constantUsersPerSec(1) during (5 seconds)

    )
  ).protocols(httpProtocol)
}
