package Bot

import akka.NotUsed
import akka.actor.{ ActorRef, ActorSystem }
import akka.stream.scaladsl.{ Flow, Sink, Source }
import akka.stream.{ Materializer, OverflowStrategy }
import dogApi.DogCeo
import rocket.RCUtils

import scala.concurrent.ExecutionContextExecutor

class AvatarBot {
  def run(): Unit = {
    implicit val system:       ActorSystem              = ActorSystem("reader")
    implicit val materializer: Materializer.type        = Materializer
    implicit val ec:           ExecutionContextExecutor = system.dispatcher

    val streamBotFlow =
      Flow[Tick]
        .via(changeAvatarTick())
    val source = Source.actorRef(10, OverflowStrategy.dropHead)
    val ref: ActorRef = streamBotFlow.to(Sink.ignore).runWith(source)

    val schedules = List("EveryHour")
    Scheduler.schedule(schedules, ref, Tick)
  }

  def changeAvatarTick(): Flow[Tick, Tick, NotUsed] = Flow[Tick].map { tick =>
    changeAvatar
    tick
  }

  def changeAvatar: String = {
    //    val photoApi = new PhotoApi
    //    val photo = photoApi.getPhotoUrl()
    val dogCeo = new DogCeo
    val photo  = dogCeo.getPhotoUrl
    RCUtils.setDoggoAvatarOnRocket(photo)
    photo
  }
}
