package course2.week1.generators

trait Tree {

}

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

