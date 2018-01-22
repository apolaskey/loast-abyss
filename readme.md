# Lost Abyss

Design Focus
 - [ ] Solo Oriented player experience
 - [ ] Finite named equipment
 - [ ] Multi-classing
 - [ ] PK allowed
 - [ ] Newbie Friendly
 - [ ] 120 char console

## Building and Running from Source
Several build tools / SDK's will need to be installed if not already present
 - Gradle
 - Java 8 SDK
 - Postgres
 
Tooling setup (Optional, if you haven't don't have the above)
 - Windows
   - Install Chocolately (https://chocolatey.org/)
   - `choco install gradle`
   - `choco install jdk8`
   - Install Docker for Windows (https://www.docker.com/docker-windows)
     - `docker install postgres`
   - `choco install postgresql` (Alternative to using Docker)
 - macOSX
   - Install homebrew (https://brew.sh/)
   - `brew install gradle`
   - `brew install jdk8`
   - Install Docker for Mac (https://www.docker.com/docker-mac)
     - `docker install postgres`

Building
 - Clone this repository
 - Navigate to where `build.gradle` is located
 - Run `gradle build`

Running game server locally
 - Navigate to `lost-abyss`
 - (Recommended) Run `gradle bootRun`
 - (Optional) Run like production `gradle startMud`
   - This is a simple task that executes the following tasks
     - `gradle dockerStop && gradle dockerRemoveContainer && gradle docker && gradle dockerPush && gradle dockerRun`

## Building and Running from Docker
 - TODO

## Project Reasoning

There are a variety of reasons as to why this project was started, mainly to explore
some of the more modern technologies out in the wild and to become familiar with
some persistence related technologies.

The following technologies are utilized outside of the build setup for hosting and such.

Tools & Technologies Overview
 - **AWS CLI (ECS, EC2, S3)** - For deployment of this application
    - Nothing actually in this project "depends" on it, it's just what we use for infra
 - **Travis CI** - Our continuous integration service
    - Reliable and easy to use
 - **Postgres** - Persistence solution
    - Picked as it's largely the most recommended RDBMS application
 - **Spring Framework** - Core application framework for development
    - Literally used everywhere, pluggable and useful to know
 - **Docker** - Container solution
    - Widely used and large community support
 
 Opinionated Spring Customizations
  - **Undertow** - Touted as one of the fastest backing servlet containers and reliable under high throughput
    - [Source](https://www.techempower.com/benchmarks/#section=data-r14&hw=ph&test=fortune)
  - **HikariCP** - One of the best JVM connection pools
    - [Source](https://github.com/brettwooldridge/HikariCP/wiki/%22My-benchmark-doesn't-show-a-difference.%22)