pipeline {
    agent 'Ubuntu'
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
                      sh 'gradlew clean build'
                    }
                }
            }
        }
        stage('Build Docker Images'){
            steps{
                script{
                   sh 'docker-compose build';
                }
            }
        }
        stage('Run Container'){
            steps{
                script{
                   sh 'docker-compose up -d'
                }
            }
        }
    }
}