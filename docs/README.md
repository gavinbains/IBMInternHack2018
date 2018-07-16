# IBMnews
FrontEnd for IBM news
Steps:
1. Install and start Tomcat.  
  A. Download Tomcat package : https://tomcat.apache.org/download-80.cgi   
  B. Unzip and put it under Library folder (/Users/Evelyn.Lin\@ibm.com/Library/)   
  C. Rename the folder name to “Tomcat”,  the directory for Tomcat would be like: /Users/Evelyn.Lin\@ibm.com/Library/Tomcat/   
  D. In Terminal, execute command below:  
       cd /Users/Evelyn.Lin\@ibm.com/Library/Tomcat/bin/  
       chmod -R 777 startup.sh  
       chmod -R 777 catalina.sh  
      ./startup.sh  
  E. Check if tomcat is running: ps -ef | grep tomcat   


2. Put the whole folder “IBMnews” under  webapps in Tomcat, after putting in, the directory of IBMnews is like  “/Users/Evelyn.Lin@ibm.com/Library/Tomcat/webapps/IBMnews”

3. Access by: http://localhost:8080/IBMnews/



