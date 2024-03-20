package computerdatabase

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt

class nodeToWebservice extends Simulation {
  val httpProtocol = http
    .baseUrl("http://localhost:3000")
  val csvFeeder = csv("data/Connector11Load.csv")


  val scnAdvLoad = scenario("nodejsLoad")
//    .feed(csvFeeder)
    .exec(http("nodejsLoad For Node to Webservice ")
      .post("/apiCall")
      .header("Content-Type", "text/plain")
      .body(StringBody("{\n    \"aadhaarNum\": \"639903540131\",\n    \"refNum\": \"1210506147305697280\",\n    \"ac\": \"A100001\",\n    \"sa\": \"A100001\",\n    \"lk\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n     \"opr\" : \"getuid\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.8.107:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson
    )

  val nodejsLoad = scenario("nodejsLoad")
    //    .feed(csvFeeder)
    .exec(http("LoadTest For NodeJS ")
      .post("/node-struid")
      .header("Content-Type", "text/plain")
      .body(StringBody("{\n    \"number\": \"639903540131\",\n    \"RefNum\": \"1202867767600295936\",\n    \"Ac\": \"A100001\",\n    \"SA\": \"A100001\",\n    \"LK\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n     \"opr\" : \"struid\",\n    \"keyType\": \"aes\",\n    \"tokenType\": \"soft\",\n    \"url\": \"http://10.210.8.107:8080/vault/\",\n    \"keyIdentifier\": \"\"\n}")).asJson


    )


  setUp(
    nodejsLoad.inject(
//      constantUsersPerSec(200) during (20 seconds)
      constantUsersPerSec(1) during (1 seconds)

    ).protocols(httpProtocol)


  )
}
