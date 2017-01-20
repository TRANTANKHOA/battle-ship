package com.learnscala.play.battleship

import java.util.UUID

case class Player(
                   val ships: Seq[Ship] = Ship().getAllShips,
                   val board: Board = Board(),
                   val rightToShoot: Boolean = false,
                   val uuid: UUID = UUID.randomUUID()
                 ) {
  val isLoser: Boolean = this.board.isLost || this.ships.forall(_.sink)

  val placedAllShips: Boolean = ships.forall(_.isPlaced == true)

  def placeAllShips: Player = {
    ships.foldLeft(this)(
      (player: Player, ship: Ship) => player.placeA(ship)
    )
  }

  def placeA(ship: Ship): Player = this.copy(
    board = this.board.placeA(ship),
    ships = this.ships.map(
      thisShip => if (thisShip.name == ship.name) ship else thisShip
    )
  )

  def recordA(shot: Shot): Player = this.copy(
    board = this.board.recordA(shot)
  )
}