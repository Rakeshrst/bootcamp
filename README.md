# bootcamp
Boot camp is all about a way to arrange an bootcamp event, where a structure  is followed there is only one Admin and many instructors having there own set of Subordinate -  Sub instrcutors to help them arrange the bootcamp.


The strucure is arranged in classic tree structure
						
						Admin
						/\
					   /  \
	                  /    \
            Instructor1    Instructor2
				/\            /\  \         
			   /  \          /  \  \
	          /    \        /    \  \
		  	I3 	  I4       I5    I6  I7
			/             /\          \         
		   /             /  \          \
	      /             /    \          \  	
		I8             I9    I10        I11
		
		
	1. Admin only has sub-instrcutors. It is the root node of the tree.
	2. Each Instrutor always had a supervisor hereby refered to as admin of the instrcutor. There cannot exist an instrcutor without an admin(supervisor)
	

The package includes a: 
1. Spring Boot Application 
2. And Instructor Entity Object 
3. An In Memory - H2 Database

There are totally 4 API
GET

	1. /instructors: This gives all the instructors from the root node onwards.
	
	2./instructor/{instructorName} : This provides the details of the specific instructor there admin (Supervisor)
	 
POST : To add an Instructor

	3. /instructor: Here the instructorName and a valid admin has to be specified . If a valid admin is not present . it is not possible to add the instructor. 

	4. /instructor/{instructorName}/subordinate : Here the instructorName and a valid admin has to part of the header . If a valid admin is not present . it is not possible to add the instructor. 


How to run : 
After downloading the code Go the bootcamp folder and run the below command. Make sure this is the folder wher pom.xml is present
	
	mvn spring-boot:run


Examples 
1. GETAllInstrcutors from Root: http://localhost:8080/instructors

	{
    "instructorName": "Admin",
    "admin": null,
    "subordinates": [
        {
            "instructorName": "I11",
            "admin": "Admin",
            "subordinates": [
                {
                    "instructorName": "I21",
                    "admin": "I11",
                    "subordinates": [
                        {
                            "instructorName": "I41",
                            "admin": "I21",
                            "subordinates": []
                        }
                    ]
                },
                {
                    "instructorName": "I22",
                    "admin": "I11",
                    "subordinates": [
                        {
                            "instructorName": "I51",
                            "admin": "I22",
                            "subordinates": []
                        }
                    ]
                },
                {
                    "instructorName": "I23",
                    "admin": "I11",
                    "subordinates": []
                }
            ]
        },
        {
            "instructorName": "I12",
            "admin": "Admin",
            "subordinates": [
                {
                    "instructorName": "I31",
                    "admin": "I12",
                    "subordinates": []
                },
                {
                    "instructorName": "I32",
                    "admin": "I12",
                    "subordinates": []
                }
            ]
        }
    ]
}


2. GET Instructor By Name : http://localhost:8080/instructor/R1

		{
    "instructorName": "R1",
    "admin": "I41",
    "subordinates": [
           {
            "instructorName": "R41",
            "admin": "R1",
            "subordinates": []
           }
    		]
		 }
		 
3. POST : Add an instructor: http://localhost:8080/instructor/
		
				{
			  "instructorName": "R41",
	         "admin": "R1"
           	}	
           	
4. POST : http://localhost:8080/instructor/I41/subordinate

		{
    "instructorName": "Rohan"
		}

	