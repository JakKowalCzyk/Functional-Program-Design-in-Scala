package week5.observer

trait Subscriber {
  def handler(pub: Publisher)

}
