package org.scala.exercises.exercise6

import scala.concurrent.ExecutionContext
import scala.util.Try
import scala.collection.mutable.ArrayBuffer
import scala.util.Success
import scala.util.Failure
import scala.util.control.NonFatal

class DefaultFuture[T] extends Future[T]{
   
  class FutureCallback(val function: (Try[T]) => Any, val context: ExecutionContext)
  
  @volatile private var result: Try[T] = null
  
  private val callbacks = new ArrayBuffer[FutureCallback]()

  def isCompleted: Boolean = result != null
  
  def value: Option[Try[T]] = {
    if(this.isCompleted){
      Some(result)
    }else{
      None
    }
  }
  
  /**
   * dispara los callbacks con el #result
   * 
   * */
  def complete(value: Try[T]) = {
    if(value == null) 
      throw new IllegalArgumentException("A future can't be completed with null")
    
    synchronized {
      if(!this.isCompleted){
        result = value
        fireCallbacks()
      } 
    }
  }
  
  override def onComplete[U](f: (Try[T]) => U) (implicit executor: ExecutionContext): Unit = {
    val callback = new FutureCallback(f,executor)
    
    this.synchronized {
      if(this.isCompleted)
        fireCallback(callback)
      else
        callbacks += callback
    } 
  }
  
  /**
   * ejecuta cada callback en su propio contexto de ejecución,
   * 
   * limpia la colección1
   * 	para evitar que haya referencias circulares entre futuros y promesas
   * */
  private def fireCallbacks(){
    callbacks.foreach(fireCallback)
    
    callbacks.clear()
  }
  
  /**
   * @param callback
   * @attribute context ofrece contexto de ejecución al Future para ejecutarse
   * */
  private def fireCallback(callback: FutureCallback){
    callback.context.execute(new Runnable{
      def run() = callback.function(result)
    })
  }
  /**
   * Crea una Promesa y devuelve el futuro de esa promesa
   * 
   * Registra un callback en este Future, y ejecutarán su operación cuando este Future se termine.
   * */
  def flatMap[S](f: (T) => Future[S])(implicit context: ExecutionContext): Future[S] = {
    val promise = Promise[S]()
    
    onComplete { 
      case Success(value) => {
        try{
          f(value).onComplete(promise.complete)
        }catch{
          case NonFatal(exception) => {promise.failure(exception)}
        }
      }

      case Failure(error) => promise.failure(error)
    }
    
    promise.future
  }
  
  def map[S](f: (T) => S)(implicit context: ExecutionContext): Future[S] = {
    val promise = Promise[S]()
    onComplete{
      /** Se pasa el valor de S a la función map del Try[T] al método complete de la Promise[S]
      *  
      */
      v => promise.complete(v.map(f)) 
    }
    
    promise.future
  }
  
}