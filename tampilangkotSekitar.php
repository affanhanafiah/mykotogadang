
<?php
include('connect.php');
$lat=$_GET["lat"];
$lon=$_GET["lon"];

$querysearch="SELECT id_angkot, destination, track, cost, st_x(st_centroid(geom)) as longitude,st_y(st_centroid(geom)) as latitude,
			st_distance_sphere(ST_GeomFromText('POINT(".$lon." ".$lat.")',-1), angkot.geom) as jarak FROM angkot where st_distance_sphere(ST_GeomFromText
			('POINT(".$lon." ".$lat.")',-1), angkot.geom) <= 350 order by jarak"; 
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

