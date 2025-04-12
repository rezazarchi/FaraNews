# Fara News

Fara News is an Android application developed as part of a job interview challenge. The app enables users to seamlessly explore the latest news.

Built following clean architecture principles, the project is organized into multiple modules, promoting scalability and maintainability. It leverages Retrofit for efficient API interactions and utilizes Room for caching, ensuring both fast data retrieval and offline access.

## Features

- **News Browsing**: Fetch data from yesterday until now, sorted by newest first. Queries limited to: (Microsoft, Apple, Google, Tesla.)
- **Bookmarking**: Save favorite news for quick access.
- **User-Friendly Interface**: Navigate the app with ease, thanks to its intuitive design implemented with Jetpack Compose and based on Material 3.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/rezazarchi/FaraNews.git
   cd FaraNews
   git checkout master
   ```

2. **Open in Android Studio**:

   - Launch Android Studio. You should have the latest version of Android Studio (Meerkat)
   - Select 'Open an existing project' and navigate to the cloned repository's directory.

3. **Build the Project**:

   - Sync the project and ensure all dependencies are downloaded.
   - Click on 'Build' > 'Make Project' to compile the app.

4. **Run the Application**:

   - Connect an Android device or use an emulator.
   - Click on the 'Run' button to install and launch the app.

## Project Structure

- **app/**: Main application module containing UI components and resources.
- **core/**: Some common utilities.
- **database/**: Database handling and all room related daos and entitis.
- **features/**: Individual features and their implementations.
- **navigation/**: Integrated all features and components in this module.
- **commonui/**: Shared UI components and theme.
- **bookmark/**: Common bookmark feature implementation used in other modules.

## Contributing

As this project was developed for a specific purpose, contributions are currently not being accepted. However, feedback and suggestions are welcome. Please open an issue for any discussions.
