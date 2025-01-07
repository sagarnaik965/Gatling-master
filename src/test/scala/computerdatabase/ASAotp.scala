package computerdatabase
import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import scala.concurrent.duration.DurationInt
class ASAotp extends Simulation {
  val httpProtocol = http
    .baseUrl("http://10.210.9.97:8888")
  val csvFeeder = csv("data/OneLactxn.csv")
  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
    .exec(http("LoadTest For ASA-IOCL-OTP-Req-With-Stub ")
//      .post("/ASA/otprequest/c13c1433-f138-45cc-b10c-80dac14d6946")
      .post("/otprequest")

      .header("Content-Type", "application/xml")
      //      //----------------------------  XML for ASA-IOCL   ------------------------------------
//      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns5:Otp ts=\"2024-07-30T11:05:08\" txn=\"9ef2cc92-4271-16bd-bb3a-d104153111a2\" ver=\"2.5\" sa=\"TEST000001\" ac=\"TEST000001\" uid=\"344366652812\" lk=\"MNnbNgo4SYmgiqAGdC8ZoN5XToW-B9YAEGN7o3Oj294hFWx5LapAqD8\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\"><ns5:Opts ch=\"00\"/></ns5:Otp>")).asXml
      .body(StringBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<ns5:Otp ts=\"2025-04-02T11:05:08\" txn=\"${txn}\" ver=\"2.5\" sa=\"TEST000001\" ac=\"TEST000001\" uid=\"216461183257\" lk=\"MNnbNgo4SYmgiqAGdC8ZoN5XToW-B9YAEGN7o3Oj294hFWx5LapAqD8\" type=\"A\" xmlns:ns6=\"http://www.cdac.in/cryptoservice\" xmlns:ns5=\"http://www.cdac.in/OTP_v1\" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns4=\"http://www.uidai.gov.in/authentication/uid-auth-request-data/2.0\" xmlns:ns3=\"http://www.uidai.gov.in/authentication/uid-auth-request/2.0\">\n\t<ns5:Opts ch=\"00\"/>\n</ns5:Otp>")).asXml

    )
  setUp(
    scnAdvLoad.inject(
//      constantUsersPerSec(10) during (1 seconds)
           rampConcurrentUsers(100).to(110).during(100)
    ).protocols(httpProtocol))

}