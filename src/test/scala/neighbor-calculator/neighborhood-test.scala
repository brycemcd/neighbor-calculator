import org.scalatest.FunSpec

import breeze.linalg.DenseVector
import NeighborCalculator.SimilarityCalculator

class SimilarityCalculatorSpec extends FunSpec {
  describe("SimilarityCalculator Object") {
    lazy val list1 = List(1,0,1,1,0)
    lazy val list2 = List(1,0,1,1,0)
    lazy val list3 = List(1,1,1,0,0)
    lazy val list4 = List(1,Nil,1,0,0)

    describe(".jacaard") {

      describe("calculates the jacaard similarity score of a List of 1's and 0's") {
        it("is 1 when the list is all ones") {
          val list = List[Int](1,1,1,1,1)
          assert(SimilarityCalculator.jacaard(list) == 1 )
        }

        it("is 0.5 when the list is half ones") {
          val list = List[Int](1,1,1,0,0,0)
          assert(SimilarityCalculator.jacaard(list) == 0.5 )
        }
      }
    }

    describe(".listBinarySimiarity") {


      it("""1's when the items in the list are the same and 0's otherwise""") {
        assertResult(List[Int](1,1,1,1,1)) {
          SimilarityCalculator.listBinarySimilarity(list1, list2)
        }

        assertResult(List[Int](1,0,1,0,1)) {
          SimilarityCalculator.listBinarySimilarity(list1, list3)
        }
      }

      it("allows for Nil values to be passed in") {
        assertResult(List[Int](1,0,1,0,1)) {
          SimilarityCalculator.listBinarySimilarity(list1, list4)
        }
      }
    }

    describe(".binarySimilarity") {
      describe("with jacaard") {
        describe("with lists") {
          it("calculates the jacaard similarity index of two lists") {
            assertResult(1.0) {
              SimilarityCalculator.binarySimilarity(list1,
                                                    list2,
                                                    SimilarityCalculator.jacaard)
            }
          }
        }

        describe("with vectors") {
          lazy val vec1 = DenseVector(1,0,1,1,0)
          lazy val vec2 = DenseVector(1,0,1,1,0)
          lazy val vec3 = DenseVector(1,1,1,0,0)
          // TODO: handle the nil case
          //private lazy val vec4 = DenseVector(1,Nil,1,0,0)

          it("calculates the jacaard similarity index of two lists") {
            assertResult(1.0) {
              SimilarityCalculator.binarySimilarity(vec1,
                                                    vec2,
                                                    SimilarityCalculator.jacaard)
            }
          }
        }
      }
    }
  }
}
