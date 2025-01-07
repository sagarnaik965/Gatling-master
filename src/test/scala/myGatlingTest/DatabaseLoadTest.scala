//import io.gatling.core.Predef._
//import io.gatling.jdbc.Predef._
//import scala.concurrent.duration._
//
//class DatabaseLoadTest extends Simulation {
//
//  // Define PostgreSQL JDBC connection parameters
//  val jdbcFeeder = jdbcFeeder(
//    url = "jdbc:postgresql://10.10.10.11:8088/Loadtest", // Replace with your DB URL
//    username = "admin", // Replace with your DB username
//    password = "root", // Replace with your DB password
//    driver = "org.postgresql.Driver"
//  )
//
//  // Define the SQL query
//  val sqlQuery = "SELECT * FROM your_table WHERE column_name = ?"
//
//  // Feeder for dynamic query parameters (Optional)
//  val queryParamFeeder = csv("query_params.csv").circular // Provide CSV for query parameters
//
//  // Define the scenario
//  val scn = scenario("Database Load Test")
//    .feed(queryParamFeeder) // Feeds data for query parameters
//    .exec(
//      jdbc("Execute Query")
//        .select(sqlQuery) // The query to execute
//        .params("${column_name}") // Dynamically set query parameter
//    )
//    .pause(1.second) // Pause between requests
//
//  // Load Simulation Configuration
//  setUp(
//    scn.inject(
//      rampUsers(10).during(10.seconds) // Gradually inject 10 users over 10 seconds
//    )
//  ).protocols(jdbcFeeder)
//}
