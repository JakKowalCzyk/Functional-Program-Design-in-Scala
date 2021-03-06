package course2.week5

import course2.week5.observer.Publisher

class BankAccount extends Publisher {
  private var balance = 0

  def deposit(amount: Int): Unit = {
    if (amount > 0) balance = balance + amount
    publish()
  }

  def withdraw(amount: Int): Unit =
    if (0 < amount && amount <= balance) {
      balance = balance - amount
      publish()
    } else throw new Error("funds")

  def currentBalance: Int = balance
}
