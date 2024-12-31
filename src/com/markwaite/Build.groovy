package com.markwaite;

/* Run ant from tool "ant-latest" using tool 'jdk21' */
void ant(def args) {
  /* Get jdk tool. */
  String jdktool = tool name: 'jdk21', type: 'hudson.model.JDK'

  /* Get the ant tool. */
  def antHome = tool name: 'ant-latest', type: 'hudson.tasks.Ant$AntInstallation'

  /* Set JAVA_HOME, and special PATH variables. */
  List javaEnv = [
    "PATH+JDK=${jdktool}/bin", "JAVA_HOME=${jdktool}", "ANT_HOME=${antHome}",
  ]

  /* Call ant tool with java envVars. */
  withEnv(javaEnv) {
    if (isUnix()) {
      sh "${antHome}/bin/ant ${args}"
    } else {
      bat "${antHome}\\bin\\ant ${args}"
    }
  }
}

/* Run maven from tool "mvn" */
void mvn(def args) {
  /* Get jdk tool. */
  String jdktool = tool name: "jdk7", type: 'hudson.model.JDK'

  /* Get the maven tool. */
  def mvnHome = tool name: 'mvn'

  /* Set JAVA_HOME, and special PATH variables. */
  List javaEnv = [
    "PATH+JDK=${jdktool}/bin", "JAVA_HOME=${jdktool}",
  ]

  /* Call maven tool with java envVars. */
  withEnv(javaEnv) {
    timeout(time: 45, unit: 'MINUTES') {
      if (isUnix()) {
        sh "${mvnHome}/bin/mvn ${args}"
      } else {
        bat "${mvnHome}\\bin\\mvn ${args}"
      }
    }
  }
}

def getSHA1(def commit) {
  if (isUnix()) {
    sha1 = sh(script: "git rev-parse ${commit}", returnStdout: true)
  } else {
    // Windows treats caret as special character, must escape it
    if (commit.contains("^")) {
      commit = commit.replace("^", "^^")
    }
    // Windows returns command line before sha1 unless we ECHO OFF prior
    sha1 = bat(script: "@ECHO OFF && git rev-parse ${commit}", returnStdout: true)
  }
  // Remove white space
  sha1 = sha1.replaceAll("\\s", "")
  if (sha1.length() > 40) {
    sha1 = sha1.substring(sha1.length() - 40)
  }
  return sha1
}
