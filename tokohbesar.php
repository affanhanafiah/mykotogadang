<?php
require 'connect.php';
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(greatcharacter.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT greatcharacter.name,ST_X(ST_Centroid(greatcharacter.geom)) 
// 				AS lon, ST_Y(ST_CENTROID(greatcharacter.geom)) As lat) As l )) As properties 
// 				FROM greatcharacter As greatcharacter  
// 				) As f ) As fc ";

$querysearch="SELECT st_asgeojson(greatcharacter.geom) as geom, greatcharacter.name, ST_X(ST_centroid(greatcharacter.geom)) 
as lon, ST_Y(ST_Centroid(greatcharacter.geom)) as lat from greatcharacter"; 

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