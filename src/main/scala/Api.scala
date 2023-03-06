package chatgpt

import cats.implicits._
import cats.effect._
import org.http4s._ 
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.circe.CirceEntityDecoder._

trait ChatRoutes[F[_]] {
  val routes: HttpRoutes[F]
}

object ChatRoutes {
  def build[F[_]: Async](client: ChatClient[F]): ChatRoutes[F] = {
    new ChatRoutes[F] {
      val dsl = new Http4sDsl[F] {}
      import dsl._  

      val routes = HttpRoutes.of[F] {
        case req @ GET -> Root / "chat" => for {
          chat    <- req.as[Chat]
          reply   <- client.completion(chat)
          choices <- Async[F].fromOption(reply.choices.headOption, new Exception("Something went wrong"))
          resp    <- Ok(choices.message.content)
        } yield resp
      }
    }
  }
}