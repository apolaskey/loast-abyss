#!/bin/bash

pip install --user awscli
gradle docker
export PATH=$PATH:$HOME/.local/bin
$(aws ecr get-login --no-include-email --region us-east-1)

echo "$TRAVIS_PULL_REQUEST"

if [[ "$TRAVIS_BRANCH" == "master" ]] && [[ "$TRAVIS_PULL_REQUEST" = "false" ]]; then
  echo "Pushing build to production"
  gradle dockerPush -Ddocker.repo=974758436222.dkr.ecr.us-east-1.amazonaws.com
elif [[ "$TRAVIS_BRANCH" == "master" ]] && [[ "$TRAVIS_PULL_REQUEST" = "true" ]]; then
  echo "Pushing build to latest"
fi