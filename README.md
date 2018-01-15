# jenkins-pipeline-utils [JENKINS-48938](https://issues.jenkins-ci.org/browse/JENKINS-48938)

Jenkins pipeline utilities testing for [JENKINS-48938](https://issues.jenkins-ci.org/browse/JENKINS-48938).
BuildData does not track the SCM name of the repository for which it is recording the SHA1 of the commit.
Comparison short circuits before traversing all repositories.
Polling then detects changes on every polling cycle, even when there is no change.

When changes are pushed to a pipeline util in a GitHub repository monitored by the GitHub Branch Source plugin, pipeline library changes are detected on the next scan of the repository.
When changes are pushed to a pipeline util in a GitHub repository monitored by the Git branch source, pipeline library changes are **not** detected, either by polling or by repository scan.

I thought I had found a case where global pipeline library changes were detected and caused a build of jobs which use that branch of the library.
Apparently, I was wrong.
