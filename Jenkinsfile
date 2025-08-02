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
				echo "build image"

			}

		}
		stage('push image'){
			steps{
				echo "push image"

			}

		}
	}
}
