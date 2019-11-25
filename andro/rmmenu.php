
<?php
require 'connect.php';

$cari_nama = $_GET["cari_nama"];
 

$querysearch="SELECT distinct id, name,address, cp,price, st_x(st_centroid(geom)) as longitude, st_y(st_centroid(geom)) As latitude from culinary as a, 
detail_menu WHERE a.id=detail_menu.id_culinary and detail_menu.id_menu='$cari_nama' order by a.name asc
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