package org.scala.exercises.exercise5

sealed trait Option[+E] {
  def isDefined(): Boolean
  def get(): E
  def getOrElse[B >: E]( f: ()=> B) : B = if (isDefined) get() else f()
  def map[R](f: E => R): Option[R]
  def flatMap[R](f: E => Option[R]): Option[R] = if(isDefined) f(this.get) else None
  def foreach[U](f: E=> U) : Unit
  
}

case class Some[+E] (element: E) extends Option[E]{
  
  override def isDefined(): Boolean = true
  override def get(): E = element
  override def map[R](f: E => R): Option[R] = Some(f(element))
  override def foreach[U](f: E => U): Unit = f(element)
  
}
case object None extends Option[Nothing]{
  
  override def isDefined(): Boolean = false
  override def get(): Nothing = throw new NoSuchElementException
  override def map[R](f: (Nothing) => R): Option[R] = None
  override def foreach[U](f: Nothing => U): Unit = {}
}