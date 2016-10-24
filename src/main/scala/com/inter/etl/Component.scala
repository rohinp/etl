package com.inter.etl

trait Component

object Capitalize extends Component{
  def apply(rs:Records):Records =
    RecordOpr(_.map { case r1: Record1[_] => Record1((r1.fields._1, r1.fields._2.toString.split("").map(_.capitalize).mkString))
    })(rs)
}

object WordCount extends Component {
  def apply(rs:Records):Records = RecordOpr(wCount) (rs)

  def wCount(rs:Records):Records = flatToWords(rs)
                                      .groupBy(identity)
                                      .mapValues(_.length)
                                      .map(v => Record2(("word",v._1),("count",v._2))).iterator

  def flatToWords(rs:Records) = for {
    record <- rs.toList
    word <- record match {case r1:Record1[_] => r1.fields._2.toString.split(" ")}
  } yield word

}

object FileInput extends Component {
  def apply(p: Path): Option[Records] = FileInputOpr((itr) => itr.map(l => Record1("line", l))) (p)
}

object FileOutput extends Component {
  def apply(rs: Records, p: Path): Boolean = {
    try {
      FileOutputOpr((rss) => rss.map {
        case r1: Record1[_] => r1.fields._2 + "\n"
        case r2: Record2[_,_] => r2.fields._1._2 + " => " + r2.fields._2._2 + "\n"
      })(rs,p)
      true
    }catch { case (e:Exception) => false}
  }
}