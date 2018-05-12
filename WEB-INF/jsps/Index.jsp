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
				<button name="new" type="submit" class="btn"  value="false">
					Continue an Existing Game
				</button>
				<p class="or">OR</p>
			</form>
				
			<form class="centered flexContainer" action="<%=request.getContextPath()%>/Main" method="post">
				<button name="new" type="submit" class="btn" value="true">
					Start a New Game
				</button>
			</form>

		</div>
		
		
		
		<jsp:include page="Footer.jsp" />
	
	</body>
</html>