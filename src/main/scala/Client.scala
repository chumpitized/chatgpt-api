package chatgpt

import cats.effect._
import org.http4s._ 
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.circe.CirceEntityDecoder._
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.client.Client
import org.http4s.headers.Authorization
import chatgpt.config.ApiKey

trait ChatClient[F[_]] {
  def completion(chat: Chat): F[CompletionResponse]
}

object ChatClient {

  def build[F[_]: Async](client: Client[F], apiKey: ApiKey): ChatClient[F] =
    new ChatClient[F] {
      def completion(chat: Chat): F[CompletionResponse] = {
        val payload = ModelAndMsgs(messages = List(chatgpt.Message(content = chat.message)))
        
        val req = Request[F](
          method = Method.POST,
          uri = uri"https://api.openai.com/v1/chat/completions",
          headers = Headers.of(
            Authorization(Credentials.Token(AuthScheme.Bearer, apiKey.key))
        )).withEntity(
          payload
        )
        
        client.expect[CompletionResponse](req)

      }

    }

}
