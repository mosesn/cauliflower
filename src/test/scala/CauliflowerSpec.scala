package com.mosesn.cauliflower

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CauliflowerSpec extends FunSpec with ShouldMatchers {
  describe("Cauliflower") {
    describe("consume") {
      val input = Seq(3, 5.0f, "bleh", false)
      val columns = Seq(IntColumn(), FloatColumn(2), StringColumn(5), BooleanColumn())
      val result = "3           5.00e+00  bleh  false"
      it ("Should properly eat a seq") {
        Cauliflower.consume(input, columns) should be === result
      }
      it("Should properly eat a stream of seq") {
        Cauliflower.consume(Stream(input), columns) should be === Stream(result)
      }
    }
  }
}
