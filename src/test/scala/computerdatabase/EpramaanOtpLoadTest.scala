package computerdatabase

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt

class EpramaanOtpLoadTest extends Simulation {
  val httpProtocol = http
    //    .baseUrl("http://10.10.10.11:80")
    .baseUrl("https://sp.epramaan.in:4002/otpservice/")
  val csvFeeder = csv("/data/otp.csv")
  val scnAdvLoad = scenario("LoadTest")
    .exec(http("LoadTest For Adv Connector ")
      .post("/apiCall")
    )

  val scnEpramaanLoad = scenario("LoadTest")
        .feed(csvFeeder)
    //    .exec(feed(uuidFeeder)) // Feed the UUID generated by uuidFeeder into the session
    .exec(http("LoadTest For Epramaan Otp ")
      .post("/sendOTP")
      .header("Content-Type", "application/json")
      .body(StringBody("{\"dept_id\":\"ASHISH\",\"data\":${data}}")).asJson
    )

  setUp(
    scnEpramaanLoad.inject(
      constantUsersPerSec(1) during (1 seconds)
    ).protocols(httpProtocol))
}