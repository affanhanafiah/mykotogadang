<?php
include('connect.php');
$id_angkot=$_GET["id_angkot"];

$querysearch="SELECT id_greatcharacter,angkot.id_angkot, angkot.destination, angkot.track, angkot.cost, st_x(st_centroid(angkot.geom)) as longitude,st_y(st_centroid(angkot.geom)) 
as latitude from angkot, detail_greatcharacter where detail_greatcharacter.id_greatcharacter = '$id_angkot' AND angkot.id_angkot = detail_greatcharacter.id_angkot"; 


$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id_angkot=$row['id_angkot'];
		  $destination=$row['destination'];
		  $cost=$row['cost'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $track=$row['track'];
		  $id_greatcharacter=$row['id_greatcharacter'];
		  $dataarray[]=array('id_angkot'=>$id_angkot,'destination'=>$destination,'track'=>$track, 'cost'=>$cost,
		  'longitude'=>$longitude,'latitude'=>$latitude,'id_greatcharacter'=>$id_greatcharacter);
	}
echo json_encode ($dataarray);
?>

