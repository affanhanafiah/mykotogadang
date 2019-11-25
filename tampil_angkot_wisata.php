<?php
include('connect.php');
$id_angkot=$_GET["id_angkot"];

$querysearch="SELECT angkot.id_angkot, angkot.destination, angkot.track, angkot.cost,st_x(st_centroid(angkot.geom)) as longitude,st_y(st_centroid(angkot.geom)) as latitude from angkot, detail_tourism where detail_tourism.id_tourism = '$id_angkot' AND angkot.id_angkot = detail_tourism.id_angkot"; 


$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id_angkot=$row['id_angkot'];
		  $destination=$row['destination'];
		  $track=$row['track'];
		  $cost=$row['cost'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id_angkot'=>$id_angkot,'destination'=>$destination,'track'=>$track, 'cost'=>$cost,
		  'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>

