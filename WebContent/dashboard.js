console.log('im a console log');
var filterDropdown = document.getElementById('filters');
console.log('var filter = ' + filterDropdown);

filterDropdown.addEventListener('change', function() {
  filterDashboard();
});

function filterDashboard() {
	console.log('filterDashboard function called');
	console.log(filterDropdown.options[filterDropdown.selectedIndex].value);
	/*
    var request = new XMLHttpRequest();
	request.open("POST", "Dashboard", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	request.onreadystatechange = function(){
		if (request.readyState == 4 && request.status == 200) {
			console.log("in readystate changed");
			document.getElementById("statistic-cards").innerHTML = request.responseText;
		}
	};
	request.send("filter=" + filter);
	*/
}

