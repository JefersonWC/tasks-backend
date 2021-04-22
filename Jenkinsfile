pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true' //Limpa empacota mas nao executa o testes com -DskipTests=true
            }
        }
        stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
               withSonarQubeEnv('SONAR_SCANNER') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.sonar.projectKey=DeployBack -Dsonar.sonar.host.url=http://localhost:9000 -Dsonar.sonar.login=ad773c2f14ec2b8659f1288f40d507ffaab8e38e -Dsonar.sonar.java.binaries=target -Dsonar.sonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**/Application.java"
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                sleep(10)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

