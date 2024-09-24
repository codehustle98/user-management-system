pipeline {
    agent any
    stages{
        stage('Extract Version'){
            steps{
                script{
                    def version = readFile('build.gradle').find(/version\s*=\s*['"](.+)['"]/).replaceAll(/.*['"](.+)['"].*/, '$1')
                    env.VERSION = version
                    echo 'Running Version : ${env.VERSION}'
                }
            }
        }
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
        stage('SSH into Ubuntu'){
            steps{
                script{
                    if(isUnix()){

                    }else{
                        bat 'ssh ubuntu@172.25.82.0'
                        echo 'Logged into Ubuntu'
                    }
                }
            }
        }
    }
}