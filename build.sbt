// === scala settings ===

inThisBuild(
  List(
    scalaVersion := "2.13.12",
    scalacOptions ++= List(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:postfixOps"
    ),
    scalafmtOnCompile := true
  )
)

// === project setting ===

lazy val root = (project in file("."))
  .settings(
    name := "scala-template",
    publish / skip := true
  )
  .aggregate(gatling)

lazy val gatlingVersion = "3.9.5"

lazy val gatling = (project in file("gatling"))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "subProject",
    libraryDependencies ++= Seq(
      "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test",
      "io.gatling" % "gatling-test-framework" % gatlingVersion % "test"
    )
  )
