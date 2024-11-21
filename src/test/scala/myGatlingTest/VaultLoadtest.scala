package com.myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt
class VaultLoadtest extends Simulation {
  val httpProtocol = http
//    .baseUrl("http://192.168.10.9:8080")
    .baseUrl("http://10.10.10.12:8080")

  val csvFeeder = csv("data/vaultLoad.csv")
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec(http("LoadTest For Vault  ")
      .post("/vault/struid")
      .header("Content-Type", "application/xml")

      //      //----------------------------  XML for Stagging Vault   ------------------------------------
//      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"77\" sa=\"77\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2955\" ts=\"2024-07-15T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>${data}</data>\n</request>")).asXml

      //      //----------------------------  XML for Stagging Vault   ------------------------------------
            .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2951\" ts=\"2024-11-08T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>${data}</data>\n</request>")).asXml
    )




  setUp(
    scnAdvLoad.inject(
//      constantUsersPerSec(1) during (120 seconds)
      rampConcurrentUsers(200).to(800).during(900)

    ).protocols(httpProtocol))
}





