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
                            configName: 'Raspberry',
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
                                        cd artifacts/series/ && \\
                                        date >> historial.txt && \\
                                        COMMIT_HASH=\$(git rev-parse --short HEAD) && \\
                                        docker build -t series-app:\$COMMIT_HASH -t series-app:latest .
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
                sh 'printenv'
            }
        }
    }
}
