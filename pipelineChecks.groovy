/**
 * Various assertions for Jenkinsfile use
 */

import com.cloudbees.groovy.cps.NonCPS

def version = '1.0'

@NonCPS
def dumpEnvVars() {
  def str = "Dumping build environment variables...\n"
  for (Map.Entry<String, String> entry : currentBuild.build().environment) {
    str += "    ${entry.key} = ${entry.value}\n"
  }
  echo str
}

/* Assert build log contains string matching regex */
def logContains(String regex, String failure_message) {
  if (!manager.logContains(regex)) {
    manager.addWarningBadge(failure_message)
    manager.createSummary("symbol-shield-warning").appendText(failure_message)
    manager.buildUnstable()
  }
}

/* Assert build log does not contain string matching regex */
def logDoesNotContain(String regex, String failure_message) {
  if (manager.logContains(regex)) {
    manager.addWarningBadge(failure_message)
    manager.createSummary("symbol-shield-warning").appendText(failure_message)
    manager.buildUnstable()
  }
}

return this;
