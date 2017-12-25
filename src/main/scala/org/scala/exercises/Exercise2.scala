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

import scala.util.Random
import scala.collection.Map


object Exercise2{

  def main(args: Array[String]): Unit ={
      val letters: Array[String] = Array("A","B","C","D","E","F","G")
      val exercise2 = new Exercise2()
      
      val nameLength = 6
      val mailLength = 10
      
      val personList = List.tabulate[exercise2.Persona](50)( a => {  
          val name = List.range(1,nameLength).map( current => letters(Random.nextInt(current))).mkString 
          val email = List.range(1,mailLength).map( current => if(current != 3) letters(Random.nextInt(letters.size)) else "@").mkString 
            
          new exercise2.Persona(name, email, (Math.random()*4).asInstanceOf[Int])
      })
      
    println("**** Input data: ****")
      
    println(personList)
    
    println("*********************")
    
    val resultBuiltInGroupBy = exercise2.groupByAge(personList) 
    
    println(resultBuiltInGroupBy + "\n")
    
    val resultFirstImpl = exercise2.implementation(personList)
    
    println(resultFirstImpl + "\n")
    
    val resultSecondImpl = exercise2.implementation$2(personList)
    
    println(resultSecondImpl + "\n")
    }
 }

/**
 * Class that provides the implementation for the solution of the exercise 2.
 */
class Exercise2 {

  def groupByAge( personList: List[Persona]): Map[Int,List[Persona]] ={
      personList.groupBy( (a) => a.age ).toMap
  }
  /**
   * foldLeft no garantiza el orden en el que se aplican las funciones
   * */
  def implementation( list : List[Persona] ) : Map[Int, List[Persona]] = {
    list.map( (persona) => { ( persona.age, persona) })
              .foldLeft( Map.empty[Int, List[Persona]] )( (mapa, elemento) => { mapa + (elemento._1 -> (mapa.getOrElse(elemento._1, List.empty[Persona])).++ (List.apply(elemento._2))) } )
  }
  
  
  def implementation$2 ( list : List[Persona] ) : Map [Int, List[Persona]] ={
    var map :scala.collection.mutable.Map[Int, List[Persona]] = scala.collection.mutable.Map.empty[Int, List[Persona]]
    
    list.map( (persona) => { ( persona.age, persona) })
        .map( (elemento) => { map += (elemento._1 -> (map.getOrElse(elemento._1, List.empty[Persona])).++ (List.apply(elemento._2))) } )
    map
  }
  
  
  case class Persona (name: String, email: String, age: Int) {
    
   override def toString(): String = {
      "Persona [name = " + name  + " email= "+ email + " age= " + age + "] "
    }
  }
}

