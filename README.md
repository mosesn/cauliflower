# cauliflower
Pretty print columns

## motivation
because columns should be pretty

## sbt
```scala
resolvers += "sonatype" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq("com.mosesn" %% "cauliflower" % "0.1.0-SNAPSHOT")
```

## example
```scala
val seq = Seq(IntColumn, DoubleColumn(10), StringColumn(15), BooleanColumn)
val input = Seq(3, 5.0, "bleh", false)
Cauliflower.consume(input, seq)
```

## todo
HList?
Good use case for ScalaCheck

## contributors
[Moses Nakamura](http://github.com/mosesn)