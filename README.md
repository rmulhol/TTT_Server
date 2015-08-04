#TTT Server

##Description
A web interface for tic-tac-toe combining my [http server](https://github.com/rmulhol/httpServer) and my [Clojure tic-tac-toe command line application](https://github.com/rmulhol/clojure-tic-tac-toe).

##How to Run

####From the Command Line
`gradle build`, `java -jar build/libs/TTTServer.jar`

Supports options `-p` and `-d` for setting port and directory, respectively (e.g. `java -jar build/libs/TTTServer.jar -p 5050 -d /test_dir`. Defaults to `5000` and `/public`.

Visit `http://localhost:5000/tic_tac_toe` (or whichever port you've specified) to get started.

####From IntelliJ
Run `Main.main()`.

##How to Test

####From the Command Line
`gradle test`

####From IntelliJ
Run all tests.

##Versions
Java 1.8