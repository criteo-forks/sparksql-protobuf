addSbtPlugin("com.thesamet" % "sbt-protoc" % "0.99.15")
libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.7.0"

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

resolvers += Resolver.url("sbt-scoverage repo", url("https://dl.bintray.com/sksamuel/sbt-plugins"))(Resolver.ivyStylePatterns)
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.0")

resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"
addSbtPlugin("org.spark-packages" % "sbt-spark-package" % "0.2.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.4")
