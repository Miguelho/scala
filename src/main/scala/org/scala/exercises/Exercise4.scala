package org.scala.exercises

import org.scala.exercises.exercise4.LinkedList2

object Exercise4 {
  
  def main( args : Array[String] ) : Unit = {
    val linkedList = new LinkedList2[String]  
    
    linkedList.add("Hola")
    println("Head: " + linkedList.head)
    linkedList.add("Que tal")
    println("Tail: " + linkedList.tail)
    linkedList.add("Adios")
    
    print("Value at node 1", linkedList.get(1))
    
    
    
//    linkedList.list
  }
}

class Exercise4 {
  
}