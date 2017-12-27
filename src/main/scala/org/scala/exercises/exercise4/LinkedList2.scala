package org.scala.exercises.exercise4



import scala.reflect.ClassTag
import java.text.Normalizer.Form

class LinkedList2[T: ClassTag] {
  var size = 0
  var head = EmptyNode.asInstanceOf[AbstractNode]
  var tail = EmptyNode.asInstanceOf[AbstractNode]
  
  def isEmpty :Boolean =  {size == 0}
  def add(value: T): Unit= {
    if(size == 0){ 
      val node = Node(EmptyNode, Option(value), EmptyNode)
      head = node
      tail = node
      size += 1
    }else{
//      head = tail
      var tmp = Node( tail,Option(value), EmptyNode)
//      tail.asInstanceOf[Node[T]].front = tmp
      size+=1
    }
  }
  
  def get(index: Int) : Node[T] = {
    //
    var node = head
    List.range(0, index)
      .foreach( (i) => {
                        println(i + " " + node.asInstanceOf[Node[T]])
                        
                        node = node.asInstanceOf[Node[T]].front
                        } )
    println("....")
    println(node)
    println("....")
    node.asInstanceOf[Node[T]]
  }
  
  def list : Unit = {
    var offset = head.asInstanceOf[Node[T]]
    List.range(0, size)
         .foreach( (int) => { 
           println( int + " " + offset); 
           offset = offset.front.asInstanceOf[Node[T]] })
    
  }
  
  case class Node[T](back: AbstractNode, value: Option[T] , front: AbstractNode) extends AbstractNode
  
  object EmptyNode extends AbstractNode
  
  abstract class AbstractNode
}