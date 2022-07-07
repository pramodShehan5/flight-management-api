package com.pramod.services.restapi.converter

import akka.http.scaladsl.marshalling.Marshaller
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import FlightResponseConverter._
import akka.actor.ActorSystem
import akka.http.scaladsl.unmarshalling.Unmarshal
import com.pramod.services.restapi.model.{CommonMessage, FlightException, FlightInformation}
import spray.json._

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.concurrent.{Await, ExecutionContextExecutor}

trait Converter {
  implicit val system: ActorSystem = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val timeout: FiniteDuration = 10.seconds
  def handler(): Marshaller[CommonMessage, HttpResponse] = {
    Marshaller.withFixedContentType[CommonMessage, HttpResponse](ContentTypes.`application/json`) {
      //    message.asInstanceOf match {
      case message: FlightException =>
        HttpResponse(500, entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, "errorMessage"))
      case flightInformation: FlightInformation =>
        HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, CreateFlightToJson.getTemplatesToJson().prettyPrint))
      case message =>
        HttpResponse(500, entity = HttpEntity(ContentTypes.`application/json`, "errorMessage"))
    }
    //  }
  }

  def handlerHttpResponse(): Marshaller[HttpResponse, HttpResponse] = {
    println(s"333333333333333333333333333333333333")
    Marshaller.withFixedContentType[HttpResponse, HttpResponse](ContentTypes.`application/json`) {
//      case message =>
//        HttpResponse(200, entity = HttpEntity(ContentTypes.`application/json`, "errorMessage"))
      case message =>
      message match {
        case HttpResponse(StatusCodes.OK, _, entity, _) =>
          /*val apiResponse =  Unmarshal(entity).to[Passenger]
          Marshaller.withFixedContentType[CommonMessage, HttpResponse](ContentTypes.`application/json`) {
            case messge: Passenger =>
              HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, CreateFlightToJson.getTemplatesToJson().prettyPrint))
            case _ =>
              HttpResponse(500, entity = HttpEntity(ContentTypes.`application/json`, "errorMessage"))
          }*/
        message
        case status@_ =>
          HttpResponse(500, entity = HttpEntity(ContentTypes.`application/json`, "errorMessage"))
      }
    }
  }
}

object CreateFlightToJson {
  def getTemplatesToJson(): JsValue = {
    val templates = FlightInformation(Option(1), Option("test"))
    templates.toJson
  }
}
