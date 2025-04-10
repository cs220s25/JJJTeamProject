# run Build
mvn clean -f JJJTeamProject/pom.xml 
mvn package -f JJJTeamProject/pom.xml

# Start application from .jar
java -jar JJJTeamProject/target/dbot-1.0-SNAPSHOT.jar "./JJJTeamProject/"
