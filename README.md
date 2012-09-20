# cauliflower
Pretty print columns

## motivation
because columns should be pretty

## todo
To construct the column seq  
```scala
val seq = Seq(IntColumn, DoubleColumn(10), StringColumn(15), BooleanColumn)
```

To consume the input  
```scala
val input = Seq(3, 5.0, "bleh", false)
Cauliflower.consume(input)
```
or
```scala
val input = Tuple4[Int, Double, String, Boolean](3, 5.0, "bleh", false)
Cauliflower.consume(input)
```
or  
JSON  
or  
XML  
or  
BSON  
or  
???