pipeline{
	agent any

	stages{
		stage('Build jar'){
			steps{
				bat "mvn clean -DskipTests"
			}

		}
		
		stage('Build image'){
			steps{
				bat 'docker build -t ayesha715/flight-booking-app .'
                                bat 'docker login -u aishasaif390@gmail.com -p <Test@1234>'


			}

		}
		stage('Push image'){
			steps{
				bat 'docker push aishasaif390@gmail.com/flight-booking-app'

			}

		}
	}
}
