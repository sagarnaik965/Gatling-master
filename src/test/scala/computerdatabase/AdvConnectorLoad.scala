package com.myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class AdvConnectorLoad extends Simulation {
  val httpProtocol = http
//    .baseUrl("http://localhost:8080")
    .baseUrl("http://localhost:8080")
  val csvFeeder = csv("data/Connector11Load.csv")
 // val csvFeeder = csv("data/secure2.csv")

  val scnAdvLoad = scenario("LoadTest")
    .feed(csvFeeder)
//        .exec(session => {
 //           val data = session("aadhaar").as[String]
  //          println("Data from feeder: " + data)
   //         session
    //      })
    .exec(http("LoadTest For Adv Connector ")
//      .post("/struid")
      .post("/getui")
//      .header("Content-Type", "text/plain")
//      .body(StringBody("${aadhaar}"))
      .body(StringBody("{\n    \"aadhaarNum\": \"345871005735\",\n    \"refNum\": \"1263427975869546496\",\n    \"ac\": \"A100001\",\n    \"sa\": \"A100001\",\n    \"lk\": \"ddb81abd-23d7-4cf6-ab5e-29d01bcc2950\",\n    \"keytype\": \"aes\",\n    \"tkntype\": \"soft\",\n    \"url\": \"http://10.210.9.67:8080/vault/\"\n}")).asJson

      //.body(StringBody("""744709211664,MALE"""))
      //      .body(StringBody("""#{aadhaar},MALE"""))
//      .check(bodyString.saveAs("responseBody"))

    )




  setUp(
        scnAdvLoad.inject(
//          constantUsersPerSec(25) during (10 seconds)
            rampConcurrentUsers(100) to 120 during (1 minute)

          //        constantUsersPerSec(700) during (120 seconds)
        ).protocols(httpProtocol)

//    scnAdvLoad.inject(
//	      rampUsers(1000) during (10 seconds),
//      rampUsers(1800) during (10 seconds),
//      rampUsers(2700) during (10 seconds),
//	 rampUsers(3900) during (10 seconds),
//	 rampUsers(5100) during (10 seconds),
//	 rampUsers(6500) during (10 seconds),
//      rampUsers(6800) during (10 seconds),
//        rampUsers(7000) during (10 seconds),
//      constantUsersPerSec(750) during (300 seconds)
////        constantUsersPerSec(700) during (120 seconds)
//    ).protocols(httpProtocol)
      )
}
