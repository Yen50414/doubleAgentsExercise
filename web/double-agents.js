function drawPoint(latlng) {
	//var point] = L.marker(latlng).addTo(mymap);
	var point = L.circle(latlng, {
		color: 'blue',
		fillColor: '#36c',
		fillOpacity: 0.5,
		radius: 500
	});
	point.bindPopup("<b>Jilted Seahorse</b><br>48, Male");
	return point;
}

function sayHello() {
	alert("Hello World from double-agent.js")
}