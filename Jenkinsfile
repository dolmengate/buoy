pipeline {
    agent any
    stages {
        stage('pre') {
            steps {
                sh 'mvn clean plugin:descriptor'
                sh 'mvn clean dependency:resolve -U'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean compiler:compile'
                sh 'mvn clean compiler:testCompile'
            }
        }
        stage('unit test') {
            steps {
                sh 'mvn clean surefire:test'
            }
        }
        stage('deploy for integration test') {
            steps {
                sh 'cd ./server && mvn spring-boot:start'
            }
        }
        stage('integration test') {
            steps {
                sh 'echo todo'
            }
        }
    }
    post {
        success {
            sh 'mvn spring-boot:stop'
        }
    }
}