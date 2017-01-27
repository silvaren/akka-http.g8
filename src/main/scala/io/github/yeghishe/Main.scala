package io.github.yeghishe

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives

import akka.event.LoggingAdapter

import scala.concurrent.ExecutionContext

object Main extends App with Config with Services {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  override protected def log = Logging(system, "service")
  override protected def executor = system.dispatcher

  Http().bindAndHandle(routes, httpConfig.interface, httpConfig.port)
}

trait Services extends StatusService {
  import Directives._

  private val apiVersion = "v1"
  private val allRoutes = Map(
    "status" -> super[StatusService].routes
  )

  protected override val routes = pathPrefix(apiVersion) {
    allRoutes.map {
      case (k, v) => path(k)(v)
    } reduce (_ ~ _)
  }
}

