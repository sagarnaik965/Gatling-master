package com.myGatlingTest

import io.gatling.core.feeder.Feeder

import scala.util.Random

object Main  {
//  def main(args: Array[String]): Unit = {
//    println("Hello world!")
//    JavaClass.getStrings()
//    val callingJava = JavaClass.encode("test")
//    println(callingJava)
//  }

  def callJavaMethod(paramsToEncode: String): String = {
    val javaObject = new JavaClass()
    val result = javaObject.encode("test begins...."+paramsToEncode);
    result
  }







//  def generateFeeder(): Feeder[Any] = {
//    val javaObject = new JavaClass()
//    val data = javaObject.getListData("getFeeder");
////    dataList.map(data => Map("data" -> data))
//    Map("data" -> data) // Map the data to a key-value pair in the feeder
////    val idPostFeeder = Iterator.continually(
////      Map("data" -> data
////    ))
//  }
}