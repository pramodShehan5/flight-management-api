package com.pramod.services.restapi.actor

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives.{complete, get, path, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.pramod.services.restapi.converter.Converter
import com.pramod.services.restapi.model.{CommonMessage, FlightException, FlightInformation}
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object FlightActor {
  def props() = Props(classOf[FlightActor])
}

trait FlightActor extends Converter with LazyLogging {

  implicit val system: ActorSystem

  def flightRoute(): Route = {
    println("2222222222222222222222222222222")
    get {
      path("flights" / Segment) { id =>
        println("111111111111111111111111111111")
        logger.info(s"Request for interchange_id: $id")
        implicit val handlers = handlerHttpResponse
        complete(clientRestAPI())
      }
    }
  }

  def client(isExceptionReturn: Boolean): Future[CommonMessage] = {
    Thread.sleep(2000)
    if (!isExceptionReturn) {
      Future {
        FlightInformation(Option(1), Option("flight1"))
      }
    } else {
      Future {
        FlightException("error", "error")
      }
    }
  }

  def clientRestAPI(): Future[HttpResponse] = {
    println("Before Thread 111111111111111111")
    Thread.sleep(2000)
    println("After Thread 111111111111111111")

    val request = HttpRequest(HttpMethods.GET, "https://gorest.co.in/public/v1/users")
    val aa = Http().singleRequest(request)
    println("finish req/res 111111111111111111")
    aa
  }
}