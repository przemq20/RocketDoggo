package dogApi
import com.typesafe.config.{ Config, ConfigFactory }

import scala.jdk.CollectionConverters._

object ConfigGetter {
  val config: Config = ConfigFactory.load()

  val theDogApiToken: String = config
    .getConfig("RocketDoggo.TheDogApi.credentials")
    .getString("token")

  val theDogApiUrl: String = config
    .getConfig("RocketDoggo.TheDogApi.environment")
    .getString("url")

//  val dogCeoApiToken = config
//    .getConfig("RocketDoggo.DogCeo.credentials")
//    .getString("token")

  val dogCeoCoreUrl: String = config
    .getConfig("RocketDoggo.DogCeo.environment")
    .getString("url")

  val dogCeoEndpoint: String = config
    .getConfig("RocketDoggo.DogCeo.environment")
    .getString("randomImage")

  val dogCeoBreeds: List[String] = config
    .getConfig("RocketDoggo.DogCeo.environment")
    .getList("breeds")
    .unwrapped()
    .asScala
    .map(_.toString)
    .toList
}
