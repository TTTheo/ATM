# GitHub README

## Running with SQLite
We currently use `SQLite` as an offline database. All the persistent data is stored in a file called `ATM.db`.
In order to run FancyBank with SQLite, first download the sqlite-jdbc jar file from `https://bitbucket.org/xerial/sqlite-jdbc/downloads/`.

Click on the most recent one (`sqlite-jdbc-3.27.2.1.jar`) to download it.

Place `sqlite-jdbc-3.27.2.1.jar` in the `ATM` directory

### Running through the command line
Make sure you've pulled the recent changes

Start in the `src` directory, run:
```
javac Main.java  # Compile program
cd ..            # Change to the `ATM` directory
```

Then on Windows, run:
```
java -classpath "src;sqlite-jdbc-3.27.2.1.jar" Main   # Windows
```

On macOS or Linux, run:
```
java -classpath "src:sqlite-jdbc-3.27.2.1.jar" Main   # Mac or Linux
```

If it works, there should be no errors, and the program should continue as expected


### Running from an IDE
Untested, but the general idea is to add `sqlite-jdbc-3.27.2.1.jar` to your class path and run Main.java.

## Logging in
(Users are created from the `main` function of `Main.java`)


Customer
* username: wx
* password: 12345


* username: zhjiang
* password: 123

Manager
* username: boss
* password: boss
