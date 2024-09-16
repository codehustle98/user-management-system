pipeline {
    agent any
    stages{
        stage('Build'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        echo 'Running on windows !'
                        bat 'gradlew clean build'
                    }
                }
            }
        }
        stage('Build Docker Images'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        bat 'docker-compose build';
                    }
                }
            }
        }
        stage('Run Container'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        bat 'docker-compose up -d'
                    }
                }
            }
        }
        stage("Login into Ubuntu WSL"){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        bat 'ssh ubuntu@172.25.82.0'
                        sh whoami
                    }
                }
            }
        }
    }
}