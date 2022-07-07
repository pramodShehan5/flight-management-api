package com.pramod.services.restapi.actor

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.RouteDirectives
import akka.util.Timeout
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object AirLineActor {
  def props(routes: RouteDirectives) =
    Props(classOf[FlightActor])
}

trait AirLineActor extends LazyLogging {

  implicit val system: ActorSystem
  implicit val TIMEOUT = Timeout(20 seconds)

  def route(): Route = {
    get {
      path("airlines" / Segment) { id =>
        logger.info(s"Request for airlines: $id")
        complete(Future {
//          FlightInformation(Option(1), Option("airline1"))
          ""
        })
      }
    }
  }
}