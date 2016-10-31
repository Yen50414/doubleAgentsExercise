var mapCenter = [39.09596, -95.80078];
var defaultZoom = 4;

var mymap;
var agentsData = [];
var agentMarkers = [];

// Run code once document is finished loading.
$(document).ready( function () {

	// Draw map through leafletjs
	mymap = drawMap();
	
	// Somehow obtain data points from csv file
	drawAgents();
})

function drawMap() {
	var mymap = L.map('mapid').setView(mapCenter, defaultZoom);
			
	L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/streets-v10/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoibW9hYm9hIiwiYSI6ImNpdXMwcm4wbzAwYXMyeXFqenpxYWJ6bzIifQ.3jc-XUbb1Crk6ruYsOWhxA', {
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> ' +
			'contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
		maxZoom: 18,
		minZoom: defaultZoom,
		id: 'moaboa',
		accessToken: 'pk.eyJ1IjoibW9hYm9hIiwiYSI6ImNpdXMwcm4wbzAwYXMyeXFqenpxYWJ6bzIifQ.3jc-XUbb1Crk6ruYsOWhxA'
	}).addTo(mymap);
	
	return mymap;
}

function drawAgents() {
	// Get data from application endpoint
	$.get("/loadAgents", function(data, status) {
		// For reasons I don't fully understand, agentsData = data doesn't work 
		// here when async = true but that option is deprecated in jQuery 1.8, 
		// so I'm adding each item to the array instead.
		data.forEach( function(agent) {
			agentsData.push(agent);
		});
	}).done( function() {
		alert("done");
		drawAgents();
	});
}

function drawAgents() {
	// Loop through all data points
	alert(agentsData);
	console.log(agentsData);
	alert(agentsData.length);
	console.log(agentsData.length);
	agentsData.forEach( function(agent) {
		console.log(agent);
		agentMarkers.push(drawAgent(agent));
	});
}

function drawAgent(agentData) {
	console.log(agentData);
	var point = L.marker([agentData.latitude,agentData.longitude]).addTo(mymap);
	/*var point = L.circle(latlng, {
		color: 'blue',
		fillColor: '#36c',
		fillOpacity: 0.5,
		radius: 500
	});
	point.bindPopup("<b>Jilted Seahorse</b><br>48, Male");*/
	return point;
}

function sayHello() {
	alert("Hello World from double-agent.js")
}