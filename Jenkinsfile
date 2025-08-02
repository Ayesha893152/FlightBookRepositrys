pipeline{
	agent any

	stages{
		stage('Build jar'){
			steps{
				sh "mvn clean package -DskipTest"
			}

		}
		stage('stage-2'){
			steps{
				echo "building docker image"

			}

		}
		stage('stage-3'){
			steps{
				echo "push docker image"

			}

		}
	}
}
