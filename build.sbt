name := "flight-management-api-service"
description := "Provide flight api"
mainClass in Compile := Some("com.pramod.services.restapi.WebServer")
packageName in Docker := "pramodshehan/flight-service"
dockerExposedPorts := Seq(5000)

enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-language:postfixOps")

libraryDependencies ++= {
  val akkaHttpVersion = "10.1.11"
  val sprayVersion = "1.3.2"
  val akkaVersion = "2.6.4"

  Seq(
    "commons-io" % "commons-io" % "2.5",
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.3"
  )
}

parallelExecution in IntegrationTest := false

Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat
Test / fork := true