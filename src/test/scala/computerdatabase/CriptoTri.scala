package computerdatabase


import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import java.util.UUID
import scala.concurrent.duration.DurationInt
class CryptoTri extends Simulation {
  val httpProtocol = http
    .baseUrl("https://172.16.07.11:8080/crypto/")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Adv Connector ")
      .post("/rsa2048encrypt")
      .header("Content-Type", "application/xml")
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100002\" sa=\"A100002\" opr=\"rsa2048encrypt\" tkntype=\"hard\" keytype=\"rsa2048\" lk=\"819ca50a-b338-4c61-adde-3e0e057b004d\" ts=\"2025-06-05T10:11:02.359\" txn=\"00ad3446-d0a6-4711-9cc2-3679c690c31d\" mac=\"BC-F4-D4-B6-5E-F1\" ip=\"10.210.5.135\" xmlns=\"http://www.cdac.in/cryptoservice\" xmlns:ns2=\"http://www.cdac.in/pii\">\n\t<data>NzM1NzMxMTcyMTQ2</data>\n\t<CustOpts>\n\t\t<ParaName name=\"pin\" value=\"NO\"/>\n\t</CustOpts>\n</request>")).asXml
    )

  setUp(
    scnAdvLoad.inject(
            constantUsersPerSec(10) during (10 seconds)
//      rampConcurrentUsers(400).to(500).during(600 seconds)
    ).protocols(httpProtocol))
}

