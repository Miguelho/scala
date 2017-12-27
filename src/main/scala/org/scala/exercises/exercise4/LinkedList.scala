package org.scala.exercises.exercise4

import scala.reflect.ClassTag
import java.lang.ref.ReferenceQueue.Null

class LinkedList[T: ClassTag] {
  
  var size = 0
  
  var h = head()
  
  var t = tail()
  
  def isEmpty : Boolean = size == 0
  
  def head() : AbstractNode = { get(0)}
  
  def tail(): AbstractNode = get(size)
  
  def add(value: T): Unit = {
    
    h = new Node(Option(value), tail(), null)
    
    size += 1
  }

  
  def get(index: Int): AbstractNode= {
   
    if(size == 0) return EmptyNode
    
    if (size > 0 && index > size) throw new IllegalArgumentException

    if(index==0) return h
    
    //Instancia de un option
    var node  =  EmptyNode.asInstanceOf[AbstractNode]
    
    //Se recorre desde el primer elemento, los nodos frontales de los nodos siguientes, hasta alcanzar
    //la posición necesitada - 1
    val list =  List.range(0, index)
    list.foreach((i) => { node = head().asInstanceOf[Node[T]].front })
    
    node
    
  }
  

  
  case class Node[T](value: Option[T] ,back: AbstractNode, front: AbstractNode) extends AbstractNode
  
  object EmptyNode extends AbstractNode
  
  abstract class AbstractNode
}