package org.scala.exercises.exercise3.concrete

import org.scala.exercises.exercise3.Vehicle
import org.scala.exercises.exercise3.GroundVehicle

case class Bus(name: String, maxSpeed: Double) extends GroundVehicle (name, maxSpeed)