package com.pramod.services.restapi.model

case class FlightException(errorMessage: String,
                           errorCode: String) extends CommonMessage
