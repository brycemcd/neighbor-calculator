package NeighborCalculator

import scala.annotation.tailrec
import breeze.linalg.DenseVector

object SimilarityCalculator {

  def jacaard(result: List[Int]) : Double  = result.sum.toDouble / result.length

  def binarySimilarity(arg1 : Any, arg2 : Any, similarityfx: List[Int] => Double) = {
    val simList = (arg1, arg2) match {
      case (x : List[Any], y: List[Any]) => listBinarySimilarity(x, y)
      case (x : DenseVector[Int], y: DenseVector[Int]) => vectorBinarySimilarity(x, y)

    }

    similarityfx(simList)
  }

  def vectorBinarySimilarity(vector1: DenseVector[Int], vector2: DenseVector[Int]) = {

    def theSame(x: Int, y: Int) : Int = if(x == y) 1 else 0

    @tailrec
    def iterate(vect1: DenseVector[Int], vect2: DenseVector[Int], res: List[Int]) : List[Int] = (vect1.length, vect2.length) match {
      case (0, 0) => res
      case (1, 1) => {
        val same = theSame(vect1(0), vect2(0))
        iterate(DenseVector[Int](), DenseVector[Int](), (res :+ same))
      }
      case _ => {
        val same = theSame(vect1(0), vect2(0))
        iterate(vect1(1 to -1), vect2(1 to -1), (res :+ same))
      }
    }

    iterate(vector1, vector2, List[Int]() )
  }

  def reduceNonSimilarOcuurances( list1: List[Any], list2: List[Any],
                                  absentIndicator : Any = Nil) = {

    @tailrec
    def iterate(li1: List[Any], li2: List[Any],
                res: (List[Any], List[Any])
                ) : (List[Any], List[Any])  = (li1, li2) match {

      case (x :: xs, y :: ys) => {
        val next = if(x == absentIndicator || y == absentIndicator) res else ((res._1 :+ x), (res._2 :+ y))
        iterate(xs, ys, next)
      }
      case _ => res
    }

    iterate(list1, list2, (List[Int](), List[Int]()) )
  }
  def listBinarySimilarity(vector1: List[Any], vector2: List[Any]) = {

    @tailrec
    def iterate(vect1: List[Any], vect2: List[Any], res: List[Int]) : List[Int] = (vect1, vect2) match {
      case (x :: xs, y :: ys) => {
        val same = if(x == y) 1 else 0
        iterate(xs, ys, (res :+ same))
      }
      case _ => res
    }

    iterate(vector1, vector2, List[Int]() )
  }
}
