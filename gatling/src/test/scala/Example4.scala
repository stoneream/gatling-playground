import io.gatling.core.Predef.*
import io.gatling.http.Predef.*

import scala.concurrent.duration.*

class Example4 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  val scn = scenario("シナリオ例 4")
    .exec(
      http("リクエスト")
        .get("/me")
        .check(status.is(200))
        .check(jsonPath("$.username").ofType[String])
        .check(jsonPath("$.username").saveAs("username"))
    )

}
