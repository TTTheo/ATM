# README
Group Members: Yujing Chen, Zehui Jiang, Wenrui Lai, David Shen


## Instructions for running through the command line
We currently use SQLite as an offline database. All the persistent data is stored in a file called `ATM.db`.
We use the sqlite-jdbc jar file (`sqlite-jdbc-3.27.2.1.jar`) to connect with SQLite. `sqlite-jdbc-3.27.2.1.jar` is located in the `src` directory.

To run, start in the `src` directory, then run:
```
javac Main.java  # Compile program
cd ..            # Move out of the src directory
java -classpath "src:src/sqlite-jdbc-3.27.2.1.jar" Main   # Mac or Linux
java -classpath "src;src/sqlite-jdbc-3.27.2.1.jar" Main   # Windows
```

The Bank ATM window should now appear.

## Logging in
A manager has been created and inserted into the database already. In addition, some test users have also been created.

* Manager
```
username: boss
password: boss
```

* Test Customer 1
```
username: fr
password: 12345
checking account number: 1
saving account number: 2
```

* Test Customer 2
```
username: dj
password: 123
checking account number: 3
saving account number: 4
```
