package com.learnscala.play.battleship

import java.util.UUID
import scala.util.Random

case class Ship(
                 name: String = "",
                 length: Int = 0,
                 horizontal: Boolean = true,
                 position: Position = new Position(Random.nextInt(Board.numRows) + 1, 1),
                 hitCells: Seq[Int] = Seq.empty[Int],
                 owner: UUID = UUID.randomUUID(),
                 isPlaced: Boolean = false
               ) {
  val occupiedCells: Seq[Int] = if (horizontal)
    (position.pos until position.pos + length)
  else
    (0 until length).map(
      int => position.pos + int * Board.numCols
    )

  val isOutOfBoard = this.occupiedCells.exists(
    !(Board.positions).contains(_)
  )

  val sink: Boolean = occupiedCells.forall(cell => hitCells.contains(cell))

  val listAllShipTypes = ListAllShipTypes()

  def getAllShips: Seq[Ship] = Random.shuffle((1 to Board.numRows)).toSeq
    .zip(
      listAllShipTypes.allShips
    )
    .map(
      ship => new Ship(
        name = ship._2._1,
        length = ship._2._2,
        position = new Position(ship._1, 1))
    )


  def moveTo(position: Position): Ship = this.copy(position = position)

}

