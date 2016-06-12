package NeighborCalculator.Test

import org.scalatest.FunSpec

import breeze.linalg.DenseVector
import NeighborCalculator.Neighbor
import NeighborCalculator.SimilarityCalculator

class NeighborSpec extends FunSpec {
  describe("Neighbor Object") {
    lazy val list1 = List(1,0,Nil,Nil,0)
    lazy val list2 = List(1,1,1,Nil,Nil)

    lazy val personOne = (1, list1)
    lazy val personTwo = (2, list2)

    describe("calculateNeighbor") {
      val sim = SimilarityCalculator.binarySimilarity(list1,
                                                      list2,
                                            SimilarityCalculator.jacaard)
      it("returns a list of similar users") {
        val compared = Neighbor.compare(personOne, personTwo)
        assert(compared == ((1,2), sim))
      }

      it("returns a list of sorted user ids with similarity") {

        val compared = Neighbor.compare(personTwo, personOne)
        assert(compared == ((1,2), sim))
      }
    }
    describe("nearest") {
      lazy val simOne = ((1,2), 0.7)
      lazy val simTwo = ((1,3), 0.4)
      lazy val simThree = ((1,4), 0.8)

      lazy val simList = List(simOne, simTwo, simThree)

      it("takes a list of calculated similarities and returns the sorted n nearest") {
        val sims = Neighbor.nearest(simList)
        assert(sims == (1, List(4,2,3)))
      }
    }
  }
}
