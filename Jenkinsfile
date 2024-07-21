pipeline{
    agent any
	
	stages{
	    
		  stage("build"){
		  steps{
		       echo("build the project")
			   }
		}
		
		stage("Run Unit test"){
		steps{
		echo("run UTs")
		   }
		}
		
		stage("Run Integration test"){
		    steps{
			   echo("run IT's")
			      }
			   }
			   
			   
		stage("Deploy to dev"){
            steps{
               echo("deploy to dev")
                   }
               }
			   
		stage("Deploy to QA"){
            steps{
               echo("deploy to qa ")
                  }
                 }
				 
		stage("Run regression test cases on QA"){
            steps{
               echo("run regression test cases on qa")
                     }
                    }


			stage("Deploy to stage"){
            steps{
               echo("deploy to stage")
                     }
                    }
					
			   
		stage("Run sanity test cases on QA"){
            steps{
               echo("run sanity test cases on QA")
                     }
                    }	   
			   
			   
		stage("Deploy to PROD"){
            steps{
               echo("deploy to PROD")
                     }
                    }  
                    
	}
}	