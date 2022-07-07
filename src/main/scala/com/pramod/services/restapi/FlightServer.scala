package com.pramod.services.restapi

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.{Directives, Route}
import com.pramod.services.restapi.actor.{AirLineActor, FlightActor}
import com.pramod.services.restapi.security.CorsSupport
import com.typesafe.scalalogging.LazyLogging

import scala.io.StdIn

object FlightServer extends FlightActor with AirLineActor with Directives with CorsSupport with LazyLogging {
//  implicit val executionContext = system.dispatcher

  def main(args: Array[String]): Unit = {
    println(s"Starting FlightServer on 8080")
    startServer("0.0.0.0", 8080)
  }

  def startServer(address: String, port: Int): Unit = {
    val route1 = flightRoute() ~ route()
    val routes = Route.seal {
      //      handleErrors {
      route1
      //      }
    }
    Http().bindAndHandle(routes, address, port)
    println(s"Flight server at http://localhost:8080/")
    StdIn.readLine() // let it run until user presses return
    //    bindingFuture
    //      .flatMap(_.unbind()) // trigger unbinding from the port
    //      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
