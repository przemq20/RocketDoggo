package rocket

import scalaj.http.{ Http, HttpOptions }

object RCUtils {

  def makeMessageString(content: String): String = {
    val newlineChar    = "\\n"
    val whitespaceChar = "\u2001"
    val rawContent = content
      .replace("\n", newlineChar)
      .replace("\t", whitespaceChar * 3)
      .replace("\"", "")
      .replace("{", "")
      .replace("}", "")

    s"""{"avatarUrl": "$rawContent"}"""
  }

  def setDoggoAvatarOnRocket(photoUrl: String): Unit = {
    val data = makeMessageString(photoUrl)
    scribe.info(s"""Sending
                   |$data
                   |""".stripMargin)
    try {
      val request = Http(RCEnvironment.SET_AVATAR)
        .postData(data)
        .header("X-Auth-Token", Credentials.TOKEN)
        .header("X-User-Id", Credentials.USER_ID)
        .header("Content-type", "application/json")
        .header("Charset", "UTF-8")

      scribe.info(s"$request")
      val response = request.option(HttpOptions.readTimeout(10000)).asString
      scribe.info(s"$response")
    } catch {
      case e: Throwable => scribe.error(s"Error: ${e.getLocalizedMessage}")
    }
  }
}
