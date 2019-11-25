function fungsionalsatu() {
    var fungsi1nama = document.getElementById('fungsi1nama').value;
    var fungsi1produk = document.getElementById('fungsi1produk').value;
    var fungsi1jenis = document.getElementById('fungsi1jenis').value;
    var fungsi1radius = document.getElementById('fungsi1radius').value;
    console.log("radius : " + fungsi1radius);
    if (fungsi1nama == "" || fungsi1produk == "" || fungsi1jenis == "" || fungsi1radius == "") {
        alert("Please fill all data !");
    } else {

        $('#hasilcari').append;
        hapusdata();
        hapusInfo();
        hapusInfo2();
        clearroute2();
        clearangkot();
        clearroute();
        hapusangkot();
        hapusMarkerTerdekat();
        $("#filterik").hide();
        $('#hasilik').show();
        $('#hasilcari1').show();
        $('#hasilcari').empty();
        cekRadius();
        console.log(server + 'fungsional1.php?cari_name=' + fungsi1nama + '&cari_jenis=' + fungsi1jenis + '&cari_product=' + fungsi1produk + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi1radius);
        // clearangkot();
        $.ajax({
            url: server + 'fungsional1.php?cari_name=' + fungsi1nama + '&cari_jenis=' + fungsi1jenis + '&cari_product=' + fungsi1produk + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi1radius,
            data: "",
            dataType: 'json',
            success: function (rows) {

                if (rows == null) {
                    alert('Data Did Not Exist !');
                }
                for (var i in rows) {
                    $('#hasilcari').append;
                    var row = rows[i];
                    var id = row.id;
                    var name = row.name;
                    //var tipe = row.type;
                    //var produk   = row.id_product;
                    var lat = row.latitude;
                    var lon = row.longitude;
                    //var jarak = row.jarak;
                    centerBaru = new google.maps.LatLng(lat, lon);
                    marker = new google.maps.Marker({
                        position: centerBaru,
                        map: map,
                        icon: "assets/img/industries.png",
                    });
                    // console.log(lat);
                    // console.log(lon);
                    markersDua.push(marker);
                    map.setCenter(centerBaru);
                    klikinfoWindow(barubana, id);
                    map.setZoom(14);
                    console.log(name);
                    $('#hasilcari').append("<tr><td>" + name + "</td><td><a role='button' class='btn btn-success' onclick='detailinfoumkm(\"" + id + "\")'>Show</a></td><td><a role='button' class='btn btn-danger fa fa-car' onclick='angkotSekitarUMKM(\"" + lat + "\",\"" + lon + "\",\"" + name + "\",\"" + id + "\")'></a><td><a role='button' class='btn btn-danger fa fa-road' onclick='callRoute(centerLokasi,centerBaru);rutetampil();'></a></td></tr>");
                }
            }
        });
    }
}

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
}

function aktifkanfungsi2() {
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
        var inputradius = document.getElementById("fungsi2radius").value;
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
}

function fungsionaldua() {
    var fungsi2nama = document.getElementById('fungsi2nama').value;
    var fungsi2menu = document.getElementById('fungsi2menu').value;
    var fungsi2angkot = document.getElementById('fungsi2angkot').value;
    var fungsi2radius = document.getElementById('fungsi2radius').value;
    console.log("radius : " + fungsi2radius);
    if (fungsi2nama == "" || fungsi2menu == "" || fungsi2angkot == "" || fungsi2radius == "") {
        alert("Please fill all data !");
    } else {

        $('#hasilcari').append;
        hapusdata();
        hapusInfo();
        hapusInfo2();
        clearroute2();
        clearangkot();
        clearroute();
        hapusangkot();
        hapusMarkerTerdekat();
        $("#filterik").hide();
        $('#hasilik').show();
        $('#hasilcari1').show();
        $('#hasilcari').empty();
        cekRadius();
        console.log(server + 'fungsional2.php?cari_name=' + fungsi2nama + '&cari_menu=' + fungsi2menu + '&cari_angkot=' + fungsi2angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi2radius);
        // clearangkot();
        $.ajax({
            url: server + 'fungsional2.php?cari_name=' + fungsi2nama + '&cari_menu=' + fungsi2menu + '&cari_angkot=' + fungsi2angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi2radius,
            data: "",
            dataType: 'json',
            success: function (rows) {

                if (rows == null) {
                    alert('Data Did Not Exist !');
                }
                for (var i in rows) {
                    $('#hasilcari').append;
                    var row = rows[i];
                    var id = row.id;
                    var name = row.name;
                    //var tipe = row.type;
                    //var produk   = row.id_product;
                    var lat = row.latitude;
                    var lon = row.longitude;
                    //var jarak = row.jarak;
                    centerBaru = new google.maps.LatLng(lat, lon);
                    marker = new google.maps.Marker({
                        position: centerBaru,
                        map: map,
                        icon: "assets/img/restaurants.png",
                    });
                    // console.log(lat);
                    // console.log(lon);
                    markersDua.push(marker);
                    map.setCenter(centerBaru);
                    klikinfoWindow2(barubana2, id);
                    map.setZoom(14);
                    console.log(name);
                    $('#hasilcari').append("<tr><td>" + name + "</td><td><a role='button' class='btn btn-success' onclick='detailinfokul(\"" + id + "\")'>Show</a></td><td><a role='button' class='btn btn-danger fa fa-car' onclick='angkotSekitarrm(\"" + lat + "\",\"" + lon + "\",\"" + name + "\",\"" + id + "\")'></a></td><td><a role='button' class='btn btn-danger fa fa-road' onclick='callRoute(centerLokasi,centerBaru);rutetampil();'></a></td></tr>");
                }
            }
        });
    }
}

function aktifkanfungsi3() {
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
        var inputradius = document.getElementById("fungsi3radius").value;
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
}

function fungsionaltiga() {
    var fungsi3nama = document.getElementById('fungsi3nama').value;
    var fungsi3fas = document.getElementById('fungsi3fas').value;
    var fungsi3angkot = document.getElementById('fungsi3angkot').value;
    var fungsi3radius = document.getElementById('fungsi3radius').value;
    console.log("radius : " + fungsi3radius);
    if (fungsi3nama == "" || fungsi3fas == "" || fungsi3angkot == "" || fungsi3radius == "") {
        alert("Please fill all data !");
    } else {

        $('#hasilcari').append;
        hapusdata();
        hapusInfo();
        hapusInfo2();
        clearroute2();
        clearangkot();
        clearroute();
        hapusangkot();
        hapusMarkerTerdekat();
        $("#filterik").hide();
        $('#hasilik').show();
        $('#hasilcari1').show();
        $('#hasilcari').empty();
        cekRadius();
        console.log(server + 'fungsional3.php?cari_name=' + fungsi3nama + '&cari_fas=' + fungsi3fas + '&cari_angkot=' + fungsi3angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi3radius);
        // clearangkot();
        $.ajax({
            url: server + 'fungsional3.php?cari_name=' + fungsi3nama + '&cari_fas=' + fungsi3fas + '&cari_angkot=' + fungsi3angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi3radius,
            data: "",
            dataType: 'json',
            success: function (rows) {

                if (rows == null) {
                    alert('Data Did Not Exist !');
                }
                for (var i in rows) {
                    $('#hasilcari').append;
                    var row = rows[i];
                    var id = row.id;
                    var name = row.name;
                    //var tipe = row.type;
                    //var produk   = row.id_product;
                    var lat = row.latitude;
                    var lon = row.longitude;
                    //var jarak = row.jarak;
                    centerBaru = new google.maps.LatLng(lat, lon);
                    marker = new google.maps.Marker({
                        position: centerBaru,
                        map: map,
                        icon: "assets/img/tours.png",
                    });
                    // console.log(lat);
                    // console.log(lon);
                    markersDua.push(marker);
                    map.setCenter(centerBaru);
                    klikinfoWindow5(barubana5, id);
                    map.setZoom(14);
                    console.log(name);
                    $('#hasilcari').append("<tr><td>" + name + "</td><td><a role='button' class='btn btn-success' onclick='detailinfowis(\"" + id + "\")'>Show</a></td><td><a role='button' class='btn btn-danger fa fa-car' onclick='angkotSekitarwisata(\"" + lat + "\",\"" + lon + "\",\"" + name + "\",\"" + id + "\")'></a></td><td><a role='button' class='btn btn-danger fa fa-road' onclick='callRoute(centerLokasi,centerBaru);rutetampil();'></a></td></tr>");
                }
            }
        });
    }
}

function aktifkanfungsi4() {
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
        var inputradius = document.getElementById("fungsi4radius").value;
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
}

function fungsionalempat() {
    var fungsi4nama = document.getElementById('fungsi4nama').value;
    var fungsi4angkot = document.getElementById('fungsi4angkot').value;
    var fungsi4jenis = document.getElementById('fungsi4jenis').value;
    var fungsi4radius = document.getElementById('fungsi4radius').value;
    console.log("radius : " + fungsi4radius);
    if (fungsi4nama == "" || fungsi4angkot == "" || fungsi4jenis == "" || fungsi4radius == "") {
        alert("Please fill all data !");
    } else {

        $('#hasilcari').append;
        hapusdata();
        hapusInfo();
        hapusInfo2();
        clearroute2();
        clearangkot();
        clearroute();
        hapusangkot();
        hapusMarkerTerdekat();
        $("#filterik").hide();
        $('#hasilik').show();
        $('#hasilcari1').show();
        $('#hasilcari').empty();
        cekRadius();
        console.log(server + 'fungsional4.php?cari_name=' + fungsi4nama + '&cari_jenis=' + fungsi4jenis + '&cari_angkot=' + fungsi4angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi4radius);
        // clearangkot();
        $.ajax({
            url: server + 'fungsional4.php?cari_name=' + fungsi4nama + '&cari_jenis=' + fungsi4jenis + '&cari_angkot=' + fungsi4angkot + '&lat=' + koordinat.lat + '&long=' + koordinat.lng + '&rad=' + fungsi4radius,
            data: "",
            dataType: 'json',
            success: function (rows) {

                if (rows == null) {
                    alert('Data Did Not Exist !');
                }
                for (var i in rows) {
                    $('#hasilcari').append;
                    var row = rows[i];
                    var id = row.id;
                    var name = row.name;
                    //var tipe = row.type;
                    //var produk   = row.id_product;
                    var lat = row.latitude;
                    var lon = row.longitude;
                    //var jarak = row.jarak;
                    centerBaru = new google.maps.LatLng(lat, lon);
                    marker = new google.maps.Marker({
                        position: centerBaru,
                        map: map,
                        icon: "assets/img/industries.png",
                    });
                    // console.log(lat);
                    // console.log(lon);
                    markersDua.push(marker);
                    map.setCenter(centerBaru);
                    klikinfoWindow(barubana, id);
                    map.setZoom(14);
                    console.log(name);
                    $('#hasilcari').append("<tr><td>" + name + "</td><td><a role='button' class='btn btn-success' onclick='detailinfoumkm(\"" + id + "\")'>Show</a></td><td><a role='button' class='btn btn-danger fa fa-car' onclick='angkotSekitarUMKM(\"" + lat + "\",\"" + lon + "\",\"" + name + "\",\"" + id + "\")'></a><td><a role='button' class='btn btn-danger fa fa-road' onclick='callRoute(centerLokasi,centerBaru);rutetampil();'></a></td></tr>");
                }
            }
        });
    }
}