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
//      .baseUrl("http://10.210.4.213:8080/securecrypto/")
    .baseUrl("http://127.0.0.1:8080/securecrypto/")
//    .baseUrl("http://10.210.4.178:8080/securecrypto")

//  val csvFeeder = csv("tenThousand3.csv")
  val scnAdvLoad = scenario("LoadTest")
//    .feed(csvFeeder)
//    .exec(feed(uuidFeeder)) // Feed the UUID generated by uuidFeeder into the session
    .exec(http("LoadTest For Adv Connector ")
      .post("/rsadecrypt")
      .header("Content-Type", "application/xml")
//      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"rsadecrypt\" tkntype=\"soft\" mask=\"no\" keytype=\"rsa2048hybd\" lk=\"24820652-1588-5bde-aaca-8cec9ff7cc2c\" ts=\"2023-08-01T16:46:30.684\" txn=\"${uuid}\" xmlns=\"http://www.cdac.in/cryptoservice\" xmlns:ns2=\"http://www.cdac.in/pii\">\n\t<data>TWpBeU15MHdPQzB3TVZReE5qbzBOam96TUM0Mk9EU0JjSUg3SzlmNW1TdzBZRWI3U05DVEVtaTB6K2Rwb3JnaWVjdzhGY0xSdjdrMExZaVo3YUlONjhPOGt6Q0FQVkZYVC9rd1g0Njhia2FPaHVvVXVxckFDcG1SYUwrMDg3cHVYTld2YTNXbVhXUlZoWG1jZHdObEhxZ0V0OXJ0K1E0YVc5Y1J3Z09kbGVwcDVodzdFYnNmMlo0SlJEeEJ1d2ZtYU5KKy9mZ2E1TVdwTjJyQkR0QlpSaDNtN1lDZWFheG1zbTd2SGdlRjBnUS9Keit4eWtjU0VaOGtIdlNqRDhYbnRnbWtRK3JUV25GNVVvbzhEUmNSRFpRNFRYb2dpY3MwME53VDIrU1JIVUhSMkRTSkp6aENYUGlSVWFXYWNRTU9KVERUNXk0VDR0allTd2JIN054YUcyQkt2STY0em9FWkhvL1RiUThTTlhpUHdQUkl4a0h5disvQ2JTZ0k2dVJkWGQzdEFkRTNCN3Y3eHVabjNBPT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"Wk5kUUpiHxVp+RNhPM0MOatp8yKk1yNmmn4W4RLmZ0mKgnXKEaEPVoJypqTRJtaYF5xB+laoY2UXvbektV++uNnv/jRc3OHTk9e3X3bEKS0wd1tesm+5G1s72CdVCi6KNThjWH1KeFLUKSPrkxY1fmwg+mzyIglWDbQkkm1ezvgPSxD5otU7jPdHjzG+HYFKlE6a9FNb+LyDbwbI7GhuIJU8+/Ou2BK7MCZGpQX1h8xZnFH2p7Xmb2RR1X7MUYQycXKdrNhxaWmLCWtHcABmb4pHi6wK7oeL0KItoT79FGlmvY3Ns0kOyLHkUGKC8beaLzp65kW+Flcpq0WqysHRcg==\"/>\n\t</CustOpts>\n</request>")).asXml
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100001\" sa=\"A100001\" opr=\"rsadecrypt\" tkntype=\"soft\" mask=\"no\" keytype=\"rsa2048hybd\" lk=\"24820652-1588-5bde-aaca-8cec9ff7cc2c\" ts=\"2023-08-01T16:46:30.684\" txn=\"08e6876e-6733-433a-b814-a2423b7346c0\" xmlns=\"http://www.cdac.in/cryptoservice\" xmlns:ns2=\"http://www.cdac.in/pii\">\n\t<data>TWpBeU15MHdPQzB3TVZReE5qbzBOam96TUM0Mk9EU0JjSUg3SzlmNW1TdzBZRWI3U05DVEVtaTB6K2Rwb3JnaWVjdzhGY0xSdjdrMExZaVo3YUlONjhPOGt6Q0FQVkZYVC9rd1g0Njhia2FPaHVvVXVxckFDcG1SYUwrMDg3cHVYTld2YTNXbVhXUlZoWG1jZHdObEhxZ0V0OXJ0K1E0YVc5Y1J3Z09kbGVwcDVodzdFYnNmMlo0SlJEeEJ1d2ZtYU5KKy9mZ2E1TVdwTjJyQkR0QlpSaDNtN1lDZWFheG1zbTd2SGdlRjBnUS9Keit4eWtjU0VaOGtIdlNqRDhYbnRnbWtRK3JUV25GNVVvbzhEUmNSRFpRNFRYb2dpY3MwME53VDIrU1JIVUhSMkRTSkp6aENYUGlSVWFXYWNRTU9KVERUNXk0VDR0allTd2JIN054YUcyQkt2STY0em9FWkhvL1RiUThTTlhpUHdQUkl4a0h5disvQ2JTZ0k2dVJkWGQzdEFkRTNCN3Y3eHVabjNBPT0=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"sessionKey\" value=\"Wk5kUUpiHxVp+RNhPM0MOatp8yKk1yNmmn4W4RLmZ0mKgnXKEaEPVoJypqTRJtaYF5xB+laoY2UXvbektV++uNnv/jRc3OHTk9e3X3bEKS0wd1tesm+5G1s72CdVCi6KNThjWH1KeFLUKSPrkxY1fmwg+mzyIglWDbQkkm1ezvgPSxD5otU7jPdHjzG+HYFKlE6a9FNb+LyDbwbI7GhuIJU8+/Ou2BK7MCZGpQX1h8xZnFH2p7Xmb2RR1X7MUYQycXKdrNhxaWmLCWtHcABmb4pHi6wK7oeL0KItoT79FGlmvY3Ns0kOyLHkUGKC8beaLzp65kW+Flcpq0WqysHRcg==\"/>\n\t</CustOpts>\n</request>")).asXml
    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(50) during (60 seconds)
    ).protocols(httpProtocol))
}

