import PassengerSyntax._
import PassengerInstances._

object Simulation extends App {
  def runSimulation(): Boolean = {
    val plane = Plane(Map(), 100)

    val passengers = CrazyPassenger(1) :: (2 to 100).map(NormalPassenger).toList

    val fullPlane: Plane = passengers.foldLeft(plane){ case (updatedPlane, p) => p match {
      case pngr: NormalPassenger => pngr.takeSeat(updatedPlane)
      case pngr: CrazyPassenger => pngr.takeSeat(updatedPlane)
    }}

    fullPlane.inAllocatedSeat(NormalPassenger(100))
  }

  println((1 to 1000).count(_ => runSimulation()))
}
