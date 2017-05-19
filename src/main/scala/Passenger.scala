sealed trait Passenger {
  val allocatedSeat: Int
}

case class CrazyPassenger(allocatedSeat: Int) extends Passenger
case class NormalPassenger(allocatedSeat: Int) extends Passenger