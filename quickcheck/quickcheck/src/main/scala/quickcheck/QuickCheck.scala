package quickcheck

import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.Prop._
import org.scalacheck._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    v <- arbitrary[Int]
    h <- oneOf(Gen.const(empty), genHeap)
  } yield insert(v, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("two elements min") = forAll {
    (a: Int, b: Int) =>
      val h = insert(a, empty)
      val h1 = insert(b, h)
      findMin(h1) == Math.min(a, b)
  }

  property("empty1") = forAll { a: Int =>
    val h = insert(a, empty)
    isEmpty(deleteMin(h))
  }

  property("min of two") = forAll {
    (h1: H, h2: H) =>
      findMin(meld(h1, h2)) == Math.min(findMin(h1), findMin(h2))
  }

  property("sorted") = forAll {
    h: H =>
      def getSortedSequence(h: H): List[Int] = {
        if (isEmpty(h)) List()
        else findMin(h) :: getSortedSequence(deleteMin(h))
      }

      val sorted = getSortedSequence(h)
      sorted == sorted.sorted
  }

  property("meld") = forAll {
    (h1: H, h2: H) =>
      val m1 = findMin(h1)
      val m2 = findMin(h2)
      val min = Math.min(m1, m2)
      findMin(meld(deleteMin(h1), insert(min, h2))) == min
  }

  property("meld2") = forAll { (h1: H, h2: H) =>
    def equals(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else findMin(h1) == findMin(h2) && equals(deleteMin(h1), deleteMin(h2))

    equals(meld(h1, h2), meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

}
