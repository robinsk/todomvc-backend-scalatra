import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._
import com.typesafe.startscript._

object HerokuExampleBuild extends Build {
  val Organization = "no.knowit"
  val Name = "todomvc-scalatra-backend"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.2"
  val ScalatraVersion = "2.2.2"


  lazy val project = Project (
    "todomvc-scalatra-backend",
    file("."),
    settings = StartScriptPlugin.startScriptForClassesSettings ++ Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "com.basho.riak" % "riak-client" % "1.1.0",
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "compile;container",
        "org.scalatra"            %% "scalatra-json"    % "2.2.2",
        "org.json4s"  %% "json4s-jackson"  % "3.1.0",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "compile;container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}



