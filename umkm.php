<?php
require 'connect.php';
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(umkm.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT umkm.name,ST_X(ST_Centroid(umkm.geom)) 
// 				AS lon, ST_Y(ST_CENTROID(umkm.geom)) As lat) As l )) As properties 
// 				FROM umkm As umkm  
// 				) As f ) As fc ";

$querysearch="SELECT st_asgeojson(umkm.geom) as geom,id, umkm.name, ST_X(ST_centroid(umkm.geom)) 
as lon, ST_Y(ST_Centroid(umkm.geom)) as lat from umkm"; 

$hasil=mysqli_query($conn, $querysearch);

$result = array(
	'type'=> 'FeatureCollection',
	'features' => array()
);
while($data=mysqli_fetch_assoc($hasil)){
	$features = array(
		'type' => 'Feature',
		'geometry' => json_decode($data['geom']),

		'properties' => array(
			'id' => $data['id'],
			'name' => $data['name'],

			'lat' =>$data['lat'],
			'lon' =>$data['lon']
		)
	);
		array_push($result['features'], $features);
}
echo json_encode($result);

// while($data=pg_fetch_array($hasil))
// 	{
// 		$load=$data['row_to_json'];
// 	}
// 	echo $load;
?>