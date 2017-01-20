package com.learnscala.play.battleship

// hardcoded here to avoid typos elsewhere using autocompletion
case class ListAllShipTypes(
                             Carrier: Tuple2[String, Int] = ("Carrier", 5),
                             Battleship: Tuple2[String, Int] = ("Battleship", 4),
                             Submarine: Tuple2[String, Int] = ("Submarine", 3),
                             Cruiser: Tuple2[String, Int] = ("Cruiser", 2),
                             Patrol: Tuple2[String, Int] = ("Patrol", 1)
                           ) {
  val allShips = Seq(Carrier,Battleship,Submarine,Cruiser,Patrol)
}