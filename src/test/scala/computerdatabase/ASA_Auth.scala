package computerdatabase

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class ASA_Auth extends Simulation {
  val httpProtocol = http
    .baseUrl("http://10.10.10.11:8080")
  val csvFeeder = csv("data/tenThousand.csv")
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec(http("LoadTest For ASA-IOCL ")
      .post("/otprequest")
      //     .post("/encrypt")

      //      .check(status.not(200)) // Check that the status code is 200
      .header("Content-Type", "application/xml")

      //      //----------------------------  XML for ASA-IOCL   ------------------------------------
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns5:Otp ts=\"2024-06-18T11:05:08\" txn=\"${txn}\" ver=\"2.5\" sa=\"TEST000001\" ac=\"TEST000001\" uid=\"344366652812\" lk=\"MNnbNgo4SYmgiqAGdC8ZoN5XToW-B9YAEGN7o3Oj294hFWx5LapAqD8\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\"><ns5:Opts ch=\"00\"/></ns5:Otp>")).asXml


    )
    setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(10) during (10 seconds)
    ).protocols(httpProtocol))

}