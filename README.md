# jenkins-pipeline-utils [JENKINS-48938](https://issues.jenkins-ci.org/browse/JENKINS-48938)

Jenkins pipeline utilities testing for [JENKINS-48938](https://issues.jenkins-ci.org/browse/JENKINS-48938).
BuildData does not track the SCM name of the repository for which it is recording the SHA1 of the commit.
Comparison short circuits before traversing all repositories.
Polling then detects changes on every polling cycle, even when there is no change.
