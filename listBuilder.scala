object listBuilder extends App {

  // 1: direct translation from Java to Scala:

  import scala.collection.mutable
  def seqFromRangeVersion1: Seq[Int] = {
    val result = mutable.ArrayBuffer[Int]()
    (0 until 100).foreach { i =>
      result += i * i
    }
    result
  }

  // 2: getting rid of mutable collection:

  def seqFromRangeVersion2: Seq[Int] = {
    var result = Vector[Int]()
    (0 until 100).foreach { i =>
      result = result :+ i * i
    }
    result
  }

  // 3: removing local var using recursion:

  import scala.annotation.tailrec
  def seqFromRangeVersion3: Seq[Int] = {
    @tailrec
    def recurse(range: Range, accumulator: Seq[Int]): Seq[Int] = {
      if (range.isEmpty) {
        accumulator
      } else {
        val i = range.head
        recurse(range.tail, accumulator :+ i * i)
      }
    }
    recurse(0 until 100, Vector())
  }

  // 4: removing local var using foldLeft:

  def seqFromRangeVersion4: Seq[Int] = {
    (0 until 100).foldLeft(Vector[Int]()) {
      (accumulator, i) => accumulator :+ i * i
    }
  }

  // 5: shorthand version of foldLeft:

  def seqFromRangeVersion5: Seq[Int] = {
    (Vector[Int]() /: (0 until 100)) {
      (accumulator, i) => accumulator :+ i * i
    }
  }

  // 6: using map:

  def seqFromRangeVersion6: Seq[Int] = (0 until 100) map { i => i * i }

  // -----

  // println(s"version 1: $seqFromRangeVersion1")
  // println(s"version 2: $seqFromRangeVersion2")
  // println(s"version 3: $seqFromRangeVersion3")
  // println(s"version 4: $seqFromRangeVersion4")
  // println(s"version 5: $seqFromRangeVersion5")
  // println(s"version 6: $seqFromRangeVersion6")

  assert(seqFromRangeVersion1 == seqFromRangeVersion2)
  assert(seqFromRangeVersion1 == seqFromRangeVersion3)
  assert(seqFromRangeVersion1 == seqFromRangeVersion4)
  assert(seqFromRangeVersion1 == seqFromRangeVersion5)
  assert(seqFromRangeVersion1 == seqFromRangeVersion6)

}
