package org.scala.exercises

import org.scala.exercises.exercise4.LinkedList

object Exercise4 {
  
  def main( args : Array[String] ) : Unit = {
    val emptyList = LinkedList()
    
    println("Is empty? " + emptyList.isEmpty() + "\n")
    
    val lista = LinkedList(1,2,3,4,5)
    
    println(lista.size)
    println("Is empty? " + lista.isEmpty() + "\n")
    
    
    val lista$1 = lista.add(6)
    
    println(lista$1.size)
    
    println(lista$1.get(3))
    println("Head "+lista$1.head())
    println("Last "+lista$1.last())
    
    val sum = lista$1.foldLeft(0)((value: Int, accum) => {value+accum})
    println(sum)
    
    println(lista$1.reverse())
    
    println(lista$1.foldRight(0)( (value: Int, accum) => {value+accum}))
  }
}

class Exercise4 {
  
}