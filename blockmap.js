var mymap = L.map('mapid').setView([28.3949, 84.1240], 8);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.dark',
    accessToken: 'pk.eyJ1IjoicHJvaml0MzIiLCJhIjoiY2p1eTk4ZDZwMGVzNTN6bnJrNXl0ajZuMCJ9.n8viqDzsRQL8E0ELqB_wcw'
}).addTo(mymap);

function getColor(d) {
    console.log(d);
    return d == 5 ? '#800026' :
           d == 4 ? '#BD0026' :
           d == 3 ? '#E31A1C' :
           d == 2 ? '#FC4E2A' :
           d == 1 ? '#FD8D3C' : '#FFEDA0';
}

function style(feature) {
    return {
        fillColor: getColor(intense[feature.properties.DISTRICT]),
        weight: 2,
        opacity: 1,
        color: 'white',
        dashArray: '3',
        fillOpacity: 0.7
    };
}
L.geoJson(stateData, {style: style}).addTo(mymap);