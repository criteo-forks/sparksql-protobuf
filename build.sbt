name := "sparksql-protobuf"
organization := "com.github.saurfang"

scalaVersion := "2.10.5"
crossScalaVersions := Seq("2.10.5", "2.11.6")

scalacOptions ++= Seq("-target:jvm-1.7" )
javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

enablePlugins(GitVersioning)
git.baseVersion := "0.1.2"

resolvers ++= Seq(
  "Twitter Maven" at "https://maven.twttr.com"
)

libraryDependencies ++= Seq(
  "org.apache.parquet" % "parquet-protobuf" % "1.8.1" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

PB.protocVersion := "-v250"
inConfig(Test)(sbtprotoc.ProtocPlugin.protobufConfigSettings)
val generatedDef = (sourceDirectory in Test)(_ / "generated")
PB.targets in Test := Seq (
  PB.gens.java -> generatedDef.value
)
javaSource in Test <<= generatedDef

parallelExecution in Test := false

coverageExcludedPackages := ".*ProtoLIST.*"

spName := "saurfang/sparksql-protobuf"
sparkVersion := "1.3.0"
sparkComponents += "sql"
credentials ++= {
  val host = publishTo.value match {
    case Some(m: MavenRepository) =>
      new URL(m.root).getHost
    case other =>
      streams.value.log.warn(s"Unrecognized publishTo value: $other")
      "oss.sonatype.org"
  }

  Seq("MAVEN_USER", "MAVEN_PASSWORD").map(sys.env.get) match {
    case Seq(Some(user), Some(pass)) =>
      Seq(Credentials("Sonatype Nexus Repository Manager", host, user, pass))
    case _ =>
      Seq()
  }
}
licenses += "Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")
publishArtifact in (Compile, packageDoc) := false
publishTo := {
  sys.env.get("DEPLOY_TO").map(_.split(":", 2)).flatMap {
    case Array(name, url) =>
      Some(name at url) // e.g. DEPLOY_TO="local-nexus:http://localhost:10002"
    case _ =>
      None
  }
}
spAppendScalaVersion := true
