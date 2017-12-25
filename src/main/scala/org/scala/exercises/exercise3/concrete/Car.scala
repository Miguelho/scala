package org.scala.exercises.exercise3.concrete

import org.scala.exercises.exercise3.GroundVehicle
import org.scala.exercises.exercise3.Vehicle

case class Car(name: String, maxSpeed: Double) extends GroundVehicle(name, maxSpeed)