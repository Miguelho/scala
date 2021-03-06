/*
 * Copyright (c) 2015 Daniel Higuero.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scala.examples

object HelloWorld {

  /**
   * Constant.
   */
  val Hi : String = "Hi from object"

  /**
   * Entry point for the HelloWorld class.
   * @param args The arguments.
   */
  def main(args: Array[String]) : Unit = {
    println("Hello world from main!")
    val obj = new HelloWorld
    obj.sayHi()
  }
}

/**
 * Basic hello world example.
 */
class HelloWorld{

  /**
   * Method that print the contents of the Hi constant.
   */
  def sayHi() : Unit = {
    println(HelloWorld.Hi)
  }

}
