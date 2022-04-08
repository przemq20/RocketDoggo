package rocket

import com.typesafe.config.{ Config, ConfigFactory }

object Credentials {
  final val configFile: String = scala.util.Properties.envOrElse("CREDENTIALS", "test.conf")
  private final val config: Config = ConfigFactory
    .load()
    .getConfig("RocketDoggo.RocketChat.credentials")

  final val TOKEN:   String = config.getString("token")
  final val USER_ID: String = config.getString("user_id")
}
