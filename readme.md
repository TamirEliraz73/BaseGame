# My LibGDX Extension Library

## Overview

This library extends the basic objects provided by libGDX by introducing base objects, handlers, and additional
features. The aim is to enhance the functionality of these simple objects, making game development more efficient and
organized.

## Features

- **Base Objects**: Extend basic libGDX objects with enhanced functionalities.
- **Handlers**: Simplify the management of game events and actions.
- **Custom Animations**: Easily create and manage animations for game characters.
- **Utility Methods**: A set of helpful functions for common game development tasks.

## Installation

To use this library in your libGDX project:

1. Add the library as a dependency.
2. Import the necessary classes in your game files.

If your're using gradle, you may add this code to your ```build.gradle```:

```gradle
sourceSets {
    main {
        java {
            srcDirs '../BaseGame/src/main/java'
        }
    }
}
```

## Dependencies

This project uses the following liberties:

```gradle
ext {
    gdxVersion = '1.13.0'
    junitVersion = '5.10.3'
}

// ...

dependencies {
    testImplementation platform("org.junit:junit-bom:$junitVersion")
    testImplementation 'org.junit.jupiter:junit-jupiter'

    implementation "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop"
    implementation "com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop"
    implementation "com.badlogicgames.gdx:gdx-tools:$gdxVersion"

    // https://mvnrepository.com/artifact/org.jetbrains/annotations
    implementation 'org.jetbrains:annotations:26.0.1'
}
```

## Usage

Here’s a quick example of how to use the library:

```java

```

## Documentation

For more details on each component, check the [Wiki](DOCUMENTATION).

## Contributing

Contributions are welcome! If you have ideas or improvements, please open an issue or submit a pull request.

## License

This project is licensed under the Creative Commons Attribution-NonCommercial (CC BY-NC) License. You are free to use,
modify, and share this code for non-commercial purposes, especially for educational purposes, as long as you give
appropriate credit to the original author. For more details, please
visit [Creative Commons](https://creativecommons.org/licenses/by-nc/4.0/).
