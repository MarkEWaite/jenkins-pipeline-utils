# tests/largeFileSupport branch for git client plugin tests

The Jenkins git-client-plugin large file support tests need a
repository which contains a branch named tests/largeFileSupport.
It contains multiple large files with string content as follows:
* "uuid.txt" - 5e7733d8acc94636850cb466aec524e4
* "uuid2.txt" - c49d89a61c3411e9a5555b2af3892239
* "uuid,3.txt" - 6fd3199a1cb111e9bbaf537fc996ab9b
* "uuid 4.txt" - 803219bc1cb111e9abbfb7cc7b029948

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
