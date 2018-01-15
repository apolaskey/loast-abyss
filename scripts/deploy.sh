#!/bin/bash

if [[ "$TRAVIS_BRANCH" == "master" ]]; then
  echo "Pushing build to production"
elif [[ "$TRAVIS_BRANCH" == "latest" ]]; then
  echo "Pushing build to latest"
fi