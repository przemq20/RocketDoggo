package dogApi

import scalaj.http.Http
import spray.json.DefaultJsonProtocol.StringJsonFormat
import spray.json._

import scala.util.Random

class DogCeo extends PhotoApi {
  val url:      String       = ConfigGetter.dogCeoCoreUrl
  val endpoint: String       = ConfigGetter.dogCeoEndpoint
  val breeds:   List[String] = ConfigGetter.dogCeoBreeds
  val random:   Random       = new Random()

  def getPhotoUrl: String = {
    val randomBreed = breeds(random.nextInt(breeds.size))
    val fullUrl     = s"${url}${randomBreed}${endpoint}"
    val request = Http(fullUrl)
      .header("Content-type", "application/json")
      .header("Charset", "UTF-8")

    scribe.info(s"$request")

    val response = request.asString.body
    scribe.info(response)

    val result = response.parseJson.asJsObject.fields.get("message") match {
      case Some(url) => url.convertTo[String]
      case None      => ""
    }
    result
  }

}
