<%@page import="beans.GameState" %>

<!DOCTYPE html>
<html>
	<head>
		
		<title>Deal or No Deal</title>
		<%	//Prevents the user from reloading the page
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache"); 
		response.setHeader ("Expires", "0"); 
		%>
		
		<link rel='stylesheet' type='text/css' href="<%=request.getContextPath()%>/css/Index.css">
		<link rel='stylesheet' type='text/css' href="<%=request.getContextPath()%>/css/DoND.css">
		<script src="<%=request.getContextPath()%>/js/Game.js"></script>
		<script src="<%=request.getContextPath()%>/js/Toggle.js"></script>
		<script>
		// Script sourced from https://stackoverflow.com/questions/13246378/detecting-user-inactivity-over-a-browser-purely-through-javascript
		var IDLE_TIMEOUT = 60; //seconds
		var _idleSecondsCounter = 0;
		document.onclick = function() {
			_idleSecondsCounter = 0;
		};
		document.onmousemove = function() {
			_idleSecondsCounter = 0;
		};
		document.onkeypress = function() {
			_idleSecondsCounter = 0;
		};
		window.setInterval(CheckIdleTime, 1000);

		function CheckIdleTime() {
			_idleSecondsCounter++;
			if (_idleSecondsCounter >= IDLE_TIMEOUT) {
				var s = document.getElementsByName("saveQuit");
				s[0].click();
			}
		}
		</script>
	</head>
	<body onload = 'toggle(<jsp:getProperty name="state" property="isDealRnd" />); hide();'>
		<jsp:useBean id="state" scope="session" class="beans.GameState" />
		<jsp:include page="Header.jsp" />
		
		
		<div class="main grid">
			<form class="leftCol" action="<%=request.getContextPath()%>/Main" method="post">
				<div class="flexContainer spaced">
					<button name="turn" class='<jsp:getProperty name="state" property="caseA" />' value="A" onClick='return !(<jsp:getProperty name="state" property="caseA" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case1" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseB" />' value="B" onClick='return !(<jsp:getProperty name="state" property="caseB" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case2" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseC" />' value="C" onClick='return !(<jsp:getProperty name="state" property="caseC" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case3" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseD" />' value="D" onClick='return !(<jsp:getProperty name="state" property="caseD" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case4" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseE" />' value="E" onClick='return !(<jsp:getProperty name="state" property="caseE" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case5" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseF" />' value="F" onClick='return !(<jsp:getProperty name="state" property="caseF" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case6" /></button>
				</div>
			</form>
			
			
			<div class="centerCol">
				<!-- If turn = checkForBankOffer == true -->
				<div class="bankOfferContainer flexContainer hides">
					<div>Bank Offer</div>
					<div>&dollar;${bankDeal}</div>
				</div>
				<div style="color:#D4AF37;text-align:center;" class="round${round}">
					Cases per Round ${opened} / ${round}
				</div>
				<form class="buttonWrapper hides" method="post">
					<input type="hidden" name="amount" value="${bankDeal}">
					<button name="bankDeal" type="submit" value="true">Deal</button>
					<button name="bankDeal" type="submit" value="false">No Deal</button>
				</form>
				
				<form class="buttonWrapper" action="<%=request.getContextPath()%>/Main" method="post">
					<button name="saveQuit" type="submit" value="true" >Save and Quit</button>
					<button name="saveQuit" type="submit" value="false" onClick="return doubleCheck()">Quit without Saving</button>
				</form>
				
				
			</div>
			
			
			<form class="rightCol" action="<%=request.getContextPath()%>/Main" method="post">
				<div class="flexContainer spaced">
					<button name="turn" class='<jsp:getProperty name="state" property="caseG" />' value="G" onClick='return !(<jsp:getProperty name="state" property="caseG" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case7" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseH" />' value="H" onClick='return !(<jsp:getProperty name="state" property="caseH" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case8" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseI" />' value="I" onClick='return !(<jsp:getProperty name="state" property="caseI" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case9" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseJ" />' value="J" onClick='return !(<jsp:getProperty name="state" property="caseJ" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case10" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseK" />' value="K" onClick='return !(<jsp:getProperty name="state" property="caseK" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case11" /></button>
					<button name="turn" class='<jsp:getProperty name="state" property="caseL" />' value="L" onClick='return !(<jsp:getProperty name="state" property="caseL" /> || <jsp:getProperty name="state" property="isDealRnd" />)'>&dollar;<jsp:getProperty name="state" property="case12" /></button>
				</div>
			</form>
		</div>
		
		
		
		<jsp:include page="Footer.jsp" />
	
	</body>
</html>