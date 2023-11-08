import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.*

class Example5 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  val scn = scenario("シナリオ例 5")
    .exec(
      http("GET リクエスト")
        .get("/path")
        .header("key", "value")
    )
    .exec(
      http("POST リクエスト")
        .post("/path")
        .header("key", "value")
        .asJson // 各種ヘッダーが付与される
    )
    .exec(
      http("PUT リクエスト")
        .put("/path")
        .header("key", "value")
        .formParam("key", "value")
        .asFormUrlEncoded // 各種ヘッダーが付与される
    )
    .exec(
      http("DELETE リクエスト")
        .delete("/path")
    )

}
