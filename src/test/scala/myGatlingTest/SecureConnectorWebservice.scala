package myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import io.gatling.core.Predef.Simulation
import javafx.util.Duration.seconds

import scala.concurrent.duration.DurationInt

class SecureConnectorWebservice extends Simulation{
  val httpProtocol = http
    .baseUrl("http://localhost:8090")
  val csvFeeder = csv("data/csndraTest.csv")

  val ConnWebserviceLoadTest = scenario("nodejsLoad")
    .feed(csvFeeder)
    .exec(http("Secure Vault Connector Load test With END-TO-END Test ")
      .post("/struid")
      .header("Content-Type", "text/plain")
//            .body(StringBody("{\n    \"aadhaarNum\": \"639903540131\",\n    \"refNum\": \"1224280626099986432\",\n    \"ac\": \"5\",\n    \"sa\": \"5\",\n    \"lk\": \"85e53f1c-2fdf-11ec-8a6d-52540023dc00\",\n     \"opr\" : \"getuid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://192.168.10.9:8080/vaultsecure/\",\n    \"keyIdentifier\": \"\"\n}")).asJson

//      .body(StringBody("{\n    \"aadhaarNum\": \"${aadhaar}\",\n    \"refNum\": \"1195300288053194752\",\n    \"ac\": \"A100001\",\n    \"sa\": \"A100001\",\n    \"lk\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n    \"opr\": \"struid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.4.178:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson

      .body(StringBody("{\n    \"aadhaarNum\": \"${aadhaar}\",\n    \"refNum\": \"1195300288053194752\",\n    \"ac\": \"A100001\",\n    \"sa\": \"A100001\",\n    \"lk\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n    \"opr\": \"struid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.4.89:8082/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson

    )

  val Test = scenario("nodejsLoad")
//    .feed(csvFeeder)
    .exec(http("Secure Vault Connector Load test With END-TO-END Test ")
      .post("/check")
    )

  setUp(
    ConnWebserviceLoadTest.inject(
            constantUsersPerSec(100) during (10 seconds)
//      constantUsersPerSec(50) during (50 seconds)
    ).protocols(httpProtocol)
  )

}
