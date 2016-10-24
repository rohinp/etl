package com.inter.etl

import org.junit.Test
import org.scalatest.Matchers._

class ComponentTests {

  @Test
  def itShouldCapitalizeToTransformRecord_SingleCars(): Unit ={
    //given
    val rs:Records = List(Record1(("c1","a")),Record1(("c2","b"))).iterator
    val expected = List(Record1(("c1","A")),Record1(("c2","B")))

    //when
    val actual = Capitalize(rs)

    //then
    actual.toList should equal (expected)
  }

  @Test
  def itShouldCapitalizeToTransformRecord_MultipleWords(): Unit ={
    //given
    val rs:Records = List(Record1(("c1","this is nice")),Record1(("c2","to check"))).iterator
    val expected = List(Record1(("c1","THIS IS NICE")),Record1(("c2","TO CHECK")))

    //when
    val actual = Capitalize(rs)

    //then
    actual.toList should equal (expected)
  }

  @Test
  def itShouldWordDoCountInRecords_singleWords(): Unit ={
    //given
    val rs:Records = List(Record1(("word","d1")),Record1(("word","d1")),Record1(("word","d2")),Record1(("word","d2"))).iterator
    val expected = List(Record2(("word","d2"),("count",2)),Record2(("word","d1"),("count",2)))

    //when
    val actual = WordCount(rs)

    //then
    actual.toList should equal (expected)
  }

  @Test
  def itShouldWordDoCountInRecords_MultipleWords(): Unit ={
    //given
    val rs:Records = List(Record1(("word","d1 d2")),Record1(("word","d1 d2"))).iterator
    val expected = List(Record2(("word","d2"),("count",2)),Record2(("word","d1"),("count",2)))

    //when
    val actual = WordCount(rs)

    //then
    actual.toList should equal (expected)
  }

}
