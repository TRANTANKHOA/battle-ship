package com.learnscala.play.battleship

import java.util.UUID
import scala.util.Random

case class Shot(
                 row: Int = Random.nextInt(Board.numRows) + 1,
                 col: Int = Random.nextInt(Board.numCols) + 1,
                 shooter: UUID
               ) extends Position(row, col)
