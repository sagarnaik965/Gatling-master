package Tripura
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt

class CryptoTriDec extends Simulation {
  val httpProtocol = http
    .baseUrl("https://172.16.1.68:8080/crypto/")

  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Crypto Decrypt For TRIPURA")
      .post("/rsa2048decrypt")
      .header("Content-Type", "application/xml")
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<request ac=\"A100002\" sa=\"A100002\" opr=\"rsa2048decrypt\" tkntype=\"hard\" keytype=\"rsa2048\" keyidentifier=\"258ea3688244a957e36c55b9e7487f2e5e8c43b1014451912a7bd78e8c6001ed\" lk=\"819ca50a-b338-4c61-adde-3e0e057b004d\" ts=\"2025-06-17T12:03:09.709\" txn=\"1a25c191-fcda-4ad2-ab3a-ec1d375f2327\" mac=\"BC-F4-D4-B6-5E-F1\" ip=\"10.210.5.135\" xmlns=\"http://www.cdac.in/cryptoservice\" xmlns:ns2=\"http://www.cdac.in/pii\">\n\t<data>HGim6CNGqvdYeP/u4ddHF3H3PGPQmFVf6XLjpFPJvwR/9p/ifsk7AFos+ZJmwb4p0a8Jmfp1uXKSHegyZpdhZYW7mXHhHQmKoCU5dz7tgkQO7j69M/etJSD4fhSGvKO35XrL3FML3up2+ElvOmElKM2SFV3K5RHR2NOBRIqlLyU=</data>\n\t<CustOpts>\n\t\t<ParaName name=\"pin\" value=\"NO\"/>\n\t</CustOpts>\n</request>")).asXml
    )

  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(10) during (10 seconds)
      //      rampConcurrentUsers(400).to(500).during(600 seconds)
    ).protocols(httpProtocol))
}

