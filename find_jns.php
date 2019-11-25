<?php
require 'connect.php';
$cari_jenis = $_GET["cari_jenis"];
$querysearch	="SELECT distinct id, name, id_type, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from umkm where id_type='$cari_jenis'"; 
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $name=$row['name'];
		  $id_type=$row['id_type'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'name'=>$name,'id_type'=>$id_type,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>