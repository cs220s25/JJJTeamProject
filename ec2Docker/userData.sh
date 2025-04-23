#!/bin/bash
yum install -y docker
systemctl enable docker
systemctl start docker
mkdir -p /usr/local/lib/docker/cli-plugins
curl -SL https://github.com/docker/compose/releases/download/v2.35.1/docker-compose-linux-x86_64   -o /usr/local/lib/docker/cli-plugins/docker-compose
chmod +x /usr/local/lib/docker/cli-plugins/docker-compose
usermod -a -G docker ec2-user
yum install -y git
git clone https://github.com/cs220s25/JJJTeamProject.git
cd /JJJTeamProject
chmod +x /JJJTeamProject/ec2Docker/redeploy.sh
docker compose up -d
