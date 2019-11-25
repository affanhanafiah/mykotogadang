<?php
require 'connect.php';
$cari_produk = $_GET["cari_product"];
$querysearch	="select distinct umkm.id, umkm.name, detail_product.id_product, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from umkm,detail_product where detail_product.id_umkm=umkm.id and id_product='$cari_produk'"; 
$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $name=$row['name'];
		  $id_product=$row['id_product'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'name'=>$name,'id_product'=>$id_product,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>