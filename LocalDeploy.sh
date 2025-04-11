
# Start redis-server as a background process (daemon)
brew services start redis

# Current Directory is the repo
cd /JJJTeamProject

# Maven lifecycle
mvn clean
mvn package

# Launch server
java -jar target/dbot-1.0-SNAPSHOT.jar
