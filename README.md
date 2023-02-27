# My Radio France
![Header image](../main/.github/banner.png?raw=true)

MyRadioFrance est une application Android qui vous permet d'accéder facilement aux émissions de radio de différentes stations françaises.

## Introduction
L'application comporte deux écrans principaux :
- **La liste des stations radio** vous permet de parcourir les différentes disponibles et de sélectionner celle que vous souhaitez découvrir.
- **La liste des émissions** vous permet de consulter les émissions de chaque station.

## Screenshots
| **Light stations** | **Light émissions** | **Dark stations** | **Dark émissions** |
| :------------: | :------------: | :------------: | :------------: |
| ![LIGHT_HOME](../main/.github/stations_light.png?raw=true) | ![LIGHT_SHOWS](../main/.github/shows_light.png?raw=true) | ![DARK_HOME](../main/.github/stations_dark.png?raw=true) | ![DARK_SHOWS](../main/.github/shows_dark.png?raw=true) |

#### Défilement du texte
Il est possible de défiler horizontalement pour lire toute la description d'une émission.

![](../main/.github/text_marquee.gif?raw=true)

### Scroll infini
L'application charge et affiche les émissions au fur et à mesure que vous scrollez en utilisant le **curseur** présent dans l'API.

![](../main/.github/infinite_scroll.gif?raw=true)

## Choix techniques
Le projet utilise le **Design Pattern MVVM**, les frameworks **Jetpack Compose** et **Jetpack Navigation**, et **Hilt** pour l'injection de dépendances.

La bibliothèque [**ApolloGraphQL**](https://www.apollographql.com/) est utilisée pour intéragir avec l'API.

La bibliothèque de test **Mockito** est utilisée pour créer des objets simulés (mocks) lors de l'écriture de tests unitaires.

Le projet a été conçu avec l'aspect **Scalability** en tête, d'où l'application de la **Clean Architecture** et autres bonnes pratiques telles que la conception de **composants UI réutilisables** et même le suivi des rêgles de collaboration pour les [**Conventional Commits**](https://www.conventionalcommits.org/).

## Test
Les tests unitaires présents dans le projet testent principalement les deux ViewModels gérant les écrans des stations et émissions.

Les tests couvrent la réception de données en cas de réussite et d'échec.
