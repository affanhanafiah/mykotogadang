function aktifkanfungsi1() {
    hapusdata();
    hapusMarkerTerdekat();
    hapusInfo();
    clearroute2();
    clearangkot();
    clearroute();
    if (koordinat == 'null') {
        alert('Click the Button of Your Position Beforehand');
    } else {
        hapusRadius();
        var inputradius = document.getElementById("fungsi1radius").value;
        var circle = new google.maps.Circle({
            center: koordinat,
            radius: parseFloat(inputradius * 100),
            map: map,
            strokeColor: "blue",
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: "blue",
            fillOpacity: 0.35
        });
        map.setZoom(12);
        map.setCenter(koordinat);
        circles.push(circle);
    }
    cekRadiusStatus = 'on';
    fungsionalsatu();
}