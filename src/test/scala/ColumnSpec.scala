package com.mosesn.cauliflower

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ColumnSpec extends FunSpec with ShouldMatchers {
  describe("StringColumn") {
    it ("Should handle exact strings") {
      StringColumn(8)("whatever") should be === "whatever"
    }
    it ("Should handle short strings") {
      StringColumn(10)("whatever") should be === "whatever  "
    }
    it ("Should handle long strings") {
      StringColumn(6)("whatever") should be === "wha..."
    }
  }

  describe("ConstantColumn") {
    it ("Should just work.") {
      ConstantColumn("column").apply should be === "column"
    }
  }

  describe("IntColumn") {
    it ("Should work for regular integers.") {
      IntColumn()(2034) should be === "2034       "
    }
    it ("Shold work for the longest integers") {
      IntColumn()(Int.MinValue) should be === Int.MinValue.toString
    }
    it ("Should work for the short integers") {
      IntColumn()(0) should be === "0          "
    }
  }

  describe("LongColumn") {
    it ("Should work for regular integers.") {
      LongColumn()(2034L) should be === "2034                "
    }
    it ("Shold work for the longest integers") {
      LongColumn()(Long.MinValue) should be === Long.MinValue.toString
    }
    it ("Should work for the short integers") {
      LongColumn()(0L) should be === "0                   "
    }
  }

  describe("FloatColumn") {
    it ("Should work for regular floats.") {
      FloatColumn(3)(0) should be === "0.000e+00 "
    }
    it ("Shold work for the longer floats") {
      FloatColumn(3)(33333333333f) should be === "3.333e+10 "
    }
    it ("Shold work for the longest floats") {
      FloatColumn(3)(Float.MinValue) should be === "-3.403e+38"
    }
    it ("Should work for the shorter floats") {
      FloatColumn(3)(3.333f) should be === "3.333e+00 "
    }
  }

  describe("DoubleColumn") {
    it ("Should work for regular floats.") {
      DoubleColumn(3)(0) should be === "0.000e+00  "
    }
    it ("Shold work for the longer floats") {
      DoubleColumn(3)(33333333333f) should be === "3.333e+10  "
    }
    it ("Shold work for the longest floats") {
      DoubleColumn(3)(Double.MinValue) should be === "-1.798e+308"
    }
    it ("Should work for the short floats") {
      DoubleColumn(3)(3.333) should be === "3.333e+00  "
    }
  }

  describe("BooleanColumn") {
    it ("Should work for false") {
      BooleanColumn()(false) should be === "false"
    }
    it ("Should work for true") {
      BooleanColumn()(true) should be === "true "
    }
  }

  describe("CharColumn") {
    it ("Should work for a char") {
      CharColumn()('a') should be === "a"
    }
  }
}
