case class Plane(seats: Map[Int, Passenger], maxPassengers: Int) {
  def isSeatTaken(seat: Int): Boolean = seats.contains(seat)
  def takeSeat(seat: Int, passenger: Passenger): Plane = Plane(seats.updated(seat, passenger), maxPassengers)
  def remainingSeats: List[Int] = ((1 to maxPassengers).toSet -- seats.keySet).toList
  def inAllocatedSeat(passenger: Passenger): Boolean = seats.get(passenger.allocatedSeat).exists(_.allocatedSeat == passenger.allocatedSeat)
}
