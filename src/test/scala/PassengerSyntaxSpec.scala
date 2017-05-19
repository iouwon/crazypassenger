import PassengerInstances.normalPassengerSeatChooser
import PassengerSyntax._
import org.scalatest.FlatSpec

import scala.language.implicitConversions

class PassengerSyntaxSpec extends FlatSpec {
  def crazyPassengerGivenSeatChooser(chosenSeat: Int): SeatChooser[CrazyPassenger] = new SeatChooser[CrazyPassenger] {
    override def takeSeat(plane: Plane)(p: CrazyPassenger): Plane = plane.takeSeat(chosenSeat, p)
  }

  "Crazy passenger" should "choose any untaken seat in the plane" in {
    implicit val crazySeatChooser = crazyPassengerGivenSeatChooser(1)

    val plane = Plane(Map(), 1)

    assert(!plane.isSeatTaken(1))

    val updatedPlane = CrazyPassenger(1).takeSeat(plane)

    assert(updatedPlane.isSeatTaken(1))
  }

  it should "choose a new seat if the seat is already taken in the plane" in {
    implicit val crazySeatChooser = crazyPassengerGivenSeatChooser(5)
    val plane = Plane(Map(), 10)

    assert(!plane.isSeatTaken(5))

    val updatedPlane = CrazyPassenger(1).takeSeat(plane)

    assert(updatedPlane.isSeatTaken(5))
  }

  "Normal passenger" should "take allocated seat if unoccupied" in {
    implicit val crazySeatChooser = crazyPassengerGivenSeatChooser(1)
    val plane = Plane(Map(), 1)

    val updatedPlane = NormalPassenger(1).takeSeat(plane)

    assert(updatedPlane.isSeatTaken(1))
  }

  it should "go crazy if seat occupied" in {
    implicit val crazySeatChooser = crazyPassengerGivenSeatChooser(2)
    val plane = Plane(Map(1 -> NormalPassenger(1)), 2)

    val updatedPlane = NormalPassenger(1).takeSeat(plane)

    assert(updatedPlane.isSeatTaken(2))
  }
}
