<?php
require 'connect.php';
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(tourism.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT tourism.name,ST_X(ST_Centroid(tourism.geom)) 
// 				AS lon, ST_Y(ST_CENTROID(tourism.geom)) As lat) As l )) As properties 
// 				FROM tourism As tourism  
// 				) As f ) As fc ";

$querysearch="SELECT st_asgeojson(tourism.geom) as geom, tourism.name, ST_X(ST_centroid(tourism.geom)) 
as lon, ST_Y(ST_Centroid(tourism.geom)) as lat from tourism"; 

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
			//'id' => $data['id'],
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