package com.myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class ActorLoad extends Simulation {
  val httpProtocol = http
    .baseUrl("http://10.210.9.97:8080")
//  val csvFeeder = csv("data/dummy10k.csv")

  val scnAdvLoad = scenario("LoadTest for Actor")
//    .feed(csvFeeder)
    .exec(http("LoadTest For Adv Actor  ")
      .get("/get")
      //      .header("Content-Type", "text/plain")
//      .body(StringBody("""${aadhaar},${gender}"""))
      //      .check(bodyString.saveAs("responseBody"))

    )
  //    .exec(session => {
  //      scala.reflect.io.File("E://refenceId.csv").appendAll(session("responseBody").as[String] + "\n")
  //      session
  //    }
  //    )




  setUp(
    scnAdvLoad.inject(
      constantUsersPerSec(500) during (30 seconds)
//              constantUsersPerSec(10) during (1 0seconds)
    ).protocols(httpProtocol))
}




