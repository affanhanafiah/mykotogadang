<?php
include('connect.php');
$id_angkot=$_GET["id_angkot"];

$querysearch="SELECT id_mosque,angkot.id_angkot, angkot.destination, angkot.track, angkot.cost, st_x(st_centroid(angkot.geom)) as longitude,st_y(st_centroid(angkot.geom)) 
as latitude from angkot, detail_mosque where detail_mosque.id_mosque = '$id_angkot' AND angkot.id_angkot = detail_mosque.id_angkot"; 


$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id_angkot=$row['id_angkot'];
		  $destination=$row['destination'];
		  $cost=$row['cost'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $track=$row['track'];
		  $id_mosque=$row['id_mosque'];
		  $dataarray[]=array('id_angkot'=>$id_angkot,'destination'=>$destination,'track'=>$track, 'cost'=>$cost,
		  'longitude'=>$longitude,'latitude'=>$latitude,'id_mosque'=>$id_mosque);
	}
echo json_encode ($dataarray);
?>

