package com.mosesn.cauliflower

object Cauliflower {
  def consume(stream: Stream[Seq[Any]], columns: Seq[Column]): Stream[String] =
    stream map (consume(_, columns))

  def consume(seq: Seq[Any], columns: Seq[Column]): String =
    listConsume(seq, columns) mkString " "

  private[this] def listConsume(seq: Seq[Any], columns: Seq[Column]): List[String] =
    seq.headOption -> columns.headOption match {
      case (None, None) => Nil
      case (None, Some(head)) => if (isAllConstant(columns)) {
        printConstants(columns)
      }
      else {
        throw new IllegalArgumentException("Not enough arguments for the columns.")
      }
      case (Some(_), None) =>
        throw new IllegalArgumentException("Too many arguments for the columns.")
      case (Some(input), Some(format)) => format match {
        case col @ ConstantColumn(_) => col.apply :: listConsume(seq, columns tail)
        case other: ArgColumn[_] => parse(seq, columns)
      }
    }

  private[this] def parse(seq: Seq[Any], columns: Seq[Column]): List[String] = (columns.head match {
    case col @ IntColumn() =>
      col(seq.head.asInstanceOf[Int])
    case col @ LongColumn() => ""
      col(seq.head.asInstanceOf[Long])
    case col @ FloatColumn(_) => ""
      col(seq.head.asInstanceOf[Float])
    case col @ DoubleColumn(_) => ""
      col(seq.head.asInstanceOf[Double])
    case col @ StringColumn(_) => ""
      col(seq.head.asInstanceOf[String])
    case col @ BooleanColumn() => ""
      col(seq.head.asInstanceOf[Boolean])
    case col @ CharColumn() => ""
      col(seq.head.asInstanceOf[Char])
  }) :: listConsume(seq tail, columns tail)

  private[this] def isAllConstant(columns: Seq[Column]): Boolean =
    (columns isEmpty) || (columns head match {
      case ConstantColumn(_) => isAllConstant(columns tail)
      case _ => false
    })

  private[this] def printConstants(columns: Seq[Column]): List[String] = columns head match {
      case col @ ConstantColumn(_) => col.apply :: printConstants(columns tail)
      case _ => throw new IllegalArgumentException("This won't happen")
  }
}
