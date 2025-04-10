# Anagram Discord Bot

## CI Status 
![Testing](https://github.com/cs220s25/JJJTeamProject/actions/workflows/run_tests.yml/badge.svg)

## Contributors 
* Jonathan Walsh <br>
* Jamell Alvarez <br>
* Jack Wagenheim
## Setup

Before the bot can be run, a few steps must be taken. After the repo has been cloned, use `brew install redis` to install the database used by the bot.
Next, create a file titles `.env`, with the following format:

```sh
DISCORD_TOKEN=<API key>
CHANNEL_NAME=<Channel Name>
```

Where <API key> is replaced by your bots API key, and <Channel Name> is replace by the name of the channel in which the bot should be active.
Finally, run `./deployLocal.sh` to execute a script that will execute the necessary steps to get the bot up and running.


## Technologies Used

By using the Discord API, this project aims to create a bot that can play a word guessing game with users.
[Discord Developer Site](https://discord.com/developers/docs/intro)

The primary mode of data storage for this project is a Redis database, but the repository also contains an interface
that can be implemented with any data storage method such as in memory or in a SQL database.
[Redis Docs](https://redis.io/docs)

The software project management tool, Maven, is used to package the bot, so that it can be run as a .jar file.
[Maven Introduction](https://maven.apache.org/)


## Background



## Project Description

The bot will allow users to start a game, join, guess words, and stop the game. 
The bot will also keep track of the words that have been used in the game.


## System Diagram

![System Diagram](SystemDiagram.png)
**Overview**
- This system diagram can be described by breaking it up into three parts: The delivery mechanism, the app, and the data storage.

**Delivery**
- The program uses discord as the main method of delivery. BotDriver acts as the main for this part of the system. It listens for messages from discord and sends them to the controller to be processed. The CommandManager processes command signatures sent from the user. The CommandManager then either communicated with the GameController to process the command or sends an error message back to the user.

**APP**
- The app and game logic for this game are handled by the GameController. The GameController is responsible for managing the game state and processing commands. The GameController acts as a boundary between the game logic and the data storage of the program. That is, there is no other area in the program with access to the data storage.

**Data Storage**
- The data storage for this program is handled by the DataManager interface. This repository implements the DataManager interface in two ways
(so far, RedisManager and InMemoryManager). The DataManager objects are responsible for keeping track of data used for game processes. It also handles the input validation for guessing words.

## Usage

Users interact with the bot through the use of various commands.
These commands start with the prefix `!` and are followed by the command name.
The bot will then send the message to be executed by the controller class, which will either return a message to output
to the user, or will throw an exception, the message of which will be propagated to the user by the bot driver.

The following commands are available to the user:
- `!start`: start the game
- `!join`: join the game
- `!guess <word>`: guess a word
- `!stop`: stop the game
- `!used`: get used words
- `!status`: get game status
- `!help`: displays commands

This implementation of the app relies heavily on the use of the Discord API, but since the delivery mechanism is seperated from the game logic, the system design allows for any type of delivery implementation; for example, a command line interface or a web interface.
