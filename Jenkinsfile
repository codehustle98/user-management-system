pipeline {
    agent any
    stages{
        stage('Build Core'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        echo 'Running on windows !'
                        bat 'gradlew :core:clean build'
                    }
                }
            }
        }
        stage('Build Web & Export'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        bat 'gradlew :web:clean build'
                        bat 'gradlew :export:clean build'
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
                        bat 'docker-compose up'
                    }
                }
            }
        }
    }
}