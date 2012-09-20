package com.mosesn.cauliflower

object Cauliflower {
  def consume(seq: Seq[Any], columns: Seq[Column]): String = seq.headOption match {
    case None => if (isAllConstant(columns)) {
      ""
    }
    else {
      throw new IllegalArgumentException("Not enough arguments for the columns.")
    }
    case Some(head) => ""
  }

  def consume(stream: Stream[Seq[Any]], columns: Seq[Column]): Stream[String] =
    stream map (consume(_, columns))

  private[this] def isAllConstant(columns: Seq[Column]): Boolean =
    (columns isEmpty) || (columns head match {
      case ConstantColumn(_) => isAllConstant(columns tail)
      case _ => false
    })

  private[this] def printConstants(columns: Seq[Column]): String = columns head match {
      case col @ ConstantColumn(_) => col + printConstants(columns tail)
      case _ => throw new IllegalArgumentException("This won't happen")
  }
}
