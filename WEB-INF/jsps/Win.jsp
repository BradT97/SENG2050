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
			
			<form class="centered flexContainer" action="<%=request.getContextPath()%>/Index" method="post">
				<h2 class="subHeading">Congratulations!</h2>
				<div>You won &dollar;${amount}</div>
				<button type="submit" style="margin-top: 160px;" class="otherBtn">Return to Home</button>
			</form>
		</div>
		
		<jsp:include page="Footer.jsp" />
	</body>
</html>