import scala.util.Random
import PassengerSyntax._

object PassengerInstances {
  val random = new Random()

  implicit object CrazyPassengerRandomSeatChooser extends SeatChooser[CrazyPassenger] {
    override def takeSeat(plane: Plane)(p: CrazyPassenger): Plane = {
      val chosenSeat = {
        val remainingSeats = plane.remainingSeats
        val seatIndex = random.nextInt(remainingSeats.size)
        remainingSeats(seatIndex)
      }
      plane.takeSeat(chosenSeat, p)
    }
  }

  implicit def normalPassengerSeatChooser(implicit crazySeatChooser: SeatChooser[CrazyPassenger]): SeatChooser[NormalPassenger] = new SeatChooser[NormalPassenger] {
    override def takeSeat(plane: Plane)(p: NormalPassenger): Plane = {
      if(plane.isSeatTaken(p.allocatedSeat)) {
        CrazyPassenger(p.allocatedSeat).takeSeat(plane)
      }else {
        plane.takeSeat(p.allocatedSeat, p)
      }
    }
  }
}
