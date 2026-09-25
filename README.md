# CodeAlpha Java Gradle CI/CD

[![Java Gradle CI](https://github.com/fazeel28329/CodeAlpha-Java-Gradle-CICD/actions/workflows/gradle-ci.yml/badge.svg)](https://github.com/fazeel28329/CodeAlpha-Java-Gradle-CICD/actions/workflows/gradle-ci.yml)

A Java 21 console-based task management demonstration built with **Gradle**, tested using **JUnit Jupiter**, and continuously built and tested with **GitHub Actions**. A multi-stage **Docker** build packages the application so it can run in a container without requiring Java or Gradle on the host.

> **CI scope:** GitHub Actions automatically builds and tests the application on pushes and pull requests targeting `master`. Docker image building and container execution are currently performed manually; they are not stages of the GitHub Actions workflow.

## Features

- Prints a small task list from a Java console application.
- Uses the Gradle Wrapper for reproducible build commands.
- Includes JUnit Jupiter tests for the application's greeting method.
- Runs automated CI on GitHub-hosted Ubuntu runners with Java 21.
- Uses a multi-stage Dockerfile: JDK for building, JRE for running.

## Technologies

| Tool | Purpose |
| --- | --- |
| Java 21 | Application development |
| Gradle | Build automation and application distribution |
| JUnit Jupiter | Unit testing |
| Git & GitHub | Version control and source hosting |
| GitHub Actions | Continuous integration |
| Docker | Container image build and application execution |

## Project structure

```text
java-gradle-cicd/
├── .github/
│   └── workflows/
│       └── gradle-ci.yml
├── app/
│   ├── build.gradle
│   └── src/
│       ├── main/java/org/example/App.java
│       └── test/java/org/example/AppTest.java
├── gradle/
├── .gitignore
├── Dockerfile
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## How the application works

The console application prints a heading followed by three sample tasks:

```text
My Task Management Application
Learn Java
Learn Gradle
Build CI/CD Pipeline
```

The application also exposes a `getGreeting()` method returning `Hello World!`, which is covered by unit tests.

## Prerequisites

To build and run locally:

- **JDK 21**
- **Git**

You do **not** need to install Gradle separately because the repository includes the Gradle Wrapper. To build and run the container, install **Docker Desktop** (Windows/macOS) or **Docker Engine** (Linux).

## Get started

Clone the repository:

```bash
git clone https://github.com/fazeel28329/CodeAlpha-Java-Gradle-CICD.git
cd CodeAlpha-Java-Gradle-CICD
```

### Build

On Linux/macOS or Git Bash:

```bash
./gradlew build
```

On Windows PowerShell:

```powershell
.\gradlew.bat build
```

The `build` task compiles the application and runs its tests.

### Run the application

Linux/macOS or Git Bash:

```bash
./gradlew :app:run
```

Windows PowerShell:

```powershell
.\gradlew.bat :app:run
```

### Run tests only

Linux/macOS or Git Bash:

```bash
./gradlew :app:test
```

Windows PowerShell:

```powershell
.\gradlew.bat :app:test
```

JUnit tests check that the greeting is not null and equals `Hello World!`.

## Continuous integration with GitHub Actions

The workflow is defined in [`.github/workflows/gradle-ci.yml`](.github/workflows/gradle-ci.yml).

```text
Push or pull request targeting master
                 |
                 v
       GitHub Actions runner
                 |
                 v
         Checkout repository
                 |
                 v
       Set up Temurin Java 21
                 |
                 v
            Set up Gradle
                 |
                 v
          ./gradlew build
                 |
                 v
         Build + JUnit tests
                 |
                 v
      CI result on GitHub
```

**Triggers:** pushes to `master` and pull requests targeting `master`.

**What CI does:** checks out the code, installs Java 21, configures Gradle, and runs `./gradlew build` on an Ubuntu runner.

See the [Actions tab](https://github.com/fazeel28329/CodeAlpha-Java-Gradle-CICD/actions) for workflow runs and logs.

## Docker

The Dockerfile uses two stages:

1. **Build stage:** `eclipse-temurin:21-jdk` runs the Gradle Wrapper and creates an installable application distribution with `:app:installDist`.
2. **Runtime stage:** `eclipse-temurin:21-jre` copies only the installed application distribution and runs its generated launcher.

### Build the image

Run this from the repository root with Docker running:

```bash
docker build -t codealpha-java-gradle .
```

### Run the container

```bash
docker run --name codealpha-java-container codealpha-java-gradle
```

The program prints its tasks and exits. **`Exited (0)` is expected** because this is a console application, not a long-running web server.

### Check the container

```bash
docker ps -a
```

### Run it again

A container with the same name cannot be created twice. Remove the old stopped container first:

```bash
docker rm codealpha-java-container
docker run --name codealpha-java-container codealpha-java-gradle
```

Or create a disposable container without assigning a fixed name:

```bash
docker run --rm codealpha-java-gradle
```

### View images

```bash
docker images codealpha-java-gradle
```

## Tests and verification

| Check | Command or location | Expected result |
| --- | --- | --- |
| Local build and tests | `./gradlew build` | Build succeeds |
| Unit tests | `./gradlew :app:test` | Tests pass |
| Run locally | `./gradlew :app:run` | Task list printed |
| CI | GitHub Actions tab | Successful workflow run |
| Docker image | `docker images codealpha-java-gradle` | Image listed |
| Container | `docker ps -a` | Container shows `Exited (0)` after successful run |

## Troubleshooting

**`./gradlew: Permission denied` in CI**  
The Gradle Wrapper must be executable in Git. If needed, run:

```bash
git update-index --chmod=+x gradlew
git commit -m "Make Gradle wrapper executable"
git push origin master
```

**Docker cannot find the image**  
Make sure Docker is running and build the image from the directory containing `Dockerfile`:

```bash
docker build -t codealpha-java-gradle .
docker images codealpha-java-gradle
```

**Docker says the container name is already in use**  
Remove the previous stopped container with `docker rm codealpha-java-container` or use `docker run --rm codealpha-java-gradle`.

**Windows says `./gradlew` is not recognized**  
Use ` .\gradlew.bat ` in PowerShell.

## Future improvements

- Extend the console demo into an interactive task management application.
- Add more unit tests and test reporting.
- Add a GitHub Actions job to build and publish the Docker image.
- Add automated deployment to a target environment.

## Author

**Muhammad Fazeel Akram**  
GitHub: [@fazeel28329](https://github.com/fazeel28329)

Developed as a **CodeAlpha DevOps project**.
