package com.myGatlingTest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt
class KrdhConnector extends Simulation {

  val httpProtocol = http
//    .baseUrl("http://localhost:8080")
    .baseUrl("http://10.210.9.54:8088")


  val csvFeeder = csv("data/dummy.csv").circular
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec()
    .exec(http("LoadTestHome for #{aadhaar}")
      .post("/inputs")
      .header("Content-Type", "text/plain")
      .body(StringBody("""{ ${aadhaar},${gender}}"""))
      //.body(StringBody("""{ 534354163142,MALE}"""))
//      .check(bodyString.saveAs("responseBody"))

      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns5:Otp ts=\"2023-12-12T11:05:08\" txn=\"9ef9cc01-5815-06bd-bb4a-d439653672a1\" ver=\"2.5\" sa=\"0000840000\" ac=\"0000840000\" uid=\"344366652812\" lk=\"MIcsA2gwbveJi0ZvLz4AzOVIOrltoUDXdWX9HtM0rQQAqjpRpE7_3bk\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\"><ns5:Opts ch=\"00\"/></ns5:Otp>")).asXml

      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns5:Otp ts=\"${aadhaar}\" txn=\"9ef9cc01-5815-06bd-bb4a-d439653672a1\" ver=\"2.5\" sa=\"0000840000\" ac=\"0000840000\" uid=\"344366652812\" lk=\"MIcsA2gwbveJi0ZvLz4AzOVIOrltoUDXdWX9HtM0rQQAqjpRpE7_3bk\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\"><ns5:Opts ch=\"00\"/></ns5:Otp>")).asXml




    )
//    .exec(session => {
//      scala.reflect.io.File("E://KrdhLoadResponse.csv").appendAll(session("responseBody").as[String] + "\n")
//      session
//    }
//    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(300) during (60 seconds)
    ).protocols(httpProtocol))
}
