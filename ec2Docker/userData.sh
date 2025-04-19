#!/bin/bash
yum install -y docker
systemctl enable docker
systemctl start docker
usermod -a -G docker ec2-user
yum install -y git
git clone https://github.com/cs220s25/JJJTeamProject.git
cd /JJJTeamProject/ec2Docker
chmod +x build.sh
./build.sh
./up.sh
