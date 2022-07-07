package com.pramod.services.restapi.converter

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.pramod.services.restapi.model.{FlightException, FlightInformation}
import spray.json.DefaultJsonProtocol

object FlightResponseConverter extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val flightInformation = jsonFormat2(FlightInformation)
  implicit val flightException = jsonFormat2(FlightException)
}