pipeline
{
agent any
	tools {
		maven "maven"
	       }
	stages
	{
		
		stage('scan pages')
		{
			steps
			{
				 withSonarQubeEnv('nutrisonar')
				{
				withMaven(maven:'maven')
				{
					bat "mvn sonar:sonar"
				}
				}
			}
		}	
	}
}