var mymap = L.map('mapid').setView([28.3949, 84.1240], 8);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.dark',
    accessToken: 'pk.eyJ1IjoicHJvaml0MzIiLCJhIjoiY2p1eTk4ZDZwMGVzNTN6bnJrNXl0ajZuMCJ9.n8viqDzsRQL8E0ELqB_wcw'
}).addTo(mymap);

L.geoJSON(stateData).addTo(mymap);

function setIntensity(item){
    var val = [parseFloat(item.lat), parseFloat(item.lng), 100];
    return val;
}
fetch('https://proapplication.000webhostapp.com/AngelHacks/ShowJSON.php')
.then(response => response.json())
.then(json => json.map(setIntensity))
.then(arr => new L.HeatLayer(arr, {radius : 25}).addTo(mymap));
