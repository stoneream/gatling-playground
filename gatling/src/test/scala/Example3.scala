import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class Example3 extends Simulation {
  val httpProtocol = http.baseUrl("http://localhost:9000").userAgentHeader("UserAgentHere")

  val scn = scenario("シナリオ例 3")
    .exec { session =>
      // セッションへ値をセットする
      val newSession1 = session.set("key1", "value")
      val newSession2 = newSession1.setAll(
        ("key2", "value"),
        ("key3", "value")
      )

      newSession2
    }
    .exec { session =>
      // セッションの値を削除する
      val newSession = session.remove("key")

      newSession
    }
    .exec { session =>
      // セッションの値を取得する
      // 値が存在しない場合、型が合わない場合に例外が発生する
      val as = session("key1").as[String]
      // 型が合わない場合に例外が発生する
      val asOption = session("key1").asOption[String]
      // 安全にアクセスできる
      val validated = session("key1").validate[String]
      ???
    }

}
