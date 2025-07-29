package Webel

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt

class CryptoLoadTest extends Simulation {
  val httpProtocol = http

    .baseUrl("http://172.25.145.146:8080")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Adv Connector ")
      .post("/crypto/encrypt")
      .header("Content-Type", "application/xml")

      //      //----------------------------  XML for RSA-decrypt   ------------------------------------
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request \n    ac=\"1\" \n    sa=\"1\" \n    opr=\"encrypt\" \n    tkntype=\"hard\" \n    mask=\"no\" \n    keytype=\"aes\" \n    lk=\"db2aa4cb-94ce-45ca-87b9-490dfa68ff76\" \n    ts=\"2024-05-07T10:41:40.066\" \n    txn=\"90f13a35-37ab-45ff-b7ee-363492b14211\" \n    xmlns=\"http://www.cdac.in/cryptoservice\"\n>\n    <data>\n        TWpBeU15MHdOUzB3TWxReE1EbzBNVG8wTUM0d05qYk1kckhXZ2UvOWJnRTh6Q1V2QnZzQnRKaW1hWk1XUGpaeUFWRnBDb3RVUFRmUDhXZlBsbXhBSmJvbVB4ZGpuMVMvOC9qNEdlM0dkMzhxQjZyM2RPeS8zaUFjbFBYbFMyaHhzU1BqYWFCam8yWGd0NHVOT2xDZjFCclp0MGcyeXl1eHhYd0JRcmdTTDMramg2dVU0aEVmY2NRbXU2NVVTcjZ3RE1GcmNKRjhTRURFWkNtcjlrNklKLzF0TlFWOTI4enltWkxxMlNzcmduVGl2ZmRPY2ZjZlcwT3pLZFQyVjZqNHNQT1pHZDhSTkFMc2c4TnhzaXNjNjMrWkVrQTFWdWxwUVEvQ1hoLzlRNGRjWGF4MFIrVjZtOU5Pa1lsQ2l1enZQUUpjUHpQZ1V4UlFpNWhBcEo4cnFOWGZCZVA4d3JncmtDNzZodjlabFN3QjRDdWxOTjJDWk9jb1JwUUZveXEzZVNGOEk2cW1lNDNsUDN5UHNUTT0=\n    </data>\n    <CustOpts>\n        <ParaName \n            name=\"sessionKey\" \n            value=\"fMzqVCvAKsNWjFhJUCawOrQ3R5QEky7/VtjBbfiSVESlTj3Eacb5Qh3WnUyZjXo6npizFEX6Ltl5otbfSMnE2CDsLwdynFKX1JV4THWzEMLcpG/ntLMRcYnBbjPD8Pcs+4nrHmolXkMrbVrqNAN/q+jK6BG9DEKOOZdmWYfG4o7Ejws1+HdsuNaDq/enJta3YIPPdxXJeTg0mSBD0R5cnX8CpdjV+YlnMSKdesMWgyzMOcsRmevyAG4JDnVojsfEYa94+mSVzDUNEDcE/1Heuu3HmyQaEkuTeKhBgU20eXFzufMVfFy9DQ0GDhVFKC4syo4FrrhOEzbtyG8GcTq6Qw==\"\n        />\n    </CustOpts>\n</request>")).asXml

    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(500) during (20 seconds)
      //      rampConcurrentUsers(400).to(500).during(600 seconds)

    ).protocols(httpProtocol))

}


