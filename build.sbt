 scalaVersion in ThisBuild := "2.13.10"

 name := "scala-chatgpt-api"

 version := "0.1"

 val catsVersion                     = "2.8.0"
 val catsEffectVersion               = "3.3.14"
 val http4sVersion                   = "0.23.18"

libraryDependencies ++= Seq(
   "org.http4s"         %% "http4s-dsl"            % http4sVersion,
   "org.http4s"         %% "http4s-ember-server"   % http4sVersion,
   "org.http4s"         %% "http4s-ember-client"   % http4sVersion,
   "org.typelevel"      %% "cats-core"             % catsVersion,
   "org.typelevel"      %% "cats-effect"           % catsEffectVersion
 )