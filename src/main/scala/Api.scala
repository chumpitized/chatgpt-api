package chatgpt.api

import cats.effect._
import org.http4s._ 
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._

trait ChatGPTRoutes[F[_]] {
  val routes: HttpRoutes[F]
}

object ChatGPTRoutes {
  def build[F[_]: Async]: ChatGPTRoutes[F] = {
    new ChatGPTRoutes[F] {
      val dsl = new Http4sDsl[F] {}
      import dsl._  

      val routes = HttpRoutes.of[F] {
        case GET -> Root / "ping" =>
            Ok(s"pong")
      }
    }
  }
}
