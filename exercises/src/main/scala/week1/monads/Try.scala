package week1.monads

import scala.util.control.NonFatal


abstract class Try[+T] {
  def flatMap[U](f: T => Try[U]): Try[U] = this match {
    case Success(x) => try f(x) catch {
      case NonFatal(ex) => Failure(new Exception(ex))
    }
    case failure: Failure => failure
  }

  def map[U](f:T => U):Try[U] = this match {
    case Success(x) => Try(f(x))
    case failure: Failure => failure
  }
}

case class Success[T](x: T) extends Try[T]

case class Failure(ex: Exception) extends Try[Nothing]

object Try {
  def apply[T](expr: => T): Try[T] =
    try Success(expr)
    catch {
      case NonFatal(ex) => Failure(new Exception(ex))
    }
}