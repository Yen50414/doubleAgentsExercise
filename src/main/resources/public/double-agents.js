var mapCenter = [39.09596, -95.80078];
var defaultZoom = 4;

var maxZ = 18;
var minZ = 4;
var mapboxId = 'moaboa';
var mapboxAT = 'pk.eyJ1IjoibW9hYm9hIiwiYSI6ImNpdXMwcm4wbzAwYXMyeXFqenpxYWJ6bzIifQ.3jc-XUbb1Crk6ruYsOWhxA';
// Dark map
var mapUrl = 'https://api.mapbox.com/styles/v1/' + mapboxId + '/ciuxwzl0t004b2ipqrkqoy43p/tiles/256/{z}/{x}/{y}?access_token=' + mapboxAT;
// Default map from mapbox
//var mapUrl = 'https://api.mapbox.com/styles/v1/mapbox/streets-v10/tiles/256/{z}/{x}/{y}?access_token=' + mapboxAT;


var mymap;
var agentsData = [];
var agentMarkers = [];
var maleMarkers = L.layerGroup();
var femaleMarkers = L.layerGroup();

// Run code once document is finished loading.
$(document).ready( function () {

	// Draw map through leafletjs
	mymap = drawMap();
	
	// Draw initial data points
	drawData();
	mymap.addLayer(maleMarkers);
	mymap.addLayer(femaleMarkers);
	
	// Add layer control to filter male vs female
	var overlayMaps = {
		"Male": maleMarkers,
		"Female": femaleMarkers
	};
	L.control.layers(null, overlayMaps, { collapsed: false }).addTo(mymap);
	
	// Redraw if filter is changed
	$(".filter").change( function() {
		// Clear existing markers
		maleMarkers.clearLayers();
		femaleMarkers.clearLayers();
		
		drawAgents($("#minAge").val(), $("#maxAge").val(), $("#search").val());
	});
})

function drawMap() {
	var mymap = L.map('mapid').setView(mapCenter, defaultZoom);
			
	L.tileLayer(mapUrl, {
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> ' +
			'contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
		maxZoom: maxZ,
		minZoom: minZ,
		id: mapboxId,
		accessToken: mapboxAT
	}).addTo(mymap);
	
	return mymap;
}

function drawData() {
	// Get data from application endpoint
	$.get("/loadAgents", function(data, status) {
		// For reasons I don't fully understand, agentsData = data doesn't work 
		// here when async = true but that option is deprecated in jQuery 1.8, 
		// so I'm adding each item to the array instead.
		data.forEach( function(agent) {
			agentsData.push(agent);
		});
	}).done( function() {
		drawAgents($("#minAge").val(), $("#maxAge").val(), $("#search").val());
	});
}

function drawAgents(minAge, maxAge, term) {
	// Loop through all data points
	agentsData.forEach( function(agent) {
		// Draw only if age less than max age
		if(agent.age >= minAge && agent.age < maxAge
		&& agent.name.toLowerCase().includes(term.toLowerCase())) {
			drawAgent(agent);
		}
	});
}

function drawAgent(agentData) {
	var color;
	var markerLayer;
	if(agentData.gender == "Male") {
		color = 'Aquamarine';
		markerLayer = maleMarkers;
	} else {
		color = 'Orchid';
		markerLayer = femaleMarkers;
	}
	
	var marker = L.circleMarker([agentData.latitude,agentData.longitude], {
		color: color,
		fillColor: color,
		fillOpacity: 0.5,
		radius : 10
	});
	marker.bindPopup("<b>" + agentData.name + "</b><br>" + agentData.age + ", " + agentData.gender);
	
	markerLayer.addLayer(marker);
	
	return marker;
}