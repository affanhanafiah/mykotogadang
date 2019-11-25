<?php
require 'connect.php';
$cari_jenis = $_GET["cari_jenis"];
$querysearch ="SELECT distinct id, nama, id_jenis_mesjid, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from mesjid where id_jenis_mesjid='$cari_jenis'";
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $nama=$row['nama'];
		  $id_jenis_mesjid=$row['id_jenis_mesjid'];
		  
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'nama'=>$nama,'id_jenis_mesjid'=>$id_jenis_mesjid,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>