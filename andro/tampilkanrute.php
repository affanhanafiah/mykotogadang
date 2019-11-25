<?php
require 'connect.php';
$id_angkot=$_GET['id_angkot'];
$querysearch="	SELECT row_to_json(fc) 
				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(a.geom)::json As geometry , row_to_json((SELECT l 
				FROM (SELECT a.id_angkot, a.destination, a.cost, a.track) As l )) As properties 
				FROM angkot As a
				where a.id_angkot='$id_angkot'
				) As f ) As fc 
			  ";

$hasil=pg_query($querysearch);
while($data=pg_fetch_array($hasil))
	{
		$load=$data['row_to_json'];
	}
	echo $load;
?>

