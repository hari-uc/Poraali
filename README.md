# Poraali

A productivity Android application designed to help users stay organized and inspired with essential tools for daily tasks, notes, quotes, and entrepreneurship news.

## Overview

Poraali is a native Android app built with Java that combines multiple productivity features into a single, minimalistic interface. The app focuses on simplicity and usability while providing essential tools for personal organization and inspiration.

## Features

- **Inspirational Quotes**: Daily quotes in English and Tamil to keep you motivated
- **Notes**: Create, edit, and manage personal notes with a clean interface
- **Todo Lists**: Track your tasks and stay organized
- **News**: Stay updated with the latest entrepreneurship news and articles
- **Multi-language Support**: Interface available in English and Tamil

## Technical Stack

- **Language**: Java
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35 (Android 15)
- **Architecture**: MVVM pattern
- **Database**: Room Database for local storage
- **Cloud Services**: Firebase Realtime Database and Cloud Firestore
- **Networking**: Volley for API requests
- **UI Components**: Material Design 3, ConstraintLayout, RecyclerView

## Key Dependencies

- AndroidX Core and AppCompat
- Material Components
- Firebase SDK (Realtime Database, Firestore)
- Room Database
- Volley
- Lottie Animations
- Picasso for image loading
- CircleImageView for profile images

## Build Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/poraali.git
   ```

2. Open the project in Android Studio

3. Add your `google-services.json` file to the `app/` directory

4. Sync Gradle and build the project

5. Run on an emulator or physical device (API 24+)

## Project Structure

```
app/
├── src/main/java/com/hari/poraali/
│   ├── Activities/          # Activity classes
│   ├── Adapters/            # RecyclerView adapters
│   ├── Database/            # Room database classes
│   ├── QuotesActivity/      # Quote-related fragments
│   └── POJO/                # Data models
├── src/main/res/
│   ├── layout/              # XML layouts
│   ├── drawable/            # Vector graphics and images
│   ├── values/              # Colors, strings, themes
│   └── raw/                 # JSON data files
```

## Configuration

The app requires Firebase configuration for cloud storage features. Ensure you have:

- Firebase Realtime Database enabled
- Cloud Firestore enabled
- Proper security rules configured

## Privacy

The app collects minimal user data necessary for functionality. For detailed information, see our [Privacy Policy](https://cheerful-beijinho-39c1ae.netlify.app/privacy-policy.html).

## Version History

**Version 2.0**
- Major UI/UX redesign with Material Design 3
- Updated quotes API integration
- Improved navigation drawer
- Enhanced card layouts across all sections
- Fixed stability issues and crashes
- Updated to latest Android SDK and libraries
- Code optimization and removal of deprecated APIs

**Version 1.0**
- Initial release with core features

## Requirements

- Android device running API 24 (Android 7.0) or higher
- Internet connection for quotes and news features
- Approximately 15 MB storage space

## License

This project is available under the MIT License.

## Developer

Developed and maintained by Hari Krishna.

## Contact

For bug reports, feature requests, or questions, please open an issue on GitHub.

## Acknowledgments

- Quote data provided by RealInspire API
- Firebase for backend services
- Material Design components by Google
