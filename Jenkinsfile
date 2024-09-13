pipeline{
    agent any
    stages{
        stage('Build Core'){
            steps{
                if(isUnix()){

                }else{
                    echo 'Running on windows !'
                    bat 'gradlew :core:clean build'
                }
            }
        }
        stage('Build Web & Export'){
            steps{
                if(isUnix()){

                }else{
                    bat 'gradlew :web:clean build'
                    bat 'gradlew :export:clean build'
                }
            }
        }
        stage('Build Docker Images'){
            steps{
                if(isUnix()){

                }else{
                    bat 'docker-compose build';
                }
            }
        }
        stage('Run Container'){
            steps{
                if(isUnix()){

                }else{
                    bat 'docker-compose up'
                }
            }
        }
    }
}