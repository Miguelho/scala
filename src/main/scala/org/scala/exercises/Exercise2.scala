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

object Exercise2{

  def main(args: Array[String]): Unit ={
      val letters: Array[String] = Array("A","B","C","D","E","F","G")
      val Exercise2 = new Exercise2()
      
      val nameLength = 6
      val mailLength = 10
      
      val personList = List.tabulate[Exercise2.Persona](50)( a => {  
          val name = List.range(1,nameLength).map( current => letters(Random.nextInt(current))).mkString 
          val email = List.range(1,mailLength).map( current => if(current != 3) letters(Random.nextInt(letters.size)) else "@").mkString 
            
          new Exercise2.Persona(name, email, (Math.random()*10).asInstanceOf[Int])
    })
    
    print(personList)
   
  }
  
}

/**
 * Class that provides the implementation for the solution of the exercise 2.
 */
class Exercise2 {

  def firstImplementation[A,B]( list : List[A], f: (A) => B): Map[A,B] ={
    Map.empty[A,B]
  }
  
//  def groupByAge(people: List[Exercise2.Persona): Map[Int, List[Exercise2.Persona] = {}
  
  class Persona (name: String, email: String, age: Int) {
    
   override def toString(): String = {
      "Persona [name = " + name  + " email= "+ email + " age= " + age + "] "
    }
  }
}

