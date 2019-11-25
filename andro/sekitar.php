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
$rad = 500;    // Isi yang dicari
$lat = $_GET["lat"];    // Isi yang dicari
$lng = $_GET["lng"];    // Isi yang dicari
$kd1=$_GET["kd1"];
$kd2=$_GET["kd2"];
$kd3=$_GET["kd3"];
$kd4=$_GET["kd4"];
$kd5=$_GET["kd5"];
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
    kecamatan.setStyle(function(feature)
    {
      var gid = feature.getProperty('id');
      if (gid == 1){ color = '#ff3300' 
        return({
      fillColor:color,
          strokeWeight:2.0,
          strokeColor:'black',
          fillOpacity:0,
          clickable: false
        }); 
    }
      else if(gid == 2){ color = '#ffd777' 
        return({
        fillColor:color,
          strokeWeight:2.0,
          strokeColor:'black',
          fillOpacity:0,
          clickable: false
        });
    }
      else if(gid == 3){ color = '#00b300' 
        return({
        fillColor:color,
          strokeWeight:2.0,
          strokeColor:'black',
          fillOpacity:0,
          clickable: false
        });

    }
       
    });
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

    var lat = "<?php echo $lat; ?>";
	var lng = "<?php echo $lng; ?>";
	var rad = "<?php echo $rad; ?>";
	var umkm="<?php echo $kd1; ?>" ;
	var kul="<?php echo $kd2; ?>";
	var mes="<?php echo $kd3; ?>";
	var tok="<?php echo $kd5; ?>";
	var wis="<?php echo $kd4; ?>";
	var url;
	var url1;
	var url2;
	var url3;
	var url4;


	if (umkm==1){
		url = 'http://192.168.1.2/kotogadang/andro/sekitar_rad.php?lat='+lat+'&lng='+lng+'&rad='+rad;	
	}	
	else{
		url=0;
	}

	if (kul==1){
		url1 = 'http://192.168.1.2/kotogadang/andro/sekitar_rad_umkm.php?lat='+lat+'&lng='+lng+'&rad='+rad;
	}	
	else{
		url1=0;
	}

	if (mes==1){
		url2 = 'http://192.168.1.2/kotogadang/andro/sekitar_rad_mes.php?lat='+lat+'&lng='+lng+'&rad='+rad;
	}	
	else{
		url2=="";
	}

	if (tok==1){
		url3 = 'http://192.168.1.2/kotogadang/andro/sekitar_rad_wis.php?lat='+lat+'&lng='+lng+'&rad='+rad;
	}	
	else{
		url3=="";
	}

	if (wis==1){
		 url4 = 'http://192.168.1.2/kotogadang/andro/sekitar_rad_tok.php?lat='+lat+'&lng='+lng+'&rad='+rad;
	}	
	else{
		url4=="";
	}
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
              icon:'../assets/ico/industries.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);
              map.setZoom(16);
          }//end for               
      }});//end ajax 
      $.ajax({url: url1, data: "", dataType: 'json', success: function(rows){ 
          for (var i in rows){ 
            var row = rows[i];
            var id = row.id;
            var latitude = row.latitude;
            var longitude = row.longitude;
      
      centerBaru = new google.maps.LatLng(latitude, longitude);
              marker = new google.maps.Marker
            ({
              position: centerBaru,
              icon:'../assets/ico/restaurants.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);

          }//end for               
      }});//end ajax 
       $.ajax({url: url2, data: "", dataType: 'json', success: function(rows){ 
          for (var i in rows){ 
            var row = rows[i];
            var id = row.id;
            var latitude = row.latitude;
            var longitude = row.longitude;
      
      centerBaru = new google.maps.LatLng(latitude, longitude);
              marker = new google.maps.Marker
            ({
              position: centerBaru,
              icon:'../assets/ico/religious.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);

          }//end for               
      }});//end ajax 
        $.ajax({url: url3, data: "", dataType: 'json', success: function(rows){ 
          for (var i in rows){ 
            var row = rows[i];
            var id = row.id;
            var latitude = row.latitude;
            var longitude = row.longitude;
      
      centerBaru = new google.maps.LatLng(latitude, longitude);
              marker = new google.maps.Marker
            ({
              position: centerBaru,
              icon:'../assets/ico/travel.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);

          }//end for               
      }});//end ajax 
        $.ajax({url: url4, data: "", dataType: 'json', success: function(rows){ 
          for (var i in rows){ 
            var row = rows[i];
            var id = row.id;
            var latitude = row.latitude;
            var longitude = row.longitude;
      
      centerBaru = new google.maps.LatLng(latitude, longitude);
              marker = new google.maps.Marker
            ({
              position: centerBaru,
              icon:'../assets/ico/meetups.png',
              map: map,
              animation: google.maps.Animation.DROP,
            });
               markersDua.push(marker);
              map.setCenter(centerBaru);
         
          }//end for               
      }});//end ajax 

  }

  
</script>
</head>
<body onload='init()'> 
<div id='map'></div>
</body>
</html>

