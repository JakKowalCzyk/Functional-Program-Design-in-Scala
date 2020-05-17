package week1


object books extends App {
  val books : Set[Book] = Set(
    Book(title = "Structure and implementation of computer programs",
      authors = List("Abelson, Harald", "Susman, Gerald J.")),
    Book(title = "Introduction to functional programming",
      authors = List("Bird, Richard", "Wadler, Phil")),
    Book(title = "Effective Java",
      authors = List("Block, Joshua")),
    Book(title = "Effective Java2",
      authors = List("Block, Joshua")),
    Book(title = "Java puzzlers",
      authors = List("Block, Joshua", "Gafter, Neal")),
    Book(title = "Programming in Scala",
      authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill"))
  )

  println(for(book <- books; a <- book.authors if a startsWith "Block") yield book.title)

  println(for(book <- books if book.title.indexOf( "Program") >= 0) yield book.title)

  val twoBooks = for{
    b1 <- books
    b2 <-books
    if b1.title < b2.title
    a1 <- b1.authors
    a2 <- b2.authors
    if a1==a2
  }yield a1
  println(twoBooks)

  println(for(book <- books; a <- book.authors if a startsWith "Bird") yield book.title)
  println(books.flatMap(book => book.authors.withFilter(a => a.startsWith("Bird")).map(x => book.title)))


}
