package course2.week1.generators

import course2.week1.generators.Generators.{booleans, integers}

object Gen extends App {

  def leafs: Generator[Leaf] = for {
    x <- integers
  } yield Leaf(x)

  def inners: Generator[Inner] = for {
    left <- treeGenerator
    right <- treeGenerator
  } yield Inner(left, right)

  def treeGenerator:Generator[Tree] = for{
    isLeaf <- booleans
    tree <- if (isLeaf) leafs else inners
  } yield tree

  println(treeGenerator.generate)
}
