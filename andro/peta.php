<!DOCTYPE html>
<html>
<head>
<meta name='viewport' content='initial-scale=1.0, user-scalable=no' /><style type='text/css'> 
html { height: 100%;width: 100% } 
body { height: 100%; width: 100%; margin: 0px; padding: 0px }
#map { height: 100%; width: 100% }
</style>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBh7Xfdh42Ro9CNFPkvoZhFVhEpTeOP16g"></script>

</script>
<script src='http://code.jquery.com/jquery-1.11.0.min.js' type='text/javascript'>
</script> 
<? 
$lat = $_GET['lat']; $lng = $_GET['lng']; //ruangan
$lat1 = $_GET['lat1']; $lng1 = $_GET['lng1']; //sekat 
$warna=$_GET['warna'];$gidruang=$_GET['idruangan']; 
$gidsekat=$_GET['gid']; 
$idruangan=$_GET['idruangan']; 
$latTujuan=$_GET['latTujuan'];
$lngTujuan=$_GET['lngTujuan'];
$bool=false;


/*if(isset($_GET['latsimpang'])){
  $latsimpang=$_GET['latsimpang'];
  $lngsimpang=$_GET['lngsimpang'];
  $bool=true;
}else{
  $latsimpang='0';
  $lngsimpang='0';
}
*/

echo"
<script type='text/javascript'> 

 function init(){

    var latlng = new google.maps.LatLng($lat, $lng); 
    var myOptions = { 
      zoom:24, center: latlng, mapTypeId: 'satellite' }; 
      var map = new google.maps.Map(document.getElementById('map'), myOptions);    

      asiska = new google.maps.Data();
      asiska.loadGeoJson('carisekat.php?gid=$gidsekat');
      asiska.setStyle(function(feature){
      return({
          fillColor: 'teal',
          strokeColor: 'teal',
          strokeWeight: 3,
          fillOpacity: 0.5
          });          
      });
      asiska.setMap(map);  


      asiska1 = new google.maps.Data();
      asiska1.loadGeoJson('cariruang.php?gid=$gidruang');
      asiska1.setStyle(function(feature){
      return({
          fillColor: 'violet',
          strokeColor: 'violet',
          strokeWeight: 3,
          fillOpacity: 0.5
          });          
      });
      asiska1.setMap(map); 
}
</script>"; 

?> 
</head>
<body onload='init()'> 
<div id='map'></div>
</body>
</html>

