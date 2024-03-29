<!DOCTYPE html>
<html>
<head>
<meta name='viewport' content='initial-scale=1.0, user-scalable=no' /><style type='text/css'> 
html { height: 100%;width: 100% } 
body { height: 100%; width: 100%; margin: 0px; padding: 0px }
#map { height: 100%; width: 100% }
</style>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBNnzxae2AewMUN0Tt_fC3gN38goeLVdVE&sensor=true">
</script>
<script src='http://code.jquery.com/jquery-1.11.0.min.js' type='text/javascript'>
</script> 
<? 
$lat = -0.305441; 
$lng = 100.3692; 
$cari_nama = $_GET["cari_nama"];    // Isi yang dicari
$nilai = $_GET["nilai"];    // Isi yang dicari

?> 
<script type='text/javascript'> 
var map;
var markersDua = [];
var centerBaru;

  function init(){
    console.log("init jalan");

    var latlng = new google.maps.LatLng(<?php echo $lat; ?>, <?php echo $lng; ?>); 
    var myOptions = { 
      zoom:14, center: latlng, mapTypeId: google.maps.MapTypeId.ROADMAP }; 
      map = new google.maps.Map(document.getElementById('map'), myOptions);     
	
	kecamatan = new google.maps.Data();
    kecamatan.loadGeoJson('batasnagari.php');
    kecamatan.setMap(map);

      mesjid = new google.maps.Data();    
    mesjid.loadGeoJson('rm.php');

    mesjid.setStyle(function(feature){
        return({
            fillColor: '#68dff0',
                    strokeColor: '#68dff0',
                    strokeWeight: 1,
                    fillOpacity: 0.6
          });
      });
      mesjid.setMap(map);
	  
    var cari_nama = "<?php echo $cari_nama; ?>";
    var url = 'http://192.168.1.2/kotogadang/andro/rmnama.php?cari_nama='+cari_nama;
    console.log(url);
      $.ajax({url: url, data: "", dataType: 'json', success: function(rows){ 
          for (var i in rows){ 
            var row = rows[i];
            var id = row.id;
            var latitude = row.latitude;
            var longitude = row.longitude;
			
			centerBaru = new google.maps.LatLng(latitude, longitude);
              marker = new google.maps.Marker
            ({
              position: centerBaru,
              icon:'../assets/img/restaurants.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);
              map.setZoom(14);
          }//end for               
      }});//end ajax 
     
  }

  
</script>
</head>
<body onload='init()'> 
<div id='map'></div>
</body>
</html>

