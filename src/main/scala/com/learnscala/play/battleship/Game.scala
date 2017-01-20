package com.learnscala.play.battleship

import java.util.UUID

import scala.util.Random

case class Game(
                 me: Player = Player(),
                 you: Player = Player()
               ) {
  val hasWinner = me.isLoser || you.isLoser

  val winnerID: Option[UUID] = if (hasWinner)
    if (me.isLoser)
      Some(you.uuid)
    else
      Some(me.uuid)
  else
    None

  val readyToPlay = you.placedAllShips && me.placedAllShips

  def callRandomShooter: Game =
    if (Random.nextInt(2) < 1)
      this
        .copy(me = me.copy(rightToShoot = true))
        .copy(you = you.copy(rightToShoot = false))
    else
      this
        .copy(me = me.copy(rightToShoot = false))
        .copy(you = you.copy(rightToShoot = true))

  def callNextShooter: Game = this
    .copy(me = me.copy(rightToShoot = !me.rightToShoot))
    .copy(you = you.copy(rightToShoot = !you.rightToShoot))

  def recordA(shot: Shot): Game = {
    val checkRightToShoot = if (me.rightToShoot) shot.shooter == me.uuid else shot.shooter == you.uuid

    checkRightToShoot match {
      case true => this.copy(
        me = if (shot.shooter == me.uuid) me else me.recordA(shot),
        you = if (shot.shooter == you.uuid) you else you.recordA(shot)
      ).callNextShooter
      case false => throw new RuntimeException("NoRightToShoot")
    }
  }

  def placeA(ship: Ship): Game = {
    this.copy(
      me = if (ship.owner == me.uuid) me.placeA(ship) else me,
      you = if (ship.owner == you.uuid) you.placeA(ship) else you
    )
  }

  def placeAllShips : Game = this.copy(
    me = me.placeAllShips,
    you = you.placeAllShips
  )

  def getShooterUUID : UUID = if (me.rightToShoot) me.uuid else you.uuid
}
