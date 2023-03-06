package chatgpt

import java.time.LocalDate

import cats.effect._
import org.http4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.client.Client
import com.comcast.ip4s._
import org.http4s.server.Server
import chatgpt.config.ApiKey
import cats.effect.unsafe.implicits.global
import chatgpt.config.ConfigService

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = (for {
    config <- Resource.eval(ConfigService.getConfig[IO])
    client <- buildClient
    chatGPTClient = ChatClient.build[IO](client, config.openai)
    httpApp = ChatRoutes.build[IO](chatGPTClient).routes.orNotFound
    server <- buildServer(httpApp)
  } yield server)
      .use(_ => IO.never)
      .as(ExitCode.Success)

  def buildServer(app: HttpApp[IO]): Resource[IO, Server] = 
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(app)
      .build

  def buildClient: Resource[IO, Client[IO]] = 
    EmberClientBuilder
    .default[IO]
    .build

}