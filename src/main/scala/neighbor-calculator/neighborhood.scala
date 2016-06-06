package NeighborCalculator

import scala.annotation.tailrec
import breeze.linalg._

object Neighbor {
  private lazy val mat = DenseMatrix(
    DenseVector(1,0,0,1,1),
    DenseVector(1,0,0,1,1),
    DenseVector(0,1,1,0,0),
    DenseVector(1,1,1,0,0)
  )
  //def calculate = calcNeighbors(mat)
}
