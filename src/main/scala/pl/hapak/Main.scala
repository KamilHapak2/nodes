package pl.hapak

import java.nio.file.Path

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import net.liftweb.json.{DefaultFormats, Serialization}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object Main {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem = ActorSystem("nodes")
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher


    val route = cors() {
      path("") {
        get {
          val reader = new XlsxReader
          val cells = reader.readCellDetails(Path.of(args.head))
          val mapper = new NodeMapper
          val nodes = mapper.mapNodes(cells)

          implicit val formats: DefaultFormats.type = DefaultFormats
          complete(HttpEntity(ContentTypes.`application/json`, Serialization.writePretty(nodes)))
        }
      }
    }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())

  }
}
