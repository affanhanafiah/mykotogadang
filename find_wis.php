<?php
require 'connect.php';
$cari_name = $_GET["cari_name"];
$querysearch	="SELECT distinct id, name, address, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from tourism where lower(name)like lower('%$cari_name%')"; 
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $name=$row['name'];
		  $address=$row['address'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
	}
		  $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'longitude'=>$longitude,'latitude'=>$latitude);
echo json_encode ($dataarray);
?>