package com.myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt
class VaultLoadTestUpdated extends Simulation {
  val httpProtocol = http
    .baseUrl("http://127.0.0.1:8080")

  val csvFeeder = csv("data/vaultLoad.csv")
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec(http("LoadTest For Secure-Vault  ")
      .post("/vault/struid")
      .header("Content-Type", "application/xml")
       .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2951\" ts=\"2025-05-28T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8UElJIHhtbG5zPSJodHRwOi8vd3d3LmNkYWMuaW4vcGlpIj4KICAgIDxpZCBwaWQ9Ijk1MDAyMDk0OTc0OSIvPgo8L1BJST4=</data>\n</request>")).asXml
    )
    .exec(session => {
      println("Request txn: " + session("txn").as[String])
      session
    })


  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(10) during (1 seconds)
      //      rampConcurrentUsers(200).to(400).during(100)
    ).protocols(httpProtocol))
}





