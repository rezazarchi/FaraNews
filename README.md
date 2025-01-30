# MetaMovie

MetaMovie is an Android application developed as part of a job interview task. The app allows users to explore and search movie collections.

## Features

- **Movie Browsing**: Search movies.
- **Bookmarking**: Save favorite movies for quick access.
- **User-Friendly Interface**: Navigate the app with ease, thanks to its intuitive design implemented with Jetpack Compose and based on Material 3.

## Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/rezazarchi/MetaMovie.git
   cd MetaMovie
   git checkout master
   ```

2. **Open in Android Studio**:

   - Launch Android Studio. You should have the latest version of Android Studio (Ladybug Feature Drop | 2024.2.2)
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
