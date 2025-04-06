# Starts redis server on port 6379 with localhost
# Start redis-server as a background process (daemon)
brew services start redis

# Maven lifecycle
mvn clean
mvn package

# Launch server 
java -jar target/dbot-1.0-SNAPSHOT.jar


