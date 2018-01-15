#!/bin/bash

echo "Branch: $TRAVIS_BRANCH isPushRequest: $TRAVIS_PULL_REQUEST"

pip install --user awscli
gradle docker
export PATH=$PATH:$HOME/.local/bin
$(aws ecr get-login --no-include-email --region us-east-1)

if [[ ( $TRAVIS_BRANCH == "master" && $TRAVIS_PULL_REQUEST == "false" ) ]]; then
  echo "Pushing build to production"
  gradle dockerPush -Ddocker.repo=974758436222.dkr.ecr.us-east-1.amazonaws.com
elif [[ ( $TRAVIS_BRANCH == "latest" && $TRAVIS_PULL_REQUEST == "false" ) ]]; then
  echo "Pushing build to latest"
else
  echo "Skipping deploy, no defined environment"
fi