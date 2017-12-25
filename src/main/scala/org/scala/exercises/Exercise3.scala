/*
 * Copyright (c) 2015 Daniel Higuero.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scala.exercises

import org.scala.exercises.exercise3.concrete.Car
import org.scala.exercises.exercise3.concrete.Plane
import org.scala.exercises.exercise3.GroundVehicle
import org.scala.exercises.exercise3.Vehicle
import org.scala.exercises.exercise3.GroundVehicle
import org.scala.exercises.exercise3.AirVehicle
import org.scala.exercises.exercise3.concrete.Bus
import org.scala.exercises.exercise3.concrete.Helicopter



object Exercise3{

  def main( args: Array[String]) : Unit ={
    val ex3 = new Exercise3()
    
    val vehicleList = List.apply[Vehicle]( new Car("Fiat", 150), new Plane("Airbus", 550, 700), new Bus("Iveco", 130), new Helicopter("Tomcat",230, 300) )
        
    println(ex3.filterGroundVehicles(vehicleList))
    
    println("average : " + ex3.getAverageMaxSpeed(vehicleList) )
  }
  
}

/**
 * Class that provides the implementation for the solution of the exercise 3.
 */
class Exercise3 {
  
  def filterGroundVehicles( vehicles: List[Vehicle] ): List[GroundVehicle] = {
    vehicles.foldLeft(List.empty[GroundVehicle])((a,b) => {
      b match {
        case (x:GroundVehicle) => a.++(List(x)); 
        case _ => a
      }
    })    
  }
  
  def getAverageMaxSpeed( vehicles: List[Vehicle] ): Double = {
    
    val airVehicleList = vehicles
          .filter( (a) => a.isInstanceOf[AirVehicle])
          .map( (a) => {a.asInstanceOf[AirVehicle].airSpeed})
          
          
    airVehicleList.reduce( (a,b) => {a+b}) ./(airVehicleList.size)

  }
}
