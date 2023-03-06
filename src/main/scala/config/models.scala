package chatgpt.config

import scala.concurrent.duration.FiniteDuration

final case class ServiceConfig(
  openai: ApiKey
)

final case class ApiKey(
  key: String
)