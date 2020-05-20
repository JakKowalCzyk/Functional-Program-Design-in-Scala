val level =
  """ooo-------
    |oSoooo----
    |ooooooooo-
    |-ooooooooo
    |-----ooToo
    |------ooo-""".stripMargin


val vector: Vector[Vector[Char]] =
  Vector(level.split("\r?\n").map(str => Vector(str: _*)).toIndexedSeq: _*)