import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
import beans.GameState;

public class Main extends HttpServlet
{
	private final String FILENAME = "../webapps/c3259038_Assignment2/WEB-INF/saves/saves.txt";
	
	public Main(){}
	
	public void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		String saveQuit = request.getParameter("saveQuit");
		GameState saveGS = (GameState)request.getSession().getAttribute("state");
		String saveName = request.getParameter("saveName");
		String bankOffer = request.getParameter("bankDeal");
		String amount = request.getParameter("amount");
		
		// Code to save game(s).
		if (saveQuit != null && saveGS != null) 
		{
			if ((saveQuit.equals("true")))
			{
				if (saveName == null) 
				{
					// Passes values to index page, no need to reroute to servlet first.
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/SaveGame.jsp");
					dispatcher.forward(request, response);
				}
				else 
				{
					//Trying to sanitise the input to prevent XSS attacks
					saveName = saveName.replace("<", "&lt;");
					saveName = saveName.replace(">", "&gt;");
					saveName = saveName.toLowerCase();
					String layout 	 = caseToStr(saveGS.getCase1()) + caseToStr(saveGS.getCase2()) + caseToStr(saveGS.getCase3()) + caseToStr(saveGS.getCase4()) + caseToStr(saveGS.getCase5()) + caseToStr(saveGS.getCase6()); 
					layout 			+= caseToStr(saveGS.getCase7()) + caseToStr(saveGS.getCase8()) + caseToStr(saveGS.getCase9()) + caseToStr(saveGS.getCase10()) + caseToStr(saveGS.getCase11()) + caseToStr(saveGS.getCase12());
					
					String cases 	 = String.valueOf(saveGS.getCaseA()) + "," + String.valueOf(saveGS.getCaseB()) + "," + String.valueOf(saveGS.getCaseC()) + "," + String.valueOf(saveGS.getCaseD()) + "," + String.valueOf(saveGS.getCaseE()) + "," + String.valueOf(saveGS.getCaseF()) + ",";
					cases			+= String.valueOf(saveGS.getCaseG()) + "," + String.valueOf(saveGS.getCaseH()) + "," + String.valueOf(saveGS.getCaseI()) + "," + String.valueOf(saveGS.getCaseJ()) + "," + String.valueOf(saveGS.getCaseK()) + "," + String.valueOf(saveGS.getCaseL());
					saveGame("\n" + saveName + "," + layout + "," + cases);
					request.getSession().invalidate();
					
					// Passes values to index page, no need to reroute to servlet first.
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Index");
					dispatcher.forward(request, response);	
				}
			}
			else if (saveQuit.equals("false"))
			{
				// Passes values to index page, no need to reroute to servlet first.
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Index");
				dispatcher.forward(request, response);	
			}
		}
		
		if (bankOffer != null && saveGS != null && amount != null)
		{
			
			if (bankOffer.equals("true"))
			{
				//Winning state.
				
				request.getSession().invalidate();
				request.getSession().setAttribute("amount", amount);
				
				// Passes values to jsp.
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/Win.jsp");
				dispatcher.forward(request, response);
			}
			else if (bankOffer.equals("false"))
			{
				saveGS.setIsDealRnd(false);
				request.getSession().invalidate();
				
				// Sends the bean to the session.
				request.getSession().setAttribute("state", saveGS);
				
				// Passes values to jsp.
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/DoND.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		
		
		// Code to progress the game state.
		String turn = request.getParameter("turn");
		if (turn != null) 
		{
			//System.out.println("state discoverable");
			progressGame(request, response);
		}
		
		// Code to create a new game.
		String newGame = request.getParameter("new");
		if (newGame != null)
		{
			if (request.getAttribute("state") != null)
			{
				System.out.println("error here");
			}
			else 
			{
				if (newGame.equals("true"))
				{
					startNewGame(request, response);
				}
				else 
				{
					loadGame(request, response);
				}
			}
		}
		
		
		
		
		
		
	}
	
	private void startNewGame(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		// Clears the current session variables.
		session.invalidate();
		
		// Create a new session and game state.
		session = request.getSession();
		GameState gs = new GameState();
		
		// Creates some random ordering.
		String rndString = createRndStr();
		
		// According to ordering assign text values for each case.
		for (int i = 0, j = 0; i < rndString.length(); i++, j++)
		{
			switch (String.valueOf(rndString.charAt(j)))
			{
				case "0":
					setCase(gs, j, "0.50");
					break;
				case "1":
					setCase(gs, j, "1");
					break;
				case "2":
					setCase(gs, j, "10");
					break;
				case "3":
					setCase(gs, j, "100");
					break;
				case "4":
					setCase(gs, j, "200");
					break;
				case "5":
					setCase(gs, j, "500");
					break;
				case "6":
					setCase(gs, j, "1000");
					break;
				case "7":
					setCase(gs, j, "2000");
					break;
				case "8":
					setCase(gs, j, "5000");
					break;
				case "9":
					setCase(gs, j, "10000");
					break;
				case "A":
					setCase(gs, j, "20000");
					break;
				case "B":
					setCase(gs, j, "50000");
					break;
			}
		}
		
		// Sends the bean to the session.
		session.setAttribute("state", gs);
		
		// Passes values to jsp.
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/DoND.jsp");
		dispatcher.forward(request, response);
	}
	
	private String createRndStr() 
	{
		// Creates a random string using the characters in string choose.
		
		Random r = new Random();
		String outStr = "", choose = "0123456789AB";
		
		while (choose.length() > 0) 
		{
			int randomInt = r.nextInt(choose.length());
			String genStr = String.valueOf(choose.charAt(randomInt));
			outStr += genStr;
			choose = choose.replace(genStr, "");
		}
		
		return outStr;
	}
	
	private void loadGame(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String searchUser = request.getParameter("userName");
		
		//Simple sanitisation to prevent XSS
		searchUser = searchUser.replace("<", "&lt;");
		searchUser = searchUser.replace(">", "&gt;");
		searchUser = searchUser.toLowerCase();
		session.invalidate();
		
		// Create the bean.
		session = request.getSession();
		GameState gs = new GameState();
		
		// Create variables for tracking file input and output.
		String[] tokenLine;
		String input, output = "";
		BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
		
		// While there are lines left in the file, check if the current lines username( input.split(",")[0] ), matches the searchUser value.
		// At the same time repeatedly append input to output. Unless a match is found then mark the buffered reader.
		while ((input = reader.readLine()) != null)
		{
			String[] tokenInput = input.split(",");
			if (searchUser != null) 
			{
				if (tokenInput[0].equals(searchUser)) 
				{
					//mark the current line with buffer size 1000.
					reader.mark(1000);
					
					// Load values into the bean.
					
					// Loads opened state of each case, true being opened.
					gs.setCaseA(Boolean.valueOf(tokenInput[2]));
					gs.setCaseB(Boolean.valueOf(tokenInput[3]));
					gs.setCaseC(Boolean.valueOf(tokenInput[4]));
					gs.setCaseD(Boolean.valueOf(tokenInput[5]));
					gs.setCaseE(Boolean.valueOf(tokenInput[6]));
					gs.setCaseF(Boolean.valueOf(tokenInput[7]));
					gs.setCaseG(Boolean.valueOf(tokenInput[8]));
					gs.setCaseH(Boolean.valueOf(tokenInput[9]));
					gs.setCaseI(Boolean.valueOf(tokenInput[10]));
					gs.setCaseJ(Boolean.valueOf(tokenInput[11]));
					gs.setCaseK(Boolean.valueOf(tokenInput[12]));
					gs.setCaseL(Boolean.valueOf(tokenInput[13]));
					
					// According to ordering (2nd csv) assign text values for each case.
					for (int i = 2, j = 0; i < tokenInput.length; i++, j++)
					{
						switch (String.valueOf(tokenInput[1].charAt(j)))
						{
							case "0":
								setCase(gs, j, "0.50");
								break;
							case "1":
								setCase(gs, j, "1");
								break;
							case "2":
								setCase(gs, j, "10");
								break;
							case "3":
								setCase(gs, j, "100");
								break;
							case "4":
								setCase(gs, j, "200");
								break;
							case "5":
								setCase(gs, j, "500");
								break;
							case "6":
								setCase(gs, j, "1000");
								break;
							case "7":
								setCase(gs, j, "2000");
								break;
							case "8":
								setCase(gs, j, "5000");
								break;
							case "9":
								setCase(gs, j, "10000");
								break;
							case "A":
								setCase(gs, j, "20000");
								break;
							case "B":
								setCase(gs, j, "50000");
								break;
						}
					}
					
					double[] dealState = checkDeal(gs);
					DecimalFormat df = new DecimalFormat("#.##");
					if (dealState[2] > 0){
						String bankDeal = df.format(dealState[2]);
						session.setAttribute("bankDeal", bankDeal);
					}
					
					if (dealState[1] == -1.0){
						session.setAttribute("amount", dealState[2]);
						
						// Passes values to jsp.
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/Win.jsp");
						dispatcher.forward(request, response);
					}
					
					session.setAttribute("opened", (int) dealState[0]);
					session.setAttribute("round", (int) dealState[1]);
					
					
					// Sends the bean to the session.
					session.setAttribute("state", gs);
					break;
				}
				else 
				{
					output +=  input + "\n";
				}
			}
		}
		
		// try-catch block attempts to reset the reader, if it is successful it appends the remaining lines in the file to output.
		try 
		{
			// Resets the reader to the line after the chosen line
			reader.reset();
			
			while ((input = reader.readLine()) != null)
			{
				output +=  input + "\n";
			}
			output = output.substring(0, output.length() - 1);
			reader.close();
			
			// Lastly try write the output string to file.
			try 
			{
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("../webapps/c3259038_Assignment2/WEB-INF/saves/saves.txt")));
				pw.print(output);
				pw.close();
			} catch (IOException e)
			{
				System.out.println("Couldnt save to file");
			}
		}
		catch (IOException e) {}	// Expected to caught, if no username is matched.
		finally 
		{
			reader.close();
			
			// Passes values to jsp.
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/DoND.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}
	
	private void setCase(GameState gs, int casePos, String value)
	{
		// Sets case at casePos to value in gamestate gs.
		switch (casePos){
			case 0:
				gs.setCase1(value);
				break;
			case 1:
				gs.setCase2(value);
				break;
			case 2:
				gs.setCase3(value);
				break;
			case 3:
				gs.setCase4(value);
				break;
			case 4:
				gs.setCase5(value);
				break;
			case 5:
				gs.setCase6(value);
				break;
			case 6:
				gs.setCase7(value);
				break;
			case 7:
				gs.setCase8(value);
				break;
			case 8:
				gs.setCase9(value);
				break;
			case 9:
				gs.setCase10(value);
				break;
			case 10:
				gs.setCase11(value);
				break;
			case 11:
				gs.setCase12(value);
				break;
		}
	}
	
	private void progressGame(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException
	{
		// Loads data from current session into local variables then terminates the session.
		HttpSession session = request.getSession();
		GameState gs = (GameState)session.getAttribute("state");
		String turn = request.getParameter("turn");
		session.invalidate();
		
		// Creates a new session ready for output.
		session = request.getSession();
		
		// If a turn session variable was detected set relevant cases values.
		if (turn != null){
			switch (turn)
			{
				case "A":
					gs.setCaseA(true);
					break;
				case "B":
					gs.setCaseB(true);
					break;
				case "C":
					gs.setCaseC(true);
					break;
				case "D":
					gs.setCaseD(true);
					break;
				case "E":
					gs.setCaseE(true);
					break;
				case "F":
					gs.setCaseF(true);
					break;
				case "G":
					gs.setCaseG(true);
					break;
				case "H":
					gs.setCaseH(true);
					break;
				case "I":
					gs.setCaseI(true);
					break;
				case "J":
					gs.setCaseJ(true);
					break;
				case "K":
					gs.setCaseK(true);
					break;
				case "L":
					gs.setCaseL(true);
					break;
				default:
					break;
			}
		}
		
		double[] dealState = checkDeal(gs);
		DecimalFormat df = new DecimalFormat("#.##");
		if (dealState[2] > 0){
			String bankDeal = df.format(dealState[2]);
			session.setAttribute("bankDeal", bankDeal);
		}
		if (dealState[1] == -1.0){
			session.setAttribute("amount", dealState[2]);
			
			// Passes values to jsp.
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/Win.jsp");
			dispatcher.forward(request, response);
		}
		session.setAttribute("opened", (int) dealState[0]);
		session.setAttribute("round", (int) dealState[1]);
		
		// Sends the bean to the session.
		session.setAttribute("state", gs);
		
		// Passes values to jsp.
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/DoND.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void saveGame(String out)
	{
		// Appends string out to the end of the file saves.txt
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME, true)));
			pw.print(out);
			pw.close();
		} catch (IOException e) {
			//throw some error
		}
	}
	
	private String caseToStr(String caseVal)
	{ 
		// Encodes case values to relevant file values.
		// "0.50" -> "0", "1" -> "1", "20000" -> "A", etc.
		switch (caseVal)
		{
			case "0.50":
				return "0";
			case "1":
				return "1";
			case "10":
				return "2";
			case "100":
				return "3";
			case "200":
				return "4";
			case "500":
				return "5";
			case "1000":
				return "6";
			case "2000":
				return "7";
			case "5000":
				return "8";
			case "10000":
				return "9";
			case "20000":
				return "A";
			case "50000":
				return "B";
			default:
				return "";
		}
	}
	
	private double[] checkDeal(GameState gs){
		// Returns a double array (output) with the following values:
		// 		output[0] = how many cases are opened in the current round.
		// 		output[1] = the current round.
		// 		output[2] = if this is a bank deal round it will return a bank offer.
		
		int opened = countOpen(gs);
		double output[] = { 0.0, 0.0, 0.0 };
		
		switch (opened)
		{
			case 1:
			case 2:
			case 3:
				output[0] = opened;
				output[1] = 4;
				break;
			case 5:
			case 6:
				output[0] = opened - 4;
				output[1] = 3;
				break;
			case 8:
				output[0] = 1;
				output[1] = 2;
				break;
				
			case 4:
				if (opened == 4)	{output[0] = 4; output[1] = 4;}
			case 7:
				if (opened == 7)	{output[0] = 3; output[1] = 3;}
			case 9:
				if (opened == 9)	{output[0] = 2; output[1] = 2;}
			case 10:
				if (opened == 10)	{output[0] = 1; output[1] = 1;}
				
				gs.setIsDealRnd(true);
				output[2] = sumUnopened(gs) / (12 - opened);
				break;
			case 11:
				if (opened == 11)	{output[0] = 1; output[1] = -1.0;}	//sentinel value to mark when to forward user to winners page
				gs.setIsDealRnd(true);
				output[2] = sumUnopened(gs) / (12 - opened);
				return output;
			default:
				return output;
		}
		return output;
	}
	
	private int countOpen(GameState gs){
		boolean[] cases = new boolean[12];
		cases[0] = gs.getCaseA();
		cases[1] = gs.getCaseB();
		cases[2] = gs.getCaseC();
		cases[3] = gs.getCaseD();
		cases[4] = gs.getCaseE();
		cases[5] = gs.getCaseF();
		cases[6] = gs.getCaseG();
		cases[7] = gs.getCaseH();
		cases[8] = gs.getCaseI();
		cases[9] = gs.getCaseJ();
		cases[10] = gs.getCaseK();
		cases[11] = gs.getCaseL();
		
		int count = 0;
		for (boolean caseState : cases){
			if (caseState == true)			{count++;}
		}
		
		return count;
	}
	
	private double sumUnopened(GameState gs){
		double sum = 0;
		if (gs.getCaseA() == false)			{sum += Double.valueOf(gs.getCase1());}
		if (gs.getCaseB() == false)			{sum += Double.valueOf(gs.getCase2());}
		if (gs.getCaseC() == false)			{sum += Double.valueOf(gs.getCase3());}
		if (gs.getCaseD() == false)			{sum += Double.valueOf(gs.getCase4());}
		if (gs.getCaseE() == false)			{sum += Double.valueOf(gs.getCase5());}
		if (gs.getCaseF() == false)			{sum += Double.valueOf(gs.getCase6());}
		if (gs.getCaseG() == false)			{sum += Double.valueOf(gs.getCase7());}
		if (gs.getCaseH() == false)			{sum += Double.valueOf(gs.getCase8());}
		if (gs.getCaseI() == false)			{sum += Double.valueOf(gs.getCase9());}
		if (gs.getCaseJ() == false)			{sum += Double.valueOf(gs.getCase10());}
		if (gs.getCaseK() == false)			{sum += Double.valueOf(gs.getCase11());}
		if (gs.getCaseL() == false)			{sum += Double.valueOf(gs.getCase12());}
		return sum;
	}
}