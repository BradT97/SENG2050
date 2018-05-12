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
				<h2 class="subHeading">Load Saved Game</h2>
				<input name="new" type="hidden" value="false">
				<div>Please Enter your User Name: <input name="userName" type="text" style="margin-left: 40px; width: 340px; font-size: var(--mainFontSize);"></div>
				<button type="submit" class="otherBtn">Load Game!</button>
			</form>
		</div>
		
		<jsp:include page="Footer.jsp" />
	</body>
</html>