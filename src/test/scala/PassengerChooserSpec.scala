import PassengerInstances.CrazyPassengerRandomSeatChooser
import PassengerInstances.normalPassengerSeatChooser
import org.scalatest.FlatSpec

class PassengerChooserSpec extends FlatSpec {

  "Crazy passenger chooser spec" should "randomly choose unoccupied seats until plane is full" in {
    val plane = Plane(Map(1 -> NormalPassenger(1),3 -> NormalPassenger(3),5 -> NormalPassenger(5)), 5)

    val fullPlane = (1 to 2).foldLeft(plane) { (plane, seat) =>
      CrazyPassengerRandomSeatChooser.takeSeat(plane)(CrazyPassenger(seat))
    }

    assert(fullPlane.seats(1) == NormalPassenger(1))
    assert(fullPlane.seats(2).isInstanceOf[CrazyPassenger])
    assert(fullPlane.seats(3) == NormalPassenger(3))
    assert(fullPlane.seats(4).isInstanceOf[CrazyPassenger])
    assert(fullPlane.seats(5) == NormalPassenger(5))
  }

  "Normal passenger chooser spec" should "choose allocated seat" in {
    val plane = Plane(Map(1 -> NormalPassenger(1),3 -> NormalPassenger(3),5 -> NormalPassenger(5)), 5)

    val fullPlane = List(2,4).foldLeft(plane) { (plane, seat) =>
      normalPassengerSeatChooser.takeSeat(plane)(NormalPassenger(seat))
    }

    assert(fullPlane.seats(1) == NormalPassenger(1))
    assert(fullPlane.seats(2) == NormalPassenger(2))
    assert(fullPlane.seats(3) == NormalPassenger(3))
    assert(fullPlane.seats(4) == NormalPassenger(4))
    assert(fullPlane.seats(5) == NormalPassenger(5))
  }

  "Normal passenger chooser spec" should "resort to crazy behaviour if allocated seat occupied" in {
    val plane = Plane(Map(1 -> CrazyPassenger(2),3 -> NormalPassenger(3),5 -> CrazyPassenger(4)), 5)

    val fullPlane = List(1,5).foldLeft(plane) { (plane, seat) =>
      normalPassengerSeatChooser.takeSeat(plane)(NormalPassenger(seat))
    }

    assert(fullPlane.seats(1) == CrazyPassenger(2))
    assert(fullPlane.seats(2).isInstanceOf[CrazyPassenger])
    assert(fullPlane.seats(3) == NormalPassenger(3))
    assert(fullPlane.seats(4).isInstanceOf[CrazyPassenger])
    assert(fullPlane.seats(5) == CrazyPassenger(4))
  }
}
