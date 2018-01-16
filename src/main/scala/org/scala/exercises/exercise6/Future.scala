package org.scala.exercises.exercise6

import scala.util.Try
import scala.concurrent.ExecutionContext
import scala.util.Failure
import scala.util.Success

trait Future[T] {
  
  def isCompleted: Boolean
  
  def value: Option[Try[T]]
  
  def flatMap[S] (flatten: (T) => Future[S])(implicit executor: ExecutionContext): Future[S]
  
  def map[S](func: (T) => S)(implicit executor: ExecutionContext): Future[S]
  
  def foreach[U](func: T => U)(implicit executor: ExecutionContext): Unit = map(func)
  
  /**
   * Ejecuta el código en todos los casos, ya sea exitosa o fallida la ejecución.
   * */
  def onComplete[U](func: (Try[T]) => U) (implicit executor: ExecutionContext): Unit
  
  def onFailure[U](partialFunc: PartialFunction[Throwable, U]) (implicit executor: ExecutionContext) = {
    onComplete { 
      case Failure(e) => partialFunc.apply(e)
      case _ => {}
    }
  }
  
  def onSuccess[U](partialFunc: PartialFunction[T,U]) (implicit executor: ExecutionContext): Unit = {
    onComplete { 
      case Success(v) => partialFunc.apply(v)
      case _ => {}
    }
  }
  
}