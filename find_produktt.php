<?php
require 'connect.php';
$cari_produktt = $_GET["cari_produktt"];
$querysearch	="SELECT distinct id, nama,produk ,st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from umkm where lower(produk)like lower('%$cari_produktt%')"; 
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $nama=$row['nama'];
		  $produk=$row['produk'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'nama'=>$nama,'produk'=>$produk,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>