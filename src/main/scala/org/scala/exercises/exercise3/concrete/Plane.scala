package org.scala.exercises.exercise3.concrete

import org.scala.exercises.exercise3.AirVehicle
import org.scala.exercises.exercise3.Vehicle

case class Plane(name: String, override val airSpeed: Double, override val maxSpeed: Double) extends AirVehicle(name, airSpeed, maxSpeed){
  
  
}