package com.inter.etl

import java.io.File

/**
  * Created by rohinp on 10/24/2016.
  */
abstract class TestUtility {
  val root_path = new File(".").getCanonicalPath

  val inputPath = root_path + "/src/file_input/"
  val outputPath = root_path + "/src/file_output/"

  def cleanUpFile(p:Path) = if(new File(p).exists()) new File(p).delete()
}
