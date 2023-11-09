import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class Example2 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  // 最もシンプルな何もしないシナリオの例
  val scn1 = scenario("シナリオ例 1")

  // ホーム画面にアクセス、10秒待ってから、概要画面に遷移
  val scn2 = scenario("シナリオ例 2")
    .exec(http("ホーム").get("/home"))
    .exec(pause(10.seconds))
    .exec(http("概要").get("/about"))

  setUp(
    scn1
      .inject(nothingFor(10.seconds))
      .protocols(httpProtocol)
  )

}
