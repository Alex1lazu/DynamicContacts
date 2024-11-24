# Dynamic Contacts

Dynamic Contacts App is an Android app built using **Jetpack Compose** and **Kotlin**. It displays a list of users fetched from a remote API and allows for infinite scrolling. It includes a top bar, user entry components, and dynamic data loading when scrolling near the end of the list.

## Features
- **Infinite Scrolling**: The app loads more data when the user scrolls near the last 3 items.
- **User List**: Displays a list of users with their name, registration age, and picture.
- **Loading State**: A loading indicator is shown when the app is fetching data.
- **Hilt for Dependency Injection**: Uses Hilt for dependency injection to provide the necessary data and view models.
- **Kotlin Flow**: Implements Kotlin Flow for managing state and handling asynchronous data updates.

## Architecture
The app follows a **clean architecture** with a MVVM approach with a clear separation of concerns:
- **Presentation Layer**: Contains the UI code written using Jetpack Compose.
- **ViewModel Layer**: Manages the UI-related data and acts as a mediator between the UI and the repository.
- **Data Layer**: Handles data fetching from the remote API and local storage, abstracted by repositories.

## Directories
- **components**: Contains UI components 
- **data**: Manages User related data in the UserRepository
- **presentation**: Contains the App's Screen, displaying a list of user fetched from backend
- **ui**: Compose Theme-related files
- **utils**: Contains internal utils meant to be used across the whole project, such as a Json object
- **viewmodel**: Contains the App's viewModel, maintaining responsive commands
- 