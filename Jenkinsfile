pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true' //Limpa empacota mas nao executa o testes com -DskipTests=true
            }
        }
    }
}