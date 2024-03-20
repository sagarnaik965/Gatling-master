package com.myGatlingTest

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class DashBoardLoadTest extends Simulation {
  val httpProtocol = http
//    .baseUrl("https://advservice.epramaan.gov.in/dashboard/")
      .baseUrl("http://10.10.86.8:8080/advload/")
  //  val csvFeeder = csv("data/dummy10k.csv")

  val scnAdvLoad = scenario("LoadTest for Actor")
    .exec(http("LoadTest For Adv Actor  ")
      .get("load")
      .check(bodyString.saveAs("responseBody"))
       )
      .exec(session => {
        val response = session("responseBody").as[String]
        println(s"Counts from Websites .....:  \n$response")
        session
      })

  setUp(
    scnAdvLoad.inject(
                    constantUsersPerSec(5000) during (300 seconds)
    ).protocols(httpProtocol))
}




