package NeighborCalculator

import scala.annotation.tailrec
import breeze.linalg._


object Neighbor {
  // TODO: put this in a package object?
  type NeighborSimilarity = Tuple2[(Int, Int), Double]

  // TODO: move this to Similarity Calculator
  def compare(one: Tuple2[Int, List[Any]],
              two: Tuple2[Int, List[Any]]) = {
  val (left_id, left_features) = if(one._1 < two._1) one else two
  val (right_id, right_features) = if(one._1 < two._1) two else one 

  val sim = SimilarityCalculator.binarySimilarity(left_features,
                                        right_features,
                                        SimilarityCalculator.jacaard)
  ((left_id, right_id), sim)
  }

  def nearest(calculateNeighbors: Seq[NeighborSimilarity]) =  {
    val root = calculateNeighbors.head._1._1
    val sorted = calculateNeighbors sortBy { _._2 } map (_._1._2)
    (root, sorted.reverse)
  }
}
