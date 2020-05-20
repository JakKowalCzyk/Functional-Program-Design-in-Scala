package week2

object test extends App {


  val problem = new Pouring(Vector(4, 9, 19))
  problem.moves

  problem.pathSets.take(3).toList

  println(problem.solution(16).head)

}
