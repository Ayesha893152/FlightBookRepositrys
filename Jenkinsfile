pipeline{
	agent any

	stages{
		stage('Build jar'){
			steps{
				bat "mvn clean package -DskipTests"
			}

		}
		
		stage('Build image'){
			steps{
				bat 'docker build -t ayesha715/flight-booking-app .'
                bat 'docker login -u ayesha715 -p <Test@1234>'


			}

		}
		stage('Push image'){
			steps{
				bat 'docker push ayesha715/flight-booking-app'

			}

		}
	}
}
