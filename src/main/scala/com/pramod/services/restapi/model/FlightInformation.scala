package com.pramod.services.restapi.model

case class FlightInformation(flightNo: Option[Int] = None,
                             flightName: Option[String]) extends CommonMessage