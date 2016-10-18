This project requires an installation of the Derby database to run the samples: 
 
To learn more, please visit: http://db.apache.org/derby/, and in particular, http://db.apache.org/derby/papers/DerbyTut/ns_intro.html

 = Basic steps
 - download Derby (http://db.apache.org/derby/releases/release-10.4.2.0.html for the version we're using)
 - unzip it 
 - set system variable for DERBY_HOME to be equal to the root of the release folder. On my Linux system:
 	 export DERBY_HOME=~/db-derby-10.4.2.0-bin/
 - descend into the bin foler, run  ./startNetworkServer
 - put that process in the background or start a new shell and, in the same folder, run: ./ij
 - in ij, enter: 
 	connect 'jdbc:derby://localhost:1527/bookshop;create=true' 
 - from there, load the SQL in the src/main/resources/bookstore.sql file by pasting it into ij itself 
    
 
 	 
 	
 