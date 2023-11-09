import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class Example5 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  val scn = scenario("シナリオ例 5")
    .exec(
      http("GETの例")
        .get("/path")
        .queryParam("key", "value") // クエリパラメーターの指定
        .header("key", "value")
    )
    .exec(
      http("POSTの例1")
        .post("/path")
        .body(StringBody("""{"name": "taro", "age": 10}""")) // リクエストボディの指定
        .asJson // accept,content-type ヘッダーに application/json を付与
    )
    .exec(
      http("POSTの例2")
        .post("/path")
        .formParam("key", "value") // フォームデータの指定
        .asFormUrlEncoded // content-type ヘッダーに application/x-www-form-urlencoded を付与
    )
    .exec(
      http("sessionの値を展開する例1")
        .post("/path")
        .formParam("key", "#{session-key-here}")
        .asFormUrlEncoded
    )
    .exec(
      http("sessionの値を展開する例2")
        .post("/path")
        .formParam("key", session => { s"${session("session-key-here")}" })
        .asFormUrlEncoded
    )

  setUp(
    scn
      .inject(nothingFor(10.seconds))
      .protocols(httpProtocol)
  )
}
