implicit val n: Int = 42
implicit val world: String = "World"
println(f)

println(f(0))

def f(implicit x: Int) = x

def greet(implicit name: String) = s"Hello, $name!"
println(greet)

def printValue[A: Show](a: A): Unit = {
  println(implicitly[Show[A]].apply(a))
}

trait Show[A] {
  def apply(a: A): String
}

object Show {
  implicit val showInt: Show[Int] = new Show[Int] {
    def apply(n: Int): String = s"Int($n)"
  }
}

printValue(42)
