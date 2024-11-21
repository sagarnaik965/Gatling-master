package com.myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class CryptoService extends Simulation {
  val httpProtocol = http
    .baseUrl("http://192.168.10.9:8080")

//  val csvFeeder = csv("data/vaultLoad.csv")

  val scnAdvLoad = scenario("LoadTest")

//    .feed(csvFeeder)

    .exec(http("LoadTest For ASA-IOCL ")
      .post("/crypto/encrypt")
      .header("Content-Type", "application/xml")


      //      //----------------------------  XML for Crypto   ------------------------------------
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"encrypt\" tkntype=\"soft\" mask=\"no\" keytype=\"aes\" lk=\"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\" ts=\"2024-05-07T10:41:40.066\" txn=\"90f13a35-37ab-45ff-b7ee-363492b14211\" xmlns=\"http://www.cdac.in/cryptoservice\">\n\t<data>TWpBeU15MHdOUzB3TWxReE1EbzBNVG8wTUM0d05qYk1kckhXZ2UvOWJnRTh6Q1V2QnZzQnRKaW1hWk1XUGpaeUFWRnBDb3RVUFRmUDhXZlBsbXhBSmJvbVB4ZGpuMVMvOC9qNEdlM0dkMzhxQjZyM2RPeS8zaUFjbFBYbFMyaHhzU1BqYWFCam8yWGd0NHVOT2xDZjFCclp0MGcyeXl1eHhYd0JRcmdTTDMramg2dVU0aEVmY2NRbXU2NVVTcjZ3RE1GcmNKRjhTRURFWkNtcjlrNklKLzF0TlFWOTI4enltWkxxMlNzcmduVGl2ZmRPY2ZjZlcwT3pLZFQyVjZqNHNQT1pHZDhSTkFMc2c4TnhzaXNjNjMrWkVrQTFWdWxwUVEvQ1hoLzlRNGRjWGF4MFIrVjZtOU5Pa1lsQ2l1enZQUUpjUHpQZ1V4UlFpNWhBcEo4cnFOWGZCZVA4d3JncmtDNzZodjlabFN3QjRDdWxOTjJDWk9jb1JwUUZveXEzZVNGOEk2cW1lNDNsUDN5UHNUTT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"fMzqVCvAKsNWjFhJUCawOrQ3R5QEky7/VtjBbfiSVESlTj3Eacb5Qh3WnUyZjXo6npizFEX6Ltl5otbfSMnE2CDsLwdynFKX1JV4THWzEMLcpG/ntLMRcYnBbjPD8Pcs+4nrHmolXkMrbVrqNAN/q+jK6BG9DEKOOZdmWYfG4o7Ejws1+HdsuNaDq/enJta3YIPPdxXJeTg0mSBD0R5cnX8CpdjV+YlnMSKdesMWgyzMOcsRmevyAG4JDnVojsfEYa94+mSVzDUNEDcE/1Heuu3HmyQaEkuTeKhBgU20eXFzufMVfFy9DQ0GDhVFKC4syo4FrrhOEzbtyG8GcTq6Qw==\"/>\n\t</CustOpts>\n</request>")).asXml

    )

  setUp(
    scnAdvLoad.inject(
//      constantUsersPerSec(2) during (10 seconds)
      rampConcurrentUsers(100).to(150).during(120)
    ).protocols(httpProtocol))
}

