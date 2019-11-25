<?php
require 'connect.php';
$cari_menu = $_GET["cari_menu"];
$querysearch	="SELECT distinct id,name,address, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from culinary,detail_menu where detail_menu.id_culinary=culinary.id and detail_menu.id_menu='$cari_menu'";
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $name=$row['name'];
		  $address=$row['address'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>