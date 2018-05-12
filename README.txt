Submission for SENG2050 - Introduction to Web Engineering
Student: 	Bradley Turner
Student#:	c3259038

URL to start the program:	"localhost:8080/c3259038_Assignment2/Index"

NOTE(S):
	- Okay, so I know that we are ment to save the game if the user is idle for a minute, but this cant happen as if we were to save the game it presents a series of problems:
		1.	What username do we associate with the saved game? Okay this could be fixed by creating a random username but;
		2.	If we do generate a username and save, how is the user to know what username they were assigned? Say if we were to do this and present a dialog box to the
			user with the username, whats to say that they hadnt lost connection to the server or worse someone else uses the same computer and accepts the generated username
			and 'steals' the game from the actual user.
		
		My solution to this somewhat does what the assignment specifies and if user is idle for a minute then it redirects to the SaveGame.jsp
	- Im also assuming that 'multi-user' refers to the web app being tested on two different browsers at the same time. Chrome tends to maintain the session id accross different open tabs.
	
Application Structure:
	Two controller servlets who receive http-POST messages:
	-	Index.java		Controls the web app flow regarding starting a new game and loading an exisiting game. Passes session variables to Main.java
	-	Main.java		Controls the main flow of the game, using javabean GameState and a variety of other one time use session variables.
	
	Both control servlets access the following jsps:
	-	Index:
	->		Index.jsp
	->		LoadSave.jsp
	
	-	Main:
	->		DoND.jsp		...		Deal or No Deal jsp
	->		SaveGame.jsp
	->		Win.jsp
	
	The above mentioned jsps include a Header.jsp and Footer.jsp
	
File Structure:
	+ c3259038_Assignment2
		+ WEB-INF
			+ classes
				+ beans
					- GameState.class
				- GameState.java
				- Index.class
				- Index.java
				- Main.class
				- Main.java
				
			+ jsps
				- DoND.jsp
				- Footer.jsp
				- Header.jsp
				- Index.jsp
				- LoadGame.jsp
				- SaveGame.jsp
				- Win.jsp
				
			+ saves
				- saves.txt
				
			- web.xml
			
		+ css
			- DoND.css
			- Index.css
			
		+ js
			- Game.js
			- Toggle.js
		
How did I implement session tracking:
	Generally through using the javabean GameState, setting boolean values for whether case is open or not, and string values for the contents of the case.
	This bean would then be passed to the session object and passed to the relevant jsp.
	
How did I implement file saving:
	File saving is done through using the following line structure:		<userName>,<case layout>,12*(true || false)
	where <case layout> is a generated string containing all chars once only in the string "0123456789AB". This is translated when fetched to arrange the layout of the cases in-game.
	

	
	
	
	
	
	
	