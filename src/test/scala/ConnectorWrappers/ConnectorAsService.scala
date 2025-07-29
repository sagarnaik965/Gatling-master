import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ConnectorAsService extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8082") // Base URL
    .contentTypeHeader("application/json")

  val jsonPayload =
    """{
      |  "aadhaarNum": "57677846362",
      |  "refNum": "1390644454915682304",
      |  "ac": "77",
      |  "sa": "77",
      |  "lk": "ddb81abd-23d7-4cf6-ab5e-29d01bcc2955",
      |  "tkntype": "soft",
      |  "url": "https://advpreprod.epramaan.in/securevault/"
      |}""".stripMargin

  val scn = scenario("Connector Service Test")
    .exec(
      http("Post STRUID")
        .post("/struid/")
        .body(StringBody(jsonPayload)).asJson
        .check(status.is(200)) // Modify based on expected response
    )

  setUp(
    scn.inject(
      constantUsersPerSec(500) during (20 seconds)
//      atOnceUsers(1) // Change to rampUsers(n) over(time) for load testing
    ).protocols(httpProtocol)
  )
}
