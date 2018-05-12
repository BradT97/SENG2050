<!DOCTYPE html>
<html>
	<head>
		<title>Deal or No Deal</title>
		<meta charset="UTF-8">
		<link rel='stylesheet' type='text/css' href="<%=request.getContextPath()%>/css/Index.css">
	</head>
	<body>
		<jsp:include page="Header.jsp" />
		
		<div class="main grid">
			
			<form class="centered flexContainer" action="<%=request.getContextPath()%>/Main" method="post">
				<h2 class="subHeading">Save Game</h2>
				<input name="saveQuit" type="hidden" value="true">
				<div>Please Enter a User Name: <input name="saveName" type="text" style="margin-left: 40px; width:340px; font-size: var(--mainFontSize);"></div>
				<button type="submit" class="otherBtn">Save</button>
			</form>
		</div>
		
		<jsp:include page="Footer.jsp" />
	</body>
</html>