#!/usr/bin/groovy
pipeline{
    agent any
    stages{
        stage ("Clone repo"){
            steps{
                git url: 'https://github.com/CharlesPikachu/Games.git'
            }
        }
        stage ("Rename"){
            steps{
                sh "mv Game7 'Dino DevOps'"
            }
        }
        stage ("Zip"){
            steps{
                script{
                zip zipFile: "DevOpsGame.zip", dir: "Dino DevOps"
                }
            }
        }
        stage ("Push"){
            steps{
                sh "mv DevOpsGame.zip repo/"
                dir("repo"){
                    sh "git config user.email 'gregolauty94@gmail.com'"
                    sh "git config user.name 'Gregorio Troncoso'"
                    sh "git add ."
                    sh "git commit -m 'Version inicial'"
                    sh "git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/grego9/directvprueba.git"
                }
            }
        }
        stage ("Notification"){
            steps{
                emailext body: "https://github.com/grego9/directvprueba.git", subject: "Test DevOps", to: "gregoriotroncoso9@gmail.com"
            }
        }
    }
}