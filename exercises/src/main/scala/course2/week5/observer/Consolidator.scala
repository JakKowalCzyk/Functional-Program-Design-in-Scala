package course2.week5

import course2.week5.observer.{Publisher, Subscriber}

class Consolidator(observed: List[BankAccount]) extends Subscriber {

  observed.foreach(_.subscribe(this))
  private var total: Int = _
  compute()

  override def handler(pub: Publisher): Unit = compute()

  private def compute() =
    total = observed.map(_.currentBalance).sum

  def totalBalance = total
}
