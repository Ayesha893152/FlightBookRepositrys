pipeline{
	agent any

	stages{
		stage('Build jar'){
			steps{
				sh "mvn clean package -DskipTest"
			}

		}
		stage('Build image'){
			steps{
				sh "docker build -t=aisha/seleniumdocker ."

			}

		}
		stage('push image'){
			steps{
				sh "docker push aisha/seleniumdocker"

			}

		}
	}
}
