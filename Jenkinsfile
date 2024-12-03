pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Крок для завантаження коду з репозиторію GitHub
                git 'https://github.com/KathyCreates/lab11'
            }
        }

        stage('Build') {
            steps {
                // Збірка проєкту за допомогою Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Запуск тестів Maven
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Збірка та тести пройшли успішно!'
        }
        failure {
            echo 'Сталася помилка під час збірки або тестування.'
        }
    }
}

