package $package$

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpcirce.CirceSupport

import scala.concurrent.ExecutionContext

trait BaseComponent extends Config {
  protected implicit def log: LoggingAdapter
  protected implicit def executor: ExecutionContext
}

trait BaseService extends BaseComponent with CirceSupport {
  protected def routes: Route
}

object Main extends App with Config with Services {
  implicit val system       = ActorSystem()
  implicit val materializer = ActorMaterializer()

  override protected def log      = Logging(system, "service")
  override protected def executor = system.dispatcher

  Http().bindAndHandle(routes, httpConfig.interface, httpConfig.port)
}

trait Services extends StatusService {
  import Directives._

  private val apiVersion = "v1"
  private val allRoutes = Map(
    "status" -> super[StatusService].routes
  )

  protected override val routes: Route = pathPrefix(apiVersion) {
    allRoutes.map {
      case (k, v) => path(k)(v)
    } reduce (_ ~ _)
  }
}
