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

import scala.collection.Map

object Exercise1 {
  
  def main(args: Array[String]) : Unit = {
    val ex1 = new Exercise1();
    
    val list = List(1,2,3,4,5)
    
    System.out.println("Second implementation using foldleft")
    System.out.print(ex1.firstImplementation(list, transformToString))
    
    System.out.print(ex1.firstImplementation(list, square))

    
    System.out.println("\nSecond implementation using foreach")
    System.out.print(ex1.secondImplementation(list, transformToString))
    
    System.out.print(ex1.secondImplementation(list, square))
  }
  
  
  def transformToString(number: Int): String = {
    String.valueOf(number)
  }
  
  def square(number:Int): Int ={
    number*number
  }
  
}

/**
 * Class that provides the implementation for the solution of the exercise 1.
 */
class Exercise1 {
  
  def firstImplementation [A,B](list: List[A], f: A => B) : Map[A,B] = {
    list.foldLeft(Map.empty[A,B])( (accum, current) =>  accum + (current -> f(current) ) )
  }
  
  def secondImplementation [A,B](list: List[A], f: A=> B) : Map[A,B] ={
   //Se define un mapa
   var map = Map.empty[A,B]
   
   //Se aplica la funcion f a cada value,
   //value es un placeholder que simula un parámetro, 
   //por eso no se encuentra definido en ningún sitio más que en la propia función
   list.foreach( value => { map +=  value -> f(value) })
   
   //En Scala, la última línea se interpreta como return
   map
  }


}
