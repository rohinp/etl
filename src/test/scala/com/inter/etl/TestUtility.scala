package com.inter.etl

import java.io.File

abstract class TestUtility {
  val root_path = new File(".").getCanonicalPath

  val inputPath = root_path + "/src/file_input/"
  val outputPath = root_path + "/src/file_output/"

  def cleanUpFile(p:Path) = if(new File(p).exists()) new File(p).delete()
}
