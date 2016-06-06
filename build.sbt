lazy val commonSettings = Seq(
  // organization := "org.myproject"
  name := "neighborCalculator",
  version := "0.0.1"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    //mainClass := Some("Main"),
    scalaVersion := "2.11.7",

    pollInterval := 1000, // changes the time between runs when ~ run

    // NOTE: look up packaging too
    scalaSource in Compile := baseDirectory.value / "src" / "main",
    scalaSource in Test := baseDirectory.value / "src" / "test",

    resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases",

    libraryDependencies += "org.scalanlp" %% "breeze" % "0.12",
    libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6",
    libraryDependencies += "org.scalatest" %%"scalatest" % "2.2.6" % "test"


     //libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"
     //libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.5.0",
  )
