var variableName = document.getElementById("buttonID");

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
	if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
    		variableName.style.display = "block";
  	} else {
    		variableName.style.display = "none";
  	}
}

function functionName() {
  	document.body.scrollTop = 0; //For Safari
  	document.documentElement.scrollTop = 0; //For Chrome
}

