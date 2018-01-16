package org.scala.exercises.exercise6

import scala.concurrent.ExecutionContext
import scala.collection.mutable.ArrayBuffer
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global
object Test {
  
  implicit val executionContext = CurrentThreadExecutionContext 
  
  def main(args: Array[String]): Unit = {
   val testSuite = new Test()
   
   testSuite.defaultFutureShouldExecuteCallbacksOnceCompleted()
   testSuite.defaultFutureShouldExecuteRightAwayAfterCompletition()
   testSuite.defaultFutureShouldExecuteCorrectlyManyCallbacksRegistered()
   testSuite.defaultFutureShouldMapValueToSomethingElse()
   testSuite.defaultFutureShouldFlatMapTheFutureIntoAnotherFuture()
  }

  
  object CurrentThreadExecutionContext extends ExecutionContext {
    def execute(runnable: Runnable): Unit = runnable.run()
    
    def reportFailure(throwable: Throwable): Unit = throwable.printStackTrace()
  }
}


class Test {
  
  def defaultFutureShouldExecuteCallbacksOnceCompleted(): Unit = {
    //Given  
    val future = new DefaultFuture[String]()
    val items = new ArrayBuffer[String]()
    
    //When
    future.onSuccess{ case value => items+=value}
    future.complete(Success("test value"))
    
    //Then
     val first= items.take(1)
    println(items.take(1) == ArrayBuffer("test value"))
  }
  
  def defaultFutureShouldExecuteRightAwayAfterCompletition(): Unit = {
    //Given
    val future = new DefaultFuture[String]()
    val items = new ArrayBuffer[String]()
    
    //When
    future.complete(Success("test value"))
    future.onSuccess { case value => items += value }
    
     val first= items.take(1)
    println(items.take(1) == ArrayBuffer("test value"))
  }
  
  def defaultFutureShouldExecuteCorrectlyManyCallbacksRegistered(): Unit = {
    //Given
    val future = new DefaultFuture[String]()
    val items = new ArrayBuffer[String]()
    
    //When
    future.complete(Success("test value"))
    future.onSuccess { case value => items += value }
    future.onSuccess { case value => items += value }
    future.onSuccess { case value => items += value }
    //Then
     val first= items.take(1)
    println(items.take(1) == ArrayBuffer("test value"))
  }
  
  def defaultFutureShouldMapValueToSomethingElse(): Unit = {
    val future = new DefaultFuture[String]()
    
    val mapped = future.map(s => s.toInt)
    
    future.complete(Success("1"))
    
    println(mapped.value.get.get == 1)
    
  }
  
  def defaultFutureShouldFlatMapTheFutureIntoAnotherFuture(): Unit = {
    val future = new DefaultFuture[String]()
    val otherFuture = new DefaultFuture[String]()
    
    val result = for ( first <- future;
                      second <- otherFuture ) yield first.toInt + second.toInt;
    
    future.complete(Success("1"))
    
    otherFuture.complete(Success("2"))
    
    println(result.value.get.get == 3)
    
    
  }
  
}


