// put this at the top of the file
import scalariform.formatter.preferences._

// Resolvers
resolvers ++= Seq(
  "scalaz bintray" at "https://dl.bintray.com/scalaz/releases",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

// Dependencies

val testDependencies = Seq (
)

val rootDependencies = Seq(
  "org.typelevel" %% "cats" % "0.4.1",
  // Plugins
  //
  compilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")
)

val dependencies =
  rootDependencies ++
  testDependencies

// Settings
//
val compileSettings = Seq(
  "-feature",
  "-deprecation",
  "-encoding", "utf8",
  //"-language:postfixOps",
  //"-language:higherKinds",
  //"-language:implicitConversions",
  "-language:_",
  "-target:jvm-1.8",
  "-Ybackend:GenBCode",
  "-Ydelambdafy:method",
  "-unchecked",
  "-Xcheckinit",
  "-Xfuture",
  "-Xlint",
  //"-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  "-Xfuture"/*,
  "-Yno-imports",
  "-Yno-predef"*/
)

val forkedJvmOption = Seq(
  "-server",
  "-Dfile.encoding=UTF8",
  "-Duser.timezone=GMT",
  "-Xss1m",
  "-Xms2048m",
  "-Xmx2048m",
  "-XX:+CMSClassUnloadingEnabled",
  "-XX:ReservedCodeCacheSize=256m",
  "-XX:+DoEscapeAnalysis",
  "-XX:+UseConcMarkSweepGC",
  "-XX:+UseParNewGC",
  "-XX:+UseCodeCacheFlushing",
  "-XX:+UseCompressedOops"
)

val formatting =
  FormattingPreferences()
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, false)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40)
    .setPreference(CompactControlReadability, false)
    .setPreference(CompactStringConcatenation, false)
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(FormatXml, true)
    .setPreference(IndentLocalDefs, false)
    .setPreference(IndentPackageBlocks, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
    .setPreference(PreserveSpaceBeforeArguments, false)
    .setPreference(PreserveDanglingCloseParenthesis, true)
    .setPreference(RewriteArrowSymbols, false)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesWithinPatternBinders, true)

val pluginsSettings =
  scalariformSettings

val settings = Seq(
  name := "freez",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  libraryDependencies ++= dependencies,
  fork in run := true,
  fork in Test := true,
  fork in testOnly := true,
  connectInput in run := true,
  javaOptions in run ++= forkedJvmOption,
  javaOptions in Test ++= forkedJvmOption,
  scalacOptions := compileSettings,
  // formatting
  //
  ScalariformKeys.preferences := formatting
)

lazy val main =
  project
    .in(file("."))
    .settings(
      pluginsSettings ++ settings:_*
    )
