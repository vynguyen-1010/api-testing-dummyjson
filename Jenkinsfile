pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/vynguyen-1010/api-testing-dummyjson.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'extent-report.html',
                    reportName: 'API Test Report'
                ])
            }
        }
    }
}