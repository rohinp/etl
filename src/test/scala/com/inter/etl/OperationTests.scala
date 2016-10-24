package com.inter.etl

import org.junit.Test
import org.scalatest.Matchers._

class OperationTests extends TestUtility{

  @Test
  def itShouldAcceptRecordsAndReturnRecords(): Unit = {
    //given
    val operation = RecordOpr(identity)
    val rs:Records = List(Record1(("c1","d1")),Record1(("c2","d2"))).iterator

    //when
    val actual:Records = operation (rs)

    //then
    actual.toList should equal (List(Record1(("c1","d1")),Record1(("c2","d2"))))

  }

  @Test
  def itShouldAcceptFilePathAndGetRecords_TypeRecord1(): Unit ={
    //given
    val operation = FileInputOpr((itr) => itr.map(l => Record1("line", l)))
    val p:Path = inputPath + "record1Test.txt"
    //when
    val actual:Records = operation (p).getOrElse(List().iterator)

    //then
    actual.toList should equal (List(Record1(("line","Data1")),Record1(("line","Data2")),Record1(("line","Data3"))))
  }

  @Test
  def itShouldAcceptFilePathAndGetRecords_TypeRecord2(): Unit ={
    //given
    val operation = FileInputOpr((itr) => itr.map(l => Record2((("c1",l.split(" ")(0)),("c2",l.split(" ")(1))))))
    val p:Path = inputPath + "record2Test.txt"

    //when
    val actual:Records = operation (p).getOrElse(List().iterator)

    //then
    actual.toList should equal (List(Record2((("c1","One"),("c2","One1"))),Record2((("c1","two"),("c2","two2")))))
  }

  @Test
  def itShouldWriteRecordsToAFile(): Unit ={
    //given
    val p:Path = outputPath + "outputTest1.txt"
    cleanUpFile(p)

    val rs:Records = List(Record1(("c1","d1")),Record1(("c2","d2"))).iterator
    val operation = FileOutputOpr((rs) => rs.map { case u: Record1[String] => u.fields._2 + "\n" })

    //when
    operation (rs,p)
    val operation1 = FileInputOpr((itr) => itr.map(l => Record1("line", l)))
    val actual:Records = operation1 (p).getOrElse(List().iterator)

    //then
    actual.toList should equal (List(Record1(("line","d1")),Record1(("line","d2"))))
  }



}
