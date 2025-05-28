package myGatlingTest


import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import java.util.UUID
import scala.concurrent.duration.DurationInt
class CryptoLoadTest extends Simulation {
  val uuidFeeder = Iterator.continually(Map("uuid" -> randomUUID)) // Feeder to generate and feed UUIDs into the session
  def randomUUID: String = UUID.randomUUID().toString // Function to generate random UUID strings



  val httpProtocol = http
//    .baseUrl("https://sp.epramaan.in:8038/securecrypto/")
    .baseUrl("https://172.25.145.146:8080/crypto/")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Adv Connector ")
//      .post("/rsadecrypt")
      .post("/encrypt")


      .header("Content-Type", "application/xml")
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"1\" sa=\"1\" opr=\"encrypt\" tkntype=\"hard\" mask=\"no\" keytype=\"aes\" lk=\"db2aa4cb-94ce-45ca-87b9-490dfa68ff76\" ts=\"2024-05-07T10:41:40.066\" txn=\"90f13a35-37ab-45ff-b7ee-363492b14211\" xmlns=\"http://www.cdac.in/cryptoservice\">\n\t<data>TWpBeU15MHdOUzB3TWxReE1EbzBNVG8wTUM0d05qYk1kckhXZ2UvOWJnRTh6Q1V2QnZzQnRKaW1hWk1XUGpaeUFWRnBDb3RVUFRmUDhXZlBsbXhBSmJvbVB4ZGpuMVMvOC9qNEdlM0dkMzhxQjZyM2RPeS8zaUFjbFBYbFMyaHhzU1BqYWFCam8yWGd0NHVOT2xDZjFCclp0MGcyeXl1eHhYd0JRcmdTTDMramg2dVU0aEVmY2NRbXU2NVVTcjZ3RE1GcmNKRjhTRURFWkNtcjlrNklKLzF0TlFWOTI4enltWkxxMlNzcmduVGl2ZmRPY2ZjZlcwT3pLZFQyVjZqNHNQT1pHZDhSTkFMc2c4TnhzaXNjNjMrWkVrQTFWdWxwUVEvQ1hoLzlRNGRjWGF4MFIrVjZtOU5Pa1lsQ2l1enZQUUpjUHpQZ1V4UlFpNWhBcEo4cnFOWGZCZVA4d3JncmtDNzZodjlabFN3QjRDdWxOTjJDWk9jb1JwUUZveXEzZVNGOEk2cW1lNDNsUDN5UHNUTT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"fMzqVCvAKsNWjFhJUCawOrQ3R5QEky7/VtjBbfiSVESlTj3Eacb5Qh3WnUyZjXo6npizFEX6Ltl5otbfSMnE2CDsLwdynFKX1JV4THWzEMLcpG/ntLMRcYnBbjPD8Pcs+4nrHmolXkMrbVrqNAN/q+jK6BG9DEKOOZdmWYfG4o7Ejws1+HdsuNaDq/enJta3YIPPdxXJeTg0mSBD0R5cnX8CpdjV+YlnMSKdesMWgyzMOcsRmevyAG4JDnVojsfEYa94+mSVzDUNEDcE/1Heuu3HmyQaEkuTeKhBgU20eXFzufMVfFy9DQ0GDhVFKC4syo4FrrhOEzbtyG8GcTq6Qw==\"/>\n\t</CustOpts>\n</request>")).asXml
//      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"77\" sa=\"77\" opr=\"rsadecrypt\" tkntype=\"soft\" mask=\"no\" keytype=\"rsa2048hybd\" lk=\"24820652-1588-5bde-aaca-8cec9ff7cc2c\" ts=\"2023-08-01T16:46:30.684\" txn=\"08e6876e-6733-433a-b814-a2423b7346c0\" xmlns=\"http://www.cdac.in/cryptoservice\" xmlns:ns2=\"http://www.cdac.in/pii\">\n\t<data>TWpBeU15MHdPQzB3TVZReE5qbzBOam96TUM0Mk9EU0JjSUg3SzlmNW1TdzBZRWI3U05DVEVtaTB6K2Rwb3JnaWVjdzhGY0xSdjdrMExZaVo3YUlONjhPOGt6Q0FQVkZYVC9rd1g0Njhia2FPaHVvVXVxckFDcG1SYUwrMDg3cHVYTld2YTNXbVhXUlZoWG1jZHdObEhxZ0V0OXJ0K1E0YVc5Y1J3Z09kbGVwcDVodzdFYnNmMlo0SlJEeEJ1d2ZtYU5KKy9mZ2E1TVdwTjJyQkR0QlpSaDNtN1lDZWFheG1zbTd2SGdlRjBnUS9Keit4eWtjU0VaOGtIdlNqRDhYbnRnbWtRK3JUV25GNVVvbzhEUmNSRFpRNFRYb2dpY3MwME53VDIrU1JIVUhSMkRTSkp6aENYUGlSVWFXYWNRTU9KVERUNXk0VDR0allTd2JIN054YUcyQkt2STY0em9FWkhvL1RiUThTTlhpUHdQUkl4a0h5disvQ2JTZ0k2dVJkWGQzdEFkRTNCN3Y3eHVabjNBPT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"Wk5kUUpiHxVp+RNhPM0MOatp8yKk1yNmmn4W4RLmZ0mKgnXKEaEPVoJypqTRJtaYF5xB+laoY2UXvbektV++uNnv/jRc3OHTk9e3X3bEKS0wd1tesm+5G1s72CdVCi6KNThjWH1KeFLUKSPrkxY1fmwg+mzyIglWDbQkkm1ezvgPSxD5otU7jPdHjzG+HYFKlE6a9FNb+LyDbwbI7GhuIJU8+/Ou2BK7MCZGpQX1h8xZnFH2p7Xmb2RR1X7MUYQycXKdrNhxaWmLCWtHcABmb4pHi6wK7oeL0KItoT79FGlmvY3Ns0kOyLHkUGKC8beaLzp65kW+Flcpq0WqysHRcg==\"/>\n\t</CustOpts>\n</request>")).asXml
    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(10) during (10 seconds)
    ).protocols(httpProtocol))
}

