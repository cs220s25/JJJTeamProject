sudo systemctl stop discordBot.service
sudo git pull origin main
sudo mvn clean package
sudo systemctl start discordBot.service

