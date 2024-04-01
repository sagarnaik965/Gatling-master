package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import io.gatling.core.Predef.Simulation
import javafx.util.Duration.seconds

import scala.concurrent.duration.DurationInt

class SecureConnectorWebservice extends Simulation{
  val httpProtocol = http
    .baseUrl("http://localhost:8080")
  val csvFeeder = csv("data/Connector11Load.csv")

  val ConnWebserviceLoadTest = scenario("nodejsLoad")
    .feed(csvFeeder)
    .exec(http("nodejsLoad For Node to Webservice ")
      .post("/getuid")
      .header("Content-Type", "text/plain")
            .body(StringBody("{\n    \"aadhaarNum\": \"639903540131\",\n    \"refNum\": \"1224280626099986432\",\n    \"ac\": \"5\",\n    \"sa\": \"5\",\n    \"lk\": \"85e53f1c-2fdf-11ec-8a6d-52540023dc00\",\n     \"opr\" : \"getuid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://192.168.10.9:8080/vaultsecure/\",\n    \"keyIdentifier\": \"\"\n}")).asJson
//      .body(StringBody("{\n    \"aadhaarNum\": \"${aadhaar}\",\n    \"refNum\": \"1195300288053194752\",\n    \"ac\": \"A100066\",\n    \"sa\": \"A100066\",\n    \"lk\": \"368d093d-0f59-4a55-af8c-947414593cc1\",\n    \"opr\": \"struid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.8.107:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson

    )

  val Test = scenario("nodejsLoad")
//    .feed(csvFeeder)
    .exec(http("nodejsLoad For Node to Webservice ")
      .post("/check")
    )

  setUp(
    Test.inject(
            constantUsersPerSec(700) during (20 seconds)
//      constantUsersPerSec(50) during (50 seconds)
    ).protocols(httpProtocol)
  )

}
