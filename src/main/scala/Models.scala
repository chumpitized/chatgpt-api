package chatgpt

import io.circe._
import io.circe.generic.semiauto._

final case class Chat(
  message: String
)
object Chat {
  implicit val decoder: Decoder[Chat] = deriveDecoder[Chat]
}

final case class Choice(
  message: Message,
  finish_reason: String,
  index: Int
)
object Choice {
  implicit val decoder: Decoder[Choice] = deriveDecoder[Choice]
}

final case class Usage(
  prompt_tokens: Int,
  completion_tokens: Int,
  total_tokens: Int
)
object Usage {
  implicit val decoder: Decoder[Usage] = deriveDecoder[Usage]
}

final case class CompletionResponse(
  id: String,
  `object`: String,
  created: Int,
  model: String,
  choices: List[Choice]
)
object CompletionResponse {
  implicit val decoder: Decoder[CompletionResponse] = deriveDecoder[CompletionResponse]
}

final case class Message(
  role: String = "user",
  content: String
)
object Message {
  implicit val encoder: Encoder[Message] = deriveEncoder[Message]
  implicit val decoder: Decoder[Message] = deriveDecoder[Message]
}

final case class ModelAndMsgs(
  model: String = "gpt-3.5-turbo",
  messages: List[Message]
)
object ModelAndMsgs {
  implicit val decoder: Encoder[ModelAndMsgs] = deriveEncoder[ModelAndMsgs]
}