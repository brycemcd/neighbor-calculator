package NeighborCalculator.Test

import org.scalatest.FunSpec

import breeze.linalg.DenseVector
import NeighborCalculator.Neighbor
import NeighborCalculator.SimilarityCalculator

class NeighborSpec extends FunSpec {
  describe("Neighbor Object") {

    describe("nearest") {
      lazy val simOne = ((1,2), 0.7)
      lazy val simTwo = ((1,3), 0.4)
      lazy val simThree = ((1,4), 0.8)

      lazy val simList = List(simOne, simTwo, simThree)
      lazy val pairScores = List( (4, 0.8), (2, 0.7), (3, 0.4) )

      it("takes a list of calculated similarities and returns the sorted nearest") {
        val sims = Neighbor.nearest(simList)
        assert(sims == (1, pairScores))
      }

      it("takes a list of calculated similarities and returns the sorted N nearest") {
        val k = 2
        val sims = Neighbor.nearest(simList, Some(k))
        assert(sims == (1, pairScores.take(k)))
      }
    }
  }
}
