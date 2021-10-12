pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build World'
            }
        }
      stage('Test') {
            steps {
                echo 'Test World'
                bat 'mvn clean test'
            }
        }
      stage('Deploy') {
            steps {
                echo 'Deploy World'
            }
        }
    }
}
