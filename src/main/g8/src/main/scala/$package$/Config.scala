package $package$

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus
import net.ceedubs.ficus.readers.ArbitraryTypeReader

trait Config {
  import ArbitraryTypeReader._
  import Ficus._

  protected case class HttpConfig(interface: String, port: Int)

  private val config                   = ConfigFactory.load()
  protected val httpConfig: HttpConfig = config.as[HttpConfig]("http")
}

object Config extends Config
