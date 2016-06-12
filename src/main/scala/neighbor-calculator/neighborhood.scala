package NeighborCalculator

import scala.annotation.tailrec
import breeze.linalg._


object Neighbor {
  // TODO: put this in a package object?
  type NeighborSimilarity = Tuple2[(Int, Int), Double]

  def nearest(calculateNeighbors: Seq[NeighborSimilarity], k : Option[Int] = None) =  {
    val root = calculateNeighbors.head._1._1
    val sorted = calculateNeighbors.
                  sortBy( _._2 ).
                  map{ case (pair, score) => (pair._2, score) }.
                  reverse

    val nSorted = k match {
      case None => sorted
      case Some(n) => sorted.take(n)
    }

    (root, nSorted)
  }
}
