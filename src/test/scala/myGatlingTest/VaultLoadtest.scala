package com.myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt
class VaultLoadtest extends Simulation {
  val httpProtocol = http
//    .baseUrl("https://sp.epramaan.in:8038/vault/struid")
    .baseUrl("http://127.0.0.1:8080")

  val csvFeeder = csv("data/vaultLoad.csv")
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec(http("LoadTest For Secure-Vault  ")
      .post("/vault/struid")
      .header("Content-Type", "application/xml")
      //      //----------------------------  XML for Stagging Vault   ------------------------------------
//      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"77\" sa=\"77\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2955\" ts=\"2024-07-15T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>${data}</data>\n</request>")).asXml

      //      //----------------------------  XML for Stagging Vault   ------------------------------------
//            .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"1\" sa=\"1\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"db2aa4cb-94ce-45ca-87b9-490dfa68ff76\" ts=\"2025-05-02T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>${data}</data>\n</request>")).asXml

      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"struid\" tkntype=\"soft\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2951\" ts=\"2025-05-27T10:39:07.933\" txn=\"${txn}\" mac=\"5C-60-BA-A2-60-E3\" ip=\"10.210.8.141\" xmlns=\"http://www.cdac.in/cryptoservice\">\n    <data>${txn}</data>\n</request>")).asXml

    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(100) during (300 seconds)
//      rampConcurrentUsers(200).to(400).during(100)
    ).protocols(httpProtocol))
}





