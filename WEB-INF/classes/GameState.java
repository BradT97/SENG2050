package beans;

import java.io.Serializable;
public class GameState implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private boolean isDealRnd, caseA, caseB, caseC, caseD, caseE, caseF, caseG, caseH, caseI, caseJ, caseK, caseL;
	private String case1, case2, case3, case4, case5, case6, case7, case8, case9, case10, case11, case12;
	public GameState(){
		isDealRnd = false;
		
		// Fills cases with set values.
		caseA = false;
		caseB = false;
		caseC = false;
		caseD = false;
		caseE = false;
		caseF = false;
		caseG = false;
		caseH = false;
		caseI = false;
		caseJ = false;
		caseK = false;
		caseL = false;
		case1 = "0.50";
		case2 = "1";
		case3 = "10";
		case4 = "100";
		case5 = "200";
		case6 = "500";
		case7 = "1000";
		case8 = "2000";
		case9 = "5000";
		case10 = "10000";
		case11 = "20000";
		case12 = "50000";

	}
	
	// Setters
	public void setCaseA(boolean state)	{caseA = state;}
	public void setCaseB(boolean state)	{caseB = state;}
	public void setCaseC(boolean state)	{caseC = state;}
	public void setCaseD(boolean state)	{caseD = state;}
	public void setCaseE(boolean state)	{caseE = state;}
	public void setCaseF(boolean state)	{caseF = state;}
	public void setCaseG(boolean state)	{caseG = state;}	
	public void setCaseH(boolean state)	{caseH = state;}	
	public void setCaseI(boolean state)	{caseI = state;}	
	public void setCaseJ(boolean state)	{caseJ = state;}	
	public void setCaseK(boolean state)	{caseK = state;}	
	public void setCaseL(boolean state)	{caseL = state;}
	
	public void setCase1(String value)		{case1 = value;}
	public void setCase2(String value)		{case2 = value;}
	public void setCase3(String value)		{case3 = value;}
	public void setCase4(String value)		{case4 = value;}
	public void setCase5(String value)		{case5 = value;}
	public void setCase6(String value)		{case6 = value;}
	public void setCase7(String value)		{case7 = value;}	
	public void setCase8(String value)		{case8 = value;}	
	public void setCase9(String value)		{case9 = value;}	
	public void setCase10(String value)		{case10 = value;}	
	public void setCase11(String value)		{case11 = value;}	
	public void setCase12(String value)		{case12 = value;}

	public void setIsDealRnd(boolean isDealRnd)			{this.isDealRnd = isDealRnd;}
	//Getters
	public boolean getCaseA()			{return caseA;}
	public boolean getCaseB()			{return caseB;}
	public boolean getCaseC()			{return caseC;}
	public boolean getCaseD()			{return caseD;}
	public boolean getCaseE()			{return caseE;}
	public boolean getCaseF()			{return caseF;}
	public boolean getCaseG()			{return caseG;}
	public boolean getCaseH()			{return caseH;}
	public boolean getCaseI()			{return caseI;}
	public boolean getCaseJ()			{return caseJ;}
	public boolean getCaseK()			{return caseK;}
	public boolean getCaseL()			{return caseL;}
	
	public String getCase1()			{return case1;}
	public String getCase2()			{return case2;}
	public String getCase3()			{return case3;}
	public String getCase4()			{return case4;}
	public String getCase5()			{return case5;}
	public String getCase6()			{return case6;}
	public String getCase7()			{return case7;}
	public String getCase8()			{return case8;}
	public String getCase9()			{return case9;}
	public String getCase10()			{return case10;}
	public String getCase11()			{return case11;}
	public String getCase12()			{return case12;}
	
	public boolean getIsDealRnd()					{return isDealRnd;}
}