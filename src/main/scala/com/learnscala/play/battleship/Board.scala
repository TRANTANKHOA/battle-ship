package com.learnscala.play.battleship

case class Board(
                  private val occupiedCells: Seq[Int] = Seq.empty[Int],
                  val hitCells: Seq[Int] = Seq.empty[Int],
                  val missedCells: Seq[Int] = Seq.empty[Int]
                ) {
  val isLost: Boolean = occupiedCells.forall(cell => hitCells.contains(cell))

  def recordA(shot: Shot): Board =
    if (occupiedCells.contains(shot.pos))
      this.copy(hitCells = this.hitCells ++ Seq(shot.pos))
    else
      this.copy(missedCells = missedCells ++ Seq(shot.pos))

  def placeA(ship: Ship): Board = ship.isOutOfBoard match {
    case true => throw new RuntimeException("ShipOutOfBoard")
    case false => ship.occupiedCells.exists(cell => this.occupiedCells.contains(cell)) match {
      case true => throw new RuntimeException("ShipisOverlapped")
      case false => this.copy(occupiedCells = occupiedCells ++ ship.occupiedCells)
    }
  }

}
object Board {
  val numRows: Int = 10
  val numCols: Int = 10
  val positions: Seq[Int] = 1 to numCols*numRows
}
