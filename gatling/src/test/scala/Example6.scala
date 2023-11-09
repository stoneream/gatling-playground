import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class Example6 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  val scn1 = scenario("シナリオ1")
    .exec(http("トップ").get("/home"))

  val scn2 = scenario("シナリオ2")
    .exec(http("ホーム").get("/home"))
    .exec(pause(10.seconds))
    .exec(http("概要").get("/about"))

  setUp(
    scn1
      .inject(
        rampUsers(10).during(30.seconds), // 10ユーザーが30秒間、分散してシナリオを実行
        nothingFor(10.seconds), // 10秒一時停止
        rampUsersPerSec(10).to(100).during(60.seconds) // 60秒間で同時シナリオ実行数を10回から100回まで増やす
      )
      .protocols(httpProtocol),
    // シナリオを同時実行することも可能です
    scn2
      .inject(
        rampUsers(10).during(30.seconds), // 10ユーザーが30秒間、分散してシナリオを実行
        nothingFor(10.seconds), // 10秒一時停止
        rampUsersPerSec(10).to(100).during(60.seconds) // 60秒間で同時シナリオ実行数を10回から100回まで増やす
      )
      .protocols(httpProtocol)
  )
}
