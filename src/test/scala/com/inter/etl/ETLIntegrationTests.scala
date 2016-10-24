package com.inter.etl

import org.junit.Test
import org.scalatest.Matchers._


class ETLIntegrationTests extends TestUtility{

  //Part 1
  @Test
  def itShouldTakeFileInputAndCapitalizeAndSaveInFile(): Unit ={
    //given
    val inFile:Path = inputPath + "Part1_Input.txt"
    val outFile:Path = outputPath + "Part1_Output.txt"
    cleanUpFile(outFile)
    //when
    val success:Boolean = FileOutput(Capitalize(FileInput(inFile).getOrElse(Iterator())),outFile)

    //then
    success shouldBe true
  }

  //Part 2
  @Test
  def itShouldTakeFileInputAndDoWordCount(): Unit ={
    //given
    val inFile:Path = inputPath + "Part2_Input.txt"
    val outFile:Path = outputPath + "Part2_Output.txt"
    cleanUpFile(outFile)
    //when
    val success:Boolean = FileOutput(WordCount(FileInput(inFile).getOrElse(Iterator())),outFile)

    //then
    success shouldBe true
  }
}
