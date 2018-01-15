#!/bin/bash

pip install --user awscli
gradle docker
export PATH=$PATH:$HOME/.local/bin
$(aws ecr get-login --no-include-email --region us-east-1)

echo $TRAVIS_BRANCH

if [[ $TRAVIS_BRANCH == "master" ]]; then
  echo "Pushing build to production"
elif [[ $TRAVIS_BRANCH == "latest" ]]; then
  echo "Pushing build to latest"
fi