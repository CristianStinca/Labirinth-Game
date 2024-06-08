# Labirinth-Game
A simple packman-like game.

### How to run the project?
You will need have Java installed. The project creates a database using MySql. Originally it was executed using MySQL Workbench 8.0 CE. In the "path to project"\src\database\HighScores.java, the username and the password has to be modified to match your MySQL database (can be found on lines 25-26). The project will require to specify a reference to mysql-connector.jar library. It can be downloaded from http://download.nust.na/pub6/mysql/downloads/connector/j/index.html. In the editor you need to reference to the path of this library, and then just run the project from the editor.

### Controls
Use AWSD for movement. 

### How to play?
The player's task is to exit the labyrinth. The exit is located in the top-right corner of the map. The game has an NPC - the Dragon. When the player gets on an adjacent cell with it, the game will end. The leader board will display the best players that are determined by the number of consecutive labyrinths the player was able to escape, and the time he spent doing it.

### Gameplay preview
![image](https://github.com/CristianStinca/Labirinth-Game/assets/158779976/d4a2dd4c-8691-4e28-849b-9aecc9cba1b3)
![image](https://github.com/CristianStinca/Labirinth-Game/assets/158779976/fbb3381f-6a6d-4e08-898c-3b02b21d20a5)
![image](https://github.com/CristianStinca/Labirinth-Game/assets/158779976/41c20774-b877-45a2-825f-5fb2ab5fa33d)
