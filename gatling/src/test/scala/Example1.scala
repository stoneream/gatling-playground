// DSLのインポート
import io.gatling.core.Predef._
import io.gatling.http.Predef._

// 負荷をかける秒数の指定をする際に便利、予めインポートしておくと良い
import scala.concurrent.duration._

class Example1 extends Simulation {
  // 1. HTTPの設定
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  // 2. シナリオの記述
  val scn = scenario("シナリオ名").exec(
    http("リクエスト名").get("/path").header("key", "value")
  )

  // 3. シナリオの実行方法(負荷のかけ方)
  setUp(
    scn
      .inject(
        // 10ユーザーが30秒間、バラバラのタイミングでシナリオを実行
        rampUsers(10).during(30.seconds)
      )
      .protocols(httpProtocol)
  )
}
