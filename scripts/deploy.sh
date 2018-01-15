#!/bin/sh

if [[ "$TRAVIS_BRANCH" == "master" ]]; then
  echo "Pushing build to production"
  pip install --user awscli
  export PATH=$PATH:$HOME/.local/bin
  $(aws ecr get-login --no-include-email --region us-east-1)
  gradle docker
  gradle dockerPush -Ddocker.repo=974758436222.dkr.ecr.us-east-1.amazonaws.com
fi