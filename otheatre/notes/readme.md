# Code profiling

Create in /src/main/resources/config/application-<profile>.properties
Configs can also be written using yml, application-marius.yml
application.properties config file will always be used!
common config

mvn spring-boot:run -Dspring-boot.run.profiles=marius

# Steps

When starting on a feature:

git checkout develop
git fetch -p
git pull -p
git branch feature/<feature>
git checkout feature/<feature>

During work on feature

Commits
git add .
git commit -m "Mesaj"
git push


Feature is done
git checkout develop
git fetch -p
git pull -p
git checkout feature/<feature-branch-ul pe care am lucrat>
git rebase develop
    git rebase --abort
solve some conflicts
git rebase --continue
git push  -f

git rebase -i develop
squash commits
git push -f



