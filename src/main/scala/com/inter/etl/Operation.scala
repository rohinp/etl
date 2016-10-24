package com.inter.etl

import scala.io.Source._
import java.nio.file.{Paths, Files, StandardOpenOption}
import java.nio.charset.{StandardCharsets}
import java.io.File

trait Operation0[I,O] extends ((I) => O)
trait Operation1[I,C] extends ((I,C) => Unit)

case class RecordOpr(exec:(Records) => Records) extends Operation0[Records,Records] {
  override def apply(r1:Records):Records = exec(r1)
}

case class FileInputOpr(marsh:(Iterator[String]) => Records) extends Operation0[Path,Option[Records]] {
  override def apply(p:Path) = if(fromFile(p).getLines().isEmpty) None else Some(marsh(fromFile(p).getLines()))
}

case class FileOutputOpr(marsh:(Records) => Iterator[String]) extends Operation1[Records,Path] {
  override def apply(rs:Records, path:Path) = marsh(rs).foreach(l => write(path,l))

  def write(filePath:String, contents:String) =
    if(new File(filePath).exists())
      Files.write(Paths.get(filePath), contents.getBytes(StandardCharsets.UTF_8),StandardOpenOption.APPEND)
    else
      Files.write(Paths.get(filePath), contents.getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE)
}
