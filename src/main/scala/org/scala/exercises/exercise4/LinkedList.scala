package org.scala.exercises.exercise4

import scala.annotation.tailrec

/**
 * LinkedList objects are built from right to left (first we have a tail and then we have the objects)
 * 
 * A esta estructura de datos le falta un index para poder acceder a los datos rápidamente, por ello es normal
 * que no sea la estructura más óptima para leer datos.
 * */
class LinkedList[+T] extends ILinkedList[T] {
  
  def head(): Option[T] = {get(size) }
  def last(): Option[T] = { get(1) }
  def isEmpty(): Boolean = { size == 0 }
  def get(index: Int): Option[T] = {
    this match {
      case Node(elem, tail: LinkedList[T]) => {
        if(size==index) {
            Option(elem.asInstanceOf[T])
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
  /**
   * Altera el orden de la colección
   * */
  @tailrec
  final def foldLeft[U](accumulator: U )( f: (U,T)=> U ): U = {
    this match{
      case EmptyNode => accumulator    
      case Node(head: T, tail: LinkedList[T]) => {
        val current = f(accumulator,head)
        tail.foldLeft(current)(f)
      }
    }
  }
  
  def reverse(): LinkedList[T] = {
    foldLeft(LinkedList[T]())((b,a) => {Node(a,b)})
  }
  
  def foldRight[U](accumulator: U)( f: (T,U) => U): U = {
    reverse()
    .foldLeft(accumulator)( (accum, element) => { f(element,accum)} )
  }
  
  /**
   * Se pasa como parámetro una función que devuelva o verdadero o falso.
   * 
   * Utilizar foldRight es por motivo de mantener el orden de la colección.
   * 
   * */
  def filter(f: (T) => Boolean) : LinkedList[T] = {
    foldRight(LinkedList[T]())( (a: T, accum) => {
      if(f(a) == true)
        Node(a, accum)
      else
        accum
    })
  }
  
  def map[U](f: (T) => U) : LinkedList[U] = {
    foldRight(LinkedList[U]())({
      (item, acc) => {
        Node(f(item),acc)
      }
    })
  }
  
  /**
   * Función de alto nivel que produce "side-effects" en la LinkedList.
   * 
   * Hay situaciones en las que el cambio de estado es necesario, porque pueden depender de sistemas externos. 
   * Por eso existen
   * */
  def forEach(f: (T) => Unit ): Unit = {
    @tailrec
    def loop(list: LinkedList[T]): Unit = {
      list match {
        case Node(head: T, tail: LinkedList[T]) => {
          f(head)
          loop(tail)
        }
        case EmptyNode => {} 
      }
    }
    loop(this)
  }
  
  
  final def find[T]( f: (T) => Boolean ): Option[T] = {
    @tailrec
    def inner(f: (T) => Boolean): Option[T] ={
      this match {
        case Node (head: T, tail) => {
          if (f(head) == true){
            Some(head)
          }else{
//            tail.find(f)
            inner(f)
          }
        }
        case EmptyNode => None
      }
    }
    inner(f)
  }
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