#!/bin/bash
yum install redis6 -y
systemctl enable redis6
systemctl start redis6
yum install -y maven-amazon-corretto21
yum install git -y
git clone https://github.com/cs220s25/JJJTeamProject.git /JJJTeamProject
cd /JJJTeamProject
chmod +x ec2Systemd/redeploy.sh
mvn package 
cp ec2Systemd/discordBot.service /etc/systemd/system
systemctl enable discordBot.service 
systemctl start discordBot.service
