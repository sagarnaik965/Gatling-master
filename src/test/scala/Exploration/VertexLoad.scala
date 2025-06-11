package Exploration

import io.gatling.core.Predef.{constantUsersPerSec, _}
import io.gatling.http.Predef.http

import scala.concurrent.duration.DurationInt
class VertexLoad extends Simulation {
  val httpProtocol = http
    .baseUrl("http://10.210.9.97:8888")
  //  val csvFeeder = csv("data/dummy10k.csv")

  val scnAdvLoad = scenario("LoadTest for Actor")
    //    .feed(csvFeeder)
    .exec(http("LoadTest For Adv Actor  ")
      .get("/?name=sagarVertex")
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
      constantUsersPerSec(1000) during (30 seconds)
//              constantUsersPerSec(10) during (1 seconds)
    ).protocols(httpProtocol))
}




