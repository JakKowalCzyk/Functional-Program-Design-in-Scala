import course2.week4.wires.{Circuits, Parameters}

val in1, in2, sum, carry = new Wire

import sim._

object sim extends Circuits with Parameters

halfAdder(in1, in2, sum, carry)
probe("sum", sum)
probe("carry", carry)

in1 setSignal (true)
run()

in2 setSignal (false)
run()

in1 setSignal (true)
run()