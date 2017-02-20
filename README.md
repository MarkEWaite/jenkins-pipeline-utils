# tests/largeFileSupport branch for git client plugin tests

The Jenkins git-client-plugin large file support tests need a
repository which contains a branch named tests/largeFileSupport and
which contains a large file named uuid.txt that contains the string
5e7733d8acc94636850cb466aec524e4.  

The GitHub billing rules require that if a forked repository uses large
files, then the upstream repository must use large files, and the GitHub
data rate and large file licensing applies to the upstream repository,
not to the fork.  Large file billing is at the repository owner level,
so that would require the jenkinsci organization (with 1000+ repositories)
to purchase GitHub data packs for the organization.

Rather than require that the jenkinsci organization purchase GitHub data
packs before they are actually needed by other projects, the git client
plugin tests have been modified to use my GitHub data packs by using
this independent repository.
