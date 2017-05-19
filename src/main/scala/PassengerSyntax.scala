object PassengerSyntax {
  implicit class PassengerOps[P <: Passenger: SeatChooser](p: P) {
    def takeSeat(plane: Plane): Plane = implicitly[SeatChooser[P]].takeSeat(plane)(p)
  }
}
