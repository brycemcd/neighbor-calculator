package neighbor_calculator

import scala.annotation.tailrec
import breeze.linalg._

object NeighborCalculator {

  private lazy val list1 = List(1,0,1,1,0)
  private lazy val list3 = List(1,1,1,0,0)
  private lazy val list2 = List(1,0,1,1,0)
  private lazy val list4 = List(1,Nil,1,0,0)

  private lazy val vec1 = DenseVector(1,0,1,1,0)
  private lazy val vec2 = DenseVector(1,0,1,1,0)
  private lazy val vec3 = DenseVector(1,1,1,0,0)
  // TODO: handle the nil case
  //private lazy val vec4 = DenseVector(1,Nil,1,0,0)

  def calculate  = {
    binarySimilarity(list1, list3, NeighborCalculator.jacaard)
    binarySimilarity(vec1, vec3, NeighborCalculator.jacaard)
  }

  def jacaard(result: List[Int]) : Double  = result.sum.toDouble / result.length

  def binarySimilarity(arg1 : Any, arg2 : Any, similarityfx: List[Int] => Double) = {
    val simList = (arg1, arg2) match {
      case (x : List[Any], y: List[Any]) => listBinarySimilarity(x, y)
      case (x : DenseVector[Int], y: DenseVector[Int]) => vectorBinarySimilarity(x, y)

    }
    val sim = similarityfx(simList)
    println(sim)
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
