# GitHubClient
A simple GitHub client which allows the user to search for users and view their repositories and the pull requests and issues attached to them.
The app is modularized and is using the Clean architecture for code structuring. Communication between components is implemented using the MVVM architecture.

The repo list is allows the user to load repos by pages.

The implemented requirements are listed below
Below the Requirements are listed some TODOs that given time I would like to work on

### Requirements
Build an Android app written in Kotlin that will use GraphQL Github API to download list of repos for arbitrary user (can be hardcoded to github.com/toptal). The items on the list should display the title and URL of each repo.
Tapping an item on the list should display a details screen with the title of the corresponding item, number of open and closed issues and pull requests (with corresponding titles). This doesn’t need to look beautiful in terms of UI.
At least some components should be unit tested.

#### Done

  Added a screen to authorize with GitHub
  
  Added a screen to search for users (with error handling for errors from API)
  
  Added a screen with a repo list for the queried user (with error handling for errors from API)
  
  Added a screen which shows the pull requests, open and cloesd issues for the selected repo from the list
  
  Added unit testint for UserSelectViewModel and CheckUserUseCase

#### TODO

∙ Add network state check before every request (usually I would do this using an extension function for the Activity class and check before every request)

∙ Work a bit on readability on some portions of the code

∙ Implement extensive unit testing. Right now, because of time constraints, the unit tests are somewhat naive and limited to just being a showcase of how I usually do them. They can be found in the presentation and domain modules. This is a pretty simple app, so the testing for the different viewmodels and usecases (which are the most sensible components to test) is going to be very similar

∙ Work a bit on UI and UX as currently it's not optimal and pretty ugly

∙ Work on optimizations (memory and time) where applicable
