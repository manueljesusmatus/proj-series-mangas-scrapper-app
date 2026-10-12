pipeline {
    agent any

    tools {
        maven 'M3'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'Laplab',
                            transfers: [
                                sshTransfer(
                                    sourceFiles: 'target/*.jar',
                                    removePrefix: 'target',
                                    remoteDirectory: 'artifacts/series'
                                ),
                                sshTransfer(
                                    sourceFiles: 'docker/*',
                                    removePrefix: 'docker',
                                    remoteDirectory: 'artifacts/series',
                                    execCommand: """
                                        cd artifacts/series/ &&
                                        date >> historial.txt &&
                                        docker build -t series-app:${env.GIT_COMMIT.substring(0,7)} -t series-app:latest .
                                    """
                                )
                            ],
                            usePromotionTimestamp: false,
                            verbose: true
                        )
                    ]
                )
            }
        }
    }

    post {
        always {
            script {
                sh 'printenv | grep GIT'
            }
        }
    }
}
