Rick And Morty Android App
===============================

## Vision 
Our vision for the Rick and Morty app is to create an ultimate platform for fans to explore and experience the multidimensional world of Rick and Morty. Our app will provide an interactive and engaging experience that captures the spirit of the show while also providing valuable content and features for fans to enjoy.

With the Rick and Morty app, fans will have access to a comprehensive database of characters, episodes, and locations that make the show so iconic. In the Rick and Morty Characters section, users will be able to view details such as name, status, species, type, gender, origin, locations, episodes, and a large picture of a character they want to see. Users will also be able to filter characters by gender (male, female, genderless, unknown) or status (dead, alive, unknown).

In the Rick and Morty Episodes section, users can explore episode details such as the name, episode code (e.g. S01E01), air date, and a list of characters featured in each episode. This section will enable fans to delve deeper into the show's universe and understand the intricacies of the storyline.

Similarly, the Rick and Morty Location section will provide users with a comprehensive list of all the locations in the show, along with details such as name, type, dimension, and a list of residents (characters) in each location.

Lastly, the Search section will allow users to search for anything by typing the name of a character or the name/type of a location. Users can click on their choice to get more information about the character or location.

Our goal is to provide an enjoyable and engaging experience that caters to the needs of all Rick and Morty fans. The Rick and Morty app will serve as a one-stop-shop for all things Rick and Morty on Android, providing exclusive content and interactive experiences that can't be found anywhere else. Whether you're a die-hard fan or a casual viewer, the Rick and Morty app is the ultimate destination for exploring and experiencing the multidimensional world of Rick and Morty.

## Branching Strategy
We are following a strategy similiar to the Git branching strategy. The two main branches to be aware of are:

1. The `main` branch.
    * Where fully working releases for each iteration will be.

2. The `dev` branch.
    * Where current development is being done.

In addition to these two main branches, each new feature will have their own branch that is created from the `dev` branch. The specific names of these branches are determined by the ticket number from `Jira` followed by feature name. Once that branch has its work completed, it will then be merged into the `dev` branch and deleted.

## Architecture
* The architecture used is [Clean Architecture](https://www.geeksforgeeks.org/what-is-clean-architecture-in-android/)

## How to Setup 
1. Go to [GitHub](https://github.com/) and open the [Rick and Morty repository](https://github.com/EON-Incubator/RickAndMorty-Android.git)
2. You can either download the Zip or clone the repository to your local machine to work on the project.
    * To clone repository from your local machine:         
      1. Click on *Code* which is located in top-right of the github repository and copy the link.
      2. Then install git on your local machine from this [link](https://git-scm.com/downloads). 
      3. Once the git is installed, open *Git Bash*.
      4. Clone the repository by typing ```git clone [link]```.
3. After downloading the repository, the next step is to [download](https://developer.android.com/studio/?gclid=Cj0KCQiAjJOQBhCkARIsAEKMtO3zEhdK4_I0CEZic3UH4dl-9gVXuHFR9dCl3TOHKjmv3xWLU3UxfhYaApfAEALw_wcB&gclsrc=aw.ds) and install Android Studio. 
    * You can follow the [steps](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-1-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-install-android-studio) to download and install Android Studio in your machine. 
4. Open Android Studio and select `Open an existing Android Studio project` from the Welcome screen.
5. Navigate to the directory where you cloned the repository and select the `build.gradle` file for the app module.
6. Android Studio will import the project and build the Gradle files automatically. If there are any missing dependencies, Android Studio will prompt you to download them.
7. Once the project is imported, You can then setup Android Emulator to run the app using the steps in this [link](https://developer.android.com/codelabs/basic-android-kotlin-compose-emulator?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-1-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-emulator#2)
8. Finally, you can run it by clicking the "Run" button in the Android Studio toolbar. You can choose a device or emulator to run the app on. 

## Development Instructions for Incubator
Final project of EON Mobile Incubator W23

Mobile app that consume the public RickyAndMorty GraphQL
  - URL - https://rickandmortyapi.com/graphql
  - Docs - https://rickandmortyapi.com/documentation

## How to Run Tests
1. Make sure your device is turned on(Emulator or Physical).
2. Get `Pixel 4 API 26` in Emulator to run test using the command below. 
3. Run command `./gradlew createDebugCoverageReport` in Android Studio `terminal` which is located on the bottom bar.
4. It will run all UI behaviour tests and system tests.
5. Once it is done, it will generate a test coverage report in `build/reports/coverage` or you can copy/paste the link `terminal` provides to any browser to see the coverage.
6. To run UI tests locate `app/src/test [unitTes]`, right click and `run test with coverage`
7. You can also check `ManualTestsCoverage` excel file in the project directory to see the coverage of the overall project. 

**Ground Rules**
* You must use Kotlin
* You must use MVVM as the app architecture
  * You are free to use other patterns that build on top of MVVM (e.g. MVVM+Coordinators)
* You must use Jetpack Compose to create your views
* Your implementation should use asynchronous and reactive programming (i.e. Flows, Coroutines)
* You must use Apollo GraphQL to interact with the Ricky and Morty GraphQL API
* You should have test coverage over 70% on your view models
  * You should explore both Unit and BDD testing
* You must setup KTLint locally and add it as a pre-commit hook to ensure your code is being linted
  * Read more about git hooks here https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks
  * KTLint must also be setup as a Github Check to run on all pull requests and this check must pass for a PR to be merged

## Learn More
*  [Build and run your App in Android Studio](https://developer.android.com/studio/run)
*  [Run App on hardware device](https://developer.android.com/studio/run/device)

## Contributors
* [Mansimar Singh Bhasin](https://github.com/mansimars)
* [Bakhshish Singh Dhillon](https://github.com/bakhshish-singh-dhillon)
* [John Matas](https://github.com/JanDee042918) 
* [Jashanpreet Singh](https://github.com/jashan-786) 

