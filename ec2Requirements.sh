
## Install java
sudo dnf install java-21-amazon-corretto -y

## install redis
sudo yum install -y redis6
sudo systemctl enable redis6
sudo systemctl start redis6

## clone repo

git clone https://github.com/cs220s25/JJJTeamProject.git
cd JJJTeamProject/
