package course2.week4.wires

abstract class Gates extends Simulation {
  def InverterDelay: Int

  def AndGateDelay: Int

  def OrGateDelay: Int

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction(): Unit = {
      val inputSig = input.getSignal()
      afterDelay(InverterDelay) {
        output setSignal (!inputSig)
      }
    }

    input addAction invertAction
  }

  def andGate(in1: Wire, in2: Wire, output: Wire) = {
    def andAction(): Unit = {
      val in1Sig = in1.getSignal()
      val in2Sig = in2.getSignal()
      afterDelay(AndGateDelay) {
        output setSignal (in1Sig && in2Sig)
      }
    }

    in1 addAction andAction
    in2 addAction andAction
  }

  def orGate(in1: Wire, in2: Wire, output: Wire) = {
    def orAction(): Unit = {
      val in1Sig = in1.getSignal()
      val in2Sig = in2.getSignal()
      afterDelay(OrGateDelay) {
        output setSignal (in1Sig | in2Sig)
      }
    }

    in1 addAction orAction
    in2 addAction orAction
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction() = {
      println(s"${name} ${currentTime} new value = ${wire.getSignal()}")
    }

    wire addAction probeAction
  }

  class Wire extends Simulation {
    private var sigVal = false
    private var actions: List[Action] = List()

    def getSignal(): Boolean = sigVal

    def setSignal(sig: Boolean): Unit =
      if (sig != sigVal) {
        sigVal = sig
        actions foreach (_ ())
      }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

}
