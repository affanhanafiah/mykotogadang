<?php
require 'connect.php';
$carifasil = $_GET["carifasil"];

$querysearch	="SELECT distinct id, name, detail_facility.id_facility, st_x(st_centroid(geom)) as longitude, 
st_y(st_centroid(geom)) as latitude from tourism left join detail_facility on tourism.id=detail_facility.id_tourism
where id_facility = $carifasil"; 

$hasil=mysqli_query($conn, $querysearch);
while($row = mysqli_fetch_array($hasil))
	{
		  $id=$row['id'];
		  $name=$row['name'];
		  $id_facility=$row['id_facility'];
		  $longitude=$row['longitude'];
		  $latitude=$row['latitude'];
		  $dataarray[]=array('id'=>$id,'name'=>$name,'id_facility'=>$id_facility,'longitude'=>$longitude,'latitude'=>$latitude);
	}
echo json_encode ($dataarray);
?>