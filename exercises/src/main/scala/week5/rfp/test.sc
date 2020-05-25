import week5.rfp.{BankAccount, Var}

val a = new BankAccount
val b = new BankAccount
val c = consolidated(List(a, b))

def consolidated(accts: List[BankAccount]): Var[Int] = {
  Var(accts.map(_.balance()).sum)
}
c()
a deposit 20
c()
b deposit (30)
c()