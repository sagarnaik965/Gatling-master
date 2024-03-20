package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import io.gatling.core.Predef.Simulation
import javafx.util.Duration.seconds

import scala.concurrent.duration.DurationInt

class DmsConnWebservice extends Simulation{
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
  val csvFeeder = csv("data/Connector11Load.csv")

  val ConnWebserviceLoadTest = scenario("nodejsLoad")
        .feed(csvFeeder)
    .exec(http("nodejsLoad For Node to Webservice ")
      .post("/struid")
      .header("Content-Type", "text/plain")
//      .body(StringBody("{\n    \"aadhaarNum\": \"639903540131\",\n    \"refNum\": \"1210506147305697280\",\n    \"ac\": \"A100001\",\n    \"sa\": \"A100001\",\n    \"lk\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n     \"opr\" : \"getuid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.8.107:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson
      .body(StringBody("{\n    \"aadhaarNum\": \"${aadhaar}\",\n    \"refNum\": \"1195300288053194752\",\n    \"ac\": \"A100066\",\n    \"sa\": \"A100066\",\n    \"lk\": \"368d093d-0f59-4a55-af8c-947414593cc1\",\n    \"opr\": \"struid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.8.107:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson

    )

  setUp(
    ConnWebserviceLoadTest.inject(
      //      constantUsersPerSec(200) during (20 seconds)
      constantUsersPerSec(150) during (15 seconds)
    ).protocols(httpProtocol)
  )

}
