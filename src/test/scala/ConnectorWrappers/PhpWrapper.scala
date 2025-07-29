import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import scala.sys.process._
import scala.concurrent.duration._

class PhpWrapper extends Simulation {

  // === Helper method to run the JAR ===
  def runJar(): String = {
    val output = new StringBuilder
    val error = new StringBuilder

    val logger = ProcessLogger(
      (out: String) => output.append(out).append("\n"),
      (err: String) => error.append(err).append("\n")
    )

    // Modify path below
    val exitCode = Process("java -jar C:\\Users\\sagarn\\Downloads\\SecureVaultwrapperJULY2024.jar").!(logger)

    // Return combined output
    s"""
       |Exit Code: $exitCode
       |--- STDOUT ---
       |${output.toString()}
       |--- STDERR ---
       |${error.toString()}
    """.stripMargin
  }

  // === Simulation Scenario ===
  val scn = scenario("Run JAR Per Request")
    .exec { session =>
      val result = runJar()
      println(s"\n==== JAR Output ====\n$result") // Full log including errors
      session
    }

  // === Load Profile ===
  setUp(
    scn.inject(
      atOnceUsers(1) // Adjust based on system capability
    )
  )
}
