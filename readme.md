# Lost Abyss

Design Focus
 - [ ] Solo Oriented player experience
 - [ ] Finite named equipment
 - [ ] Multi-classing
 - [ ] PK allowed
 - [ ] Newbie Friendly

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

Running game server
 - Navigate to `lost-abyss`
 - Run `gradle bootRun`
 - Optional run from Docker `gradle docker && gradle dockerRun`
   - To stop `gradle dockerStop && gradle dockerRemoveContainer`

## Building and Running from Docker
 - TODO

## Project Reasoning

There are a variety of reasons as to why this project was started, mainly to explore
some of the more modern technologies out in the wild and to become familiar with
some persistence related technologies.

The following technologies are utilized outside of the build setup for hosting and such.

Tools & Technologies Overview
 - **AWS CLI (ECS, EC2, S3)** - For deployment of this application
 - **Travis CI** - Our continuous integration service
 - **REDIS** - For active session state management
 - **Postgres** - Persistence solution, decided over NoSQL variants for MUD feature requirements
 - **Spring Framework** - Mature and Stable web framework, widely used across enterprises
 - **Docker** - Container solution, widely used