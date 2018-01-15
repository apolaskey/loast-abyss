#!/bin/bash

if [[$TRAVIS_BRANCH == "master" ]]; then
  echo "Pushing build to production"
  export PATH=$PATH:$HOME/.local/bin
  $(aws ecr get-login --no-include-email --region us-east-1)
  gradle dockerPush -Ddocker.repo=974758436222.dkr.ecr.us-east-1.amazonaws.com
elif [[ $TRAVIS_BRANCH == "latest" ]]; then
  echo "Pushing build to latest"
fi