package week4.wires

abstract class Simulation {

  type Action = () => Unit
  private type Agenda = List[Event]
  private var agenda: Agenda = List()
  private var curtime = 0

  def currentTime: Int = curtime

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(curtime + delay, () => block)
    agenda = insert(agenda, item)
  }

  def run(): Unit = {
    afterDelay(0) {
      println(s"*** Simulation started, time ${curtime} ***")
    }
    loop()
  }

  private def insert(agenda: Agenda, item: Event): Agenda = agenda match {
    case first :: rest if first.time <= item.time => first :: insert(rest, item)
    case _ => item :: agenda
  }

  private def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curtime = first.time
      first.action()
      loop()
    case Nil =>
  }

  case class Event(time: Int, action: Action)

}

