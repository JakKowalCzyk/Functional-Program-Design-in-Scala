package week1.generators

import java.util.Random

trait Generator[+T] {
  self =>
  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    override def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    override def generate = f(self.generate).generate
  }
}

object Generators {
  val integers = new Generator[Int] {
    val rand = new Random()

    override def generate = rand.nextInt()
  }

  def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
    x => u map { y => (x, y) }
  }

  val booleans = integers map { x=> x>0}

  def single[T](x: T): Generator[T] = new Generator[T] {
    override def generate = x
  }

  def choose(lo: Int, hi: Int): Generator[Int] =
    for (x <- integers) yield lo + x % (hi - lo)

  def oneOf[T](xs: T*): Generator[T] =
    for (idx <- choose(0, xs.length)) yield xs(idx)

  def lists: Generator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyList else nonEmptyList
  } yield list

  def emptyList = single(Nil)

  def nonEmptyList = for {
    head <- integers
    tail <- lists
  } yield head :: tail



}
