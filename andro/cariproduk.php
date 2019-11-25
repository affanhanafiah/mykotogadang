
<?php
require 'connect.php';

$cari_nama = $_GET["cari_nama"];
 

$querysearch="SELECT distinct a.id,a.name,ST_X(ST_Centroid(a.geom)) AS longitude, ST_Y(ST_CENTROID(a.geom)) As latitude
          FROM umkm as a,detail_product where detail_product.id_product='$cari_nama' and detail_product.id_umkm=a.id
				";
			   
$hasil=pg_query($querysearch);
while($row = pg_fetch_array($hasil))
    {
          $id=$row['id'];
          $name=$row['name'];
          $longitude=$row['longitude'];
          $latitude=$row['latitude'];
          $dataarray[]=array('id'=>$id,'name'=>$name,'longitude'=>$longitude,'latitude'=>$latitude);
    }
echo json_encode ($dataarray);
?>