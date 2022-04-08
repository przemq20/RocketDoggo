package rocket

import com.typesafe.config.{ Config, ConfigFactory }

object RCEnvironment {
  private final val config: Config = ConfigFactory
    .load()
    .getConfig("RocketDoggo.RocketChat.environment")

  final val HOST:     String = config.getString("host")
  final val API_PATH: String = "/api/v1"

  final val CORE_URL: String = s"$HOST$API_PATH"
  final val SET_AVATAR = s"$CORE_URL/users.setAvatar"
}
