# Install brew
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install maven and redis
brew install maven
brew install redis

# Starts redis server on port 6379 with localhost
# Start redis-server as a background process (daemon)
brew services start redis

# Maven lifecycle
mvn clean
mvn package

# Launch server 
]java -jar target/dbot-1.0-SNAPSHOT.jar


