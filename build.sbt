organization := "com.mosesn"

name := "cauliflower"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "1.7.1" % "test",
                    "junit" % "junit" % "4.8.1" % "test")

scalacOptions += "-deprecation"

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

publishArtifact in Test := false

pomExtra := (
  <url>http://github.com/mosesn/cauliflower</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:mosesn/cauliflower.git</url>
    <connection>scm:git:git@github.com:mosesn/cauliflower.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mosesn</id>
      <name>Moses Nakamura</name>
      <url>http://github.com/mosesn</url>
    </developer>
  </developers>)
