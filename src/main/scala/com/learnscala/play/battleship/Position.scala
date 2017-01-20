package com.learnscala.play.battleship

class Position(
                row: Int,
                col: Int
              ) {
  val pos = col + (row - 1) * Board.numCols

  def isOutOfBoard: Boolean = !isInBoard

  def isInBoard: Boolean = (1 to Board.numRows * Board.numCols).contains(pos)
}
