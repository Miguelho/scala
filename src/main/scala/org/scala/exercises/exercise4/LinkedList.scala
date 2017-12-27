package org.scala.exercises.exercise4


class LinkedList[T] {
 
  case class Node[T](value: Option[T], tail: LinkedList[T]) extends LinkedList
  
  case object EmptyNode extends LinkedList[Nothing]
  
}