# Galactic Directory

An overengineered, offline-first, super simple app to fetch data from the SWAPI API with the ability to add favorites.

## Overview

This project is based on [Forge](https://github.com/SkashEU/Forge) and serves as a demonstration of a robust, multi-module architecture in a Kotlin Multiplatform project.

## Setup

Since this project relies on [Forge](https://github.com/SkashEU/Forge), which is hosted via GitHub Packages, you must provide authentication credentials to fetch the dependencies.

### Prerequisites

You will need your **GitHub User ID** and a **Personal Access Token (PAT)**.

### 1. Get Your User ID

1. Open your browser and visit: `https://api.github.com/users/YOUR_USERNAME`
2. Look for the **`"id"`** field in the text displayed (e.g., `"id": 1234567`).
3. Copy that number.

### Generating the Key

The Personal Access Token acts as your password. The only permission required is `read:packages`.

1. Go to **Settings** > **Developer settings** > **Personal access tokens** > **[Tokens (classic)](https://github.com/settings/tokens)**.
2. Click **Generate new token (classic)**.
3. Add a note (e.g., "Galactic Directory").
4. Under **Select scopes**, check **only** the box for **`read:packages`** (this authorizes the package download).
5. Click **Generate token** and copy the resulting string (it usually starts with `ghp_`).

### Configuration

Open the `local.properties` or `gradle.properties` file in your project's root directory (or create it if it doesn't exist) and add the following lines, replacing the placeholders with your actual details:

```properties
gpr.user=YOUR_GITHUB_USER_ID
gpr.key=YOUR_GENERATED_TOKEN
```
## Showcase
<img width="128" height="285" alt="image" src="https://github.com/user-attachments/assets/bd991e68-28a4-494e-8d7e-b727af27ee3b" />
<img width="128" height="285" alt="image" src="https://github.com/user-attachments/assets/907bbb48-b9f1-43cc-850d-47fc0c68ad46" />
<img width="128" height="285" alt="image" src="https://github.com/user-attachments/assets/df000007-d44c-4e50-88a8-b3a0d89f7994" />

## Features

- **Offline-First**: Data is cached locally to ensure a seamless experience even without an internet connection.
- **SWAPI Integration**: Fetches galactic data including characters and species from the Star Wars API.
- **Favorites**: Mark your favorite galactic entities for quick access.
- **Overengineered**: Built with a highly scalable and testable architecture, perhaps more than necessary for its simple functionality.

## Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Compose Multiplatform**
- **Forge**: Architecture framework.
- **Room**: Local database.
- **Ktor**: Networking.
- **Koin**: Dependency injection.
