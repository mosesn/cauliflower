package com.mosesn.cauliflower

sealed trait Column

sealed trait ArgColumn[A] extends Column {
  def apply(key: A): String
}

case class StringColumn(length: Int) extends ArgColumn[String] {
  def apply(string: String): String = if (string.length > length) {
    "%s...".format(string.substring(0, length - 3))
  } else {
    string.padTo(length, ' ')
  }
}

case class ConstantColumn(column: String) extends Column {
  def apply: String = column
}

case class IntColumn() extends ArgColumn[Int] {
  lazy val IntLength = Int.MinValue.toString.length
  def apply(int: Int): String = int.toString.padTo(IntLength, ' ')
}

case class LongColumn() extends ArgColumn[Long] {
  lazy val LongLength = Long.MinValue.toString.length
  def apply(long: Long): String = long.toString.padTo(LongLength, ' ')

}

case class FloatColumn(precision: Int) extends ArgColumn[Float] {
  def apply(float: Float): String = "%s%d%s".format("%1.", precision, "f").format(float)
}

case class DoubleColumn(precision: Int) extends ArgColumn[Double] {
  def apply(float: Double): String = "%s%d%s".format("%1.", precision, "f").format(float)
}

case class BooleanColumn() extends ArgColumn[Boolean] {
  lazy val BoolLength = false.toString.length
  def apply(bool: Boolean): String = bool.toString.padTo(BoolLength, ' ')
}

case class CharColumn() extends ArgColumn[Char] {
  def apply(char: Char): String = char.toString
}
