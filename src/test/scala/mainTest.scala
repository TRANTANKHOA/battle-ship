package com.learnscala.play.battleship

import org.scalatest.FunSpec

class mainTest extends FunSpec {
  describe("Perform a random shooting game") {
    it("should print out the winner UUID") {
      var myWins = 0
      var yourWins = 0
      for (n <- 1 to 2000) {
        var game = (new main).newGame.placeAllShips
        val playerNames = Map(game.me.uuid -> "I", game.you.uuid -> "You")
        while (!game.hasWinner) {
          val randomShot = Shot(shooter = game.getShooterUUID)
          // println(playerNames(randomShot.shooter) + " shot at cell#" + randomShot.pos.toString)
          game = game.recordA(
            randomShot
          )
        }
        println(playerNames(game.winnerID.get) + " win!!")

        if (game.winnerID.get == game.me.uuid)
          myWins += 1
        else
          yourWins += 1

        assert(game.winnerID.get.toString.nonEmpty)
      }
      println(s"I win $myWins times and you win $yourWins times")
    }
  }
}