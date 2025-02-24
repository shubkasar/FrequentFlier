# Frequent Flier App

**Frequent Flier App** is an end-to-end database application developed for CS550 that simulates a frequent flyer management system. This project integrates a Java-based web backend (using Servlets, JSP, and JDBC) with an Android client to provide a seamless user experience—from user authentication to flight management and points transfers.

## Demo

Watch the presentation video for a detailed walkthrough of the application—from login to points transfer—demonstrating both the web and Android interfaces in action.
Please check the following video for more details: [https://youtu.be/A3jje55Jisw](https://youtu.be/A3jje55Jisw)

-----------
## Features

- **Login Module**
  - Validates username and password.
  - Returns a confirmation along with a passenger ID on successful login.

- **Passenger Information**
  - Displays passenger name, total points, and profile image.

- **Flight Details**
  - Retrieves dynamic flight data via JDBC.
  - Formats results with comma-separated columns and hash-separated rows for easy parsing.

- **Award & Redemption Management**
  - Retrieves award IDs from redemption history.
  - Displays award details such as descriptions, required points, and redemption history using multiple SQL queries and joins.

- **Points Transfer**
  - Enables points transfer between passenger accounts.
  - Executes SQL queries to deduct points from the source account and add them to the destination account.

- **Android Application**
  - Implements network requests to interact with the backend (using libraries like Volley).
  - Utilizes spinners for selecting flights and destination accounts.
  - Offers a seamless navigation experience between login, home, flights, awards, and points transfer screens.
