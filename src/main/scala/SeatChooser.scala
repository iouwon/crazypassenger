trait SeatChooser[T <: Passenger] {
  def takeSeat(plane: Plane)(t: T): Plane
}
