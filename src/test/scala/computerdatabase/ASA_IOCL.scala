package com.myGatlingTest
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class ASA_IOCL extends Simulation {
  val httpProtocol = http
//    .baseUrl("http://10.10.86.8:8080/crypto")
   //.baseUrl("http://10.210.9.244:8888")
   .baseUrl("http://10.210.9.97:8888")
  //    .baseUrl("http://localhost:8080/crypto")
   val csvFeeder = csv("data/tenThousand3.csv")
   
  val scnAdvLoad = scenario("LoadTest")
      .feed(csvFeeder)
    .exec(http("LoadTest For ASA-IOCL ")
      .post("/otprequest")
//     .post("/encrypt")

//      .check(status.not(200)) // Check that the status code is 200
      .header("Content-Type", "application/xml")

//      //----------------------------  XML for ASA-IOCL   ------------------------------------
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns5:Otp ts=\"2023-12-12T11:05:08\" txn=\"${txn}\" ver=\"2.5\" sa=\"0000840000\" ac=\"0000840000\" uid=\"344366652812\" lk=\"MIcsA2gwbveJi0ZvLz4AzOVIOrltoUDXdWX9HtM0rQQAqjpRpE7_3bk\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\"><ns5:Opts ch=\"00\"/></ns5:Otp>")).asXml

      //----------------------------  XML for AES encrypt   ------------------------------------
 //  .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100020\" sa=\"A100020\" opr=\"encrypt\" tkntype=\"soft\" mask=\"no\" keytype=\"aes\" lk=\"4b457cf6-3d93-58fd-8e5f-798f9b078808\" ts=\"2023-05-02T10:41:40.066\" txn=\"b5d0bc76-1d98-46c9-9754-7f695bcf2eec\" xmlns=\"http://www.cdac.in/cryptoservice\">\n\t<data>TWpBeU15MHdOUzB3TWxReE1EbzBNVG8wTUM0d05qYk1kckhXZ2UvOWJnRTh6Q1V2QnZzQnRKaW1hWk1XUGpaeUFWRnBDb3RVUFRmUDhXZlBsbXhBSmJvbVB4ZGpuMVMvOC9qNEdlM0dkMzhxQjZyM2RPeS8zaUFjbFBYbFMyaHhzU1BqYWFCam8yWGd0NHVOT2xDZjFCclp0MGcyeXl1eHhYd0JRcmdTTDMramg2dVU0aEVmY2NRbXU2NVVTcjZ3RE1GcmNKRjhTRURFWkNtcjlrNklKLzF0TlFWOTI4enltWkxxMlNzcmduVGl2ZmRPY2ZjZlcwT3pLZFQyVjZqNHNQT1pHZDhSTkFMc2c4TnhzaXNjNjMrWkVrQTFWdWxwUVEvQ1hoLzlRNGRjWGF4MFIrVjZtOU5Pa1lsQ2l1enZQUUpjUHpQZ1V4UlFpNWhBcEo4cnFOWGZCZVA4d3JncmtDNzZodjlabFN3QjRDdWxOTjJDWk9jb1JwUUZveXEzZVNGOEk2cW1lNDNsUDN5UHNUTT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"fMzqVCvAKsNWjFhJUCawOrQ3R5QEky7/VtjBbfiSVESlTj3Eacb5Qh3WnUyZjXo6npizFEX6Ltl5otbfSMnE2CDsLwdynFKX1JV4THWzEMLcpG/ntLMRcYnBbjPD8Pcs+4nrHmolXkMrbVrqNAN/q+jK6BG9DEKOOZdmWYfG4o7Ejws1+HdsuNaDq/enJta3YIPPdxXJeTg0mSBD0R5cnX8CpdjV+YlnMSKdesMWgyzMOcsRmevyAG4JDnVojsfEYa94+mSVzDUNEDcE/1Heuu3HmyQaEkuTeKhBgU20eXFzufMVfFy9DQ0GDhVFKC4syo4FrrhOEzbtyG8GcTq6Qw==\"/>\n\t</CustOpts>\n</request>")).asXml

      // .check(bodyString.saveAs("Crypto_Response"))

    )
//    .exec(session => {
//            val data = session("Crypto_Response").as[String]
//            println("Crypto_Response: " + data)
//            session
//          })
3

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(100) during (1000 seconds)
    ).protocols(httpProtocol))
//    .assertions(
//      // Verify the number of successful responses with status code 200
//      details("My Request").successfulRequests.count.is(1)
//    )
}

