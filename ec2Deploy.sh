# run Build
JJJTeamProject/mvnw clean -f JJJTeamProject/pom.xml 
JJJTeamProject/mvnw package -f JJJTeamProject/pom.xml

# Start application from .jar
java -jar JJJTeamProject/target/dbot-1.0-SNAPSHOT.jar "./JJJTeamProject/"
