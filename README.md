[![codebeat badge](https://codebeat.co/badges/07eeaf3d-d079-4f7f-8ba5-abf596b60f31)](https://codebeat.co/projects/github-com-gabrielbmoro-moviedbapi-master)

# Welcome!

- This repository provides an Android project that uses the library  [Movie DB API](https://www.themoviedb.org)

## Architecture

![alt text](https://github.com/gabrielbmoro/MovieDBApi/blob/master/img/architecture.svg)

## Coverage 

> _Last update 05/11/20_

| Layer                | Coverage (% classes)      |
|----------------------|:-------------------------:|
| core                 | _62%_                     |
| domain               | _100%_                    |
| presentation         | _22%_                     |
| repository           | _90%_                     |

Total: _61%_



## Stack Overview

| Type                 | Current Implementation  |
|----------------------|:-----------------------:|
| Architecture         | MVVM                    |
| Dependency Injection | Koin                    |
| Load Images          | Glide                   |
| View Binding         | Kotlin Synthatics       |
| Handler to IO calls  | Coroutines              |
| Screen Navigation    | Hard coded              |
| Unit Tests           | JUnit, Truth, Mockito   |
| UI Tests             | Expresso, Truth         |
| Network calls        | Retrofit                |
| Json converter       | Retrofit - Gson         |
| Persistence          | Room                    |

## Teaser

![alt text](https://github.com/tido4410/moviedatabaseapi/blob/master/img/teaser.gif)
