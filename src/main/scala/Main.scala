import Bot.AvatarBot
import Utils.HttpConnection.createConnection

class Main {
  new Thread {
    override def run(): Unit = {
      (new AvatarBot).run()
    }
  }.start()
  new Thread {
    override def run(): Unit = {
      createConnection
    }
  }.start()

}

object Main extends App {
  val main = new Main
}
