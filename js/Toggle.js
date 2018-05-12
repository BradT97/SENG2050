//toggle function for visibility
function toggle(state)
{
	var E = document.getElementsByClassName("hides");
	for (var i = 0; i < E.length; i++)
	{
		if (state == true){
			E[i].style.visibility = "visible";
		}
		else {
			E[i].style.visibility = "hidden";
		}
	}
	
	var R = document.getElementsByClassName("round");
	try {
		R[0].style.visibility = "hidden";
	}
	catch (err) {} 
	
	
	
}

function hide()
{
	var F = document.getElementsByClassName("false");
	for (var i = 0; i < F.length; i++)
	{
		console.log(F[i].innerHTML)
		F[i].innerHTML = "";
	}
}
