 scalaVersion in ThisBuild := "2.13.10"

 name := "scala-chatgpt-api"

 version := "0.1"

 val catsVersion                     = "2.8.0"
 val catsEffectVersion               = "3.3.14"
 val http4sVersion                   = "0.23.18"
 val circeVersion                    = "0.14.3"
 val pureConfigVersion               = "0.17.2"
 val specs2Version                   = "4.13.0"

libraryDependencies ++= Seq(
   "org.http4s"            %% "http4s-dsl"            % http4sVersion,
   "org.http4s"            %% "http4s-ember-server"   % http4sVersion,
   "org.http4s"            %% "http4s-ember-client"   % http4sVersion,
   "org.http4s"            %% "http4s-circe"          % http4sVersion,
   "io.circe"              %% "circe-generic"         % circeVersion,
   "org.typelevel"         %% "cats-core"             % catsVersion,
   "org.typelevel"         %% "cats-effect"           % catsEffectVersion,
   "com.github.pureconfig" %% "pureconfig"            % pureConfigVersion,
   "com.github.pureconfig" %% "pureconfig-http4s"     % pureConfigVersion,
   "org.specs2"            %% "specs2-core"           % specs2Version % Test,
   "org.specs2"            %% "specs2-matcher"        % specs2Version % Test,
   "org.specs2"            %% "specs2-scalacheck"     % specs2Version % Test
 )