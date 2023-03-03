package chatgpt.api

import java.time.LocalDate

import cats.effect._
import org.http4s._
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    buildServer(ChatGPTRoutes.build[IO].routes.orNotFound)
  }

  def buildServer(app: HttpApp[IO]): IO[ExitCode] = 
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(app)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)

}