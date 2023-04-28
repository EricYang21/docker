# Integrating Retrofit Supply Chains

![maven CI badge](https://github.com/spe-uob/2022-RetrofitSupplyChains/actions/workflows/maven-tests.yml/badge.svg)
![google-java-format CI badge](https://github.com/spe-uob/2022-RetrofitSupplyChains/actions/workflows/java-formatting.yml/badge.svg)
![integration tests CI badge](https://github.com/spe-uob/2022-RetrofitSupplyChains/actions/workflows/integration.yml/badge.svg)

## Table of Contents
- [Project Description](#project-description)
- [Usage](#Usage)
- [Group members](#Contributors)
- [Stakeholders](#Stakeholders)
- [User Stories](#user-stories)


## Project Description
>Develop a supply chain integration solution for **Bristol Energy Network(BEN)**,
> where BEN officers or the retrofit clients themselves can look up tradespeople according to their abilities,
> location and ratings without having to compare across different websites.

## Usage
This project uses Spring, a Java web framework, in the backend. The frontend uses React and must be
built with Babel.

### Getting started
You will need the following dependencies installed on your system:
 - Java 17
 - Maven
 - npm

Get the source code of the project by cloning the repository. If you just want to run it, you can
download an archive from GitHub instead. This will not include the project's history and will not
allow you to submit changes.

Then, in the root directory of the project, run `npm install`. This will fetch dependencies for
building the frontend.

### Running the application
Make sure you are in the project's root directory, and run the following:
```sh
NODE_ENV=development npx babel js --out-dir static/js # builds the frontend into browser-compatible JS
mvn spring-boot:run # starts the backend, which is also responsible for serving the frontend over HTTP
```

To use the application, navigate to `http://127.0.0.1:8080` in your browser.

### Backend demo
You can run the backend's tests to see it fetching contractor data from various sources. In the
project's root directory, run `mvn clean test`. This will output a lot of build logs, but at the end
of the output there will be details of various contractors corresponding to some example search
terms.

### Development
Make sure you are in the project's root directory, and run `dev.sh`. Keep it running while you make
changes to the frontend. If you make changes to the backend, restart the script. It watches for
changes to the JavaScript source and recompiles it when they happen, but it does not watch the Java
source.

This will serve the frontend on `http://127.0.0.1:8080`. Reload the page after making frontend
changes so that your browser will pick them up.

## Stakeholders
- BEN

  - BEN will need to teach how to use the website effectively to its officers,
  so the website needs to be intuitive for them to teach and use.
  - BEN will also need to maintain the website themselves once our project is finished,
  so the code needs to be well documented and ready for future expansion
- BEN officers / Potential retrofit clients

  - BEN officers / Potential retrofit clients will need to use the website to find the appropriate retrofit clients, 
  so they would want to be able to specify their needs/situation.
- Retrofit provider

  - Retrofit providers would like to have more clients, so they would want our website to aggregate websites that
  contain their information such that they will be discovered more easily.

## Contributors
- Rose Hudson
- Douglas Fong Tak sum 
- Min He (Mindy)
- Yupeng Yang (Eric)

## User Stories

#### BEN officer/Potential retrofit clients
- I want to be able to find tradespeople easily, so that I can fulfill my clients needs.
- While searching for tradespeople, I want to be able to check the qualifications for said tradespeople such that the client can apply for the appropriate grant.
- Additionally, I want to be able to search for feedback of those tradespeople so that I can gain a better understanding of their abilities.

#### Retrofit provider
- As a retrofit provider, I would like our company/myself to be discovered easily such that I can have more potential clients.

