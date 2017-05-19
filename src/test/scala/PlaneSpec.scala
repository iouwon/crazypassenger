import org.scalatest.FlatSpec

class PlaneSpec extends FlatSpec {
  "Plane" should "return false if the seat of a given passenger is not taken" in {
    val plane = Plane(Map(), 1)
    assert(!plane.isSeatTaken(1))
  }

  it should "return true if the seat of a given passenger is taken" in {
    val plane = Plane(Map(1 -> NormalPassenger(1)), 1)
    assert(plane.isSeatTaken(1))
  }

  it should "take seat" in {
    val plane = Plane(Map(1 -> NormalPassenger(1)), 2)
    assert(!plane.isSeatTaken(2))
    assert(plane.takeSeat(2, NormalPassenger(2)).isSeatTaken(2))
  }

  it should "return true if passenger in allocated seat" in {
    val plane = Plane(Map(1 -> NormalPassenger(1)), 2)
    assert(plane.inAllocatedSeat(NormalPassenger(1)))
  }

  it should "return false if passenger not in allocated seat" in {
    val plane = Plane(Map(1 -> NormalPassenger(2)), 2)
    assert(!plane.inAllocatedSeat(NormalPassenger(2)))
  }
}
