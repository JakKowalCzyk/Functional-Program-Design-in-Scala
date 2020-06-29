package course2.week5.observer

trait Subscriber {
  def handler(pub: Publisher)

}
