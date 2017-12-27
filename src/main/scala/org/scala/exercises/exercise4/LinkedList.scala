package org.scala.exercises.exercise4

/**
 * LinkedList objects are built from right to left (first we have a tail and then we have the objects
 * */
class LinkedList[+T] extends ILinkedList[T] {
  
  def head(): Option[T] = {get(size) }
  def last(): Option[T] = { get(1) }
  def isEmpty(): Boolean = { size == 0 }
  def get(index: Int): Option[T] = {
    this match {
      case Node(elem: T, tail: LinkedList[T]) => {
        if(size==index) {
            Option(elem)
          }else{
            println("next recursive invocation, exiting " + this)
            tail.get(index)
        }
      }
      
      case _ => { 
        println("Node not found") 
        Option.empty[T]
      }
    }
    
    
    
  }
  def add[T](element: T): LinkedList[T]={
    Node(element, this)
  }
  
  def size(): Int={this.size()}
  
  
}
/**
 * el tamaño es una variable inmutable asociada con cada instancia de Node.
 * 
 * 1 + el valor de la LinkedList antes de añadir el element
 * 
 * */
case class Node[T](element: T, tail: LinkedList[T]) extends LinkedList{
  override val size = 1 + tail.size()
}
  
  /**
   * Valor final de la lista.
   * */
  case object EmptyNode extends LinkedList[Nothing]{
    override val size = 0
  }
  
/**
   * Companion object
   * */
  object LinkedList{
    def apply[T](items: T*): LinkedList[T] = {
      if(items.isEmpty)
        EmptyNode
      else
        Node(items.head, apply(items.tail: _*))
    }
  }

trait ILinkedList[+T] {
  
  def head(): Option[T]
  def last(): Option[T]
  def isEmpty(): Boolean
  def get(index: Int): Option[T]
  def add[T](element: T): LinkedList[T]
  def size(): Int
  
}