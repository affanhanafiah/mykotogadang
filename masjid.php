<?php
require 'connect.php';
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(mosque.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT mosque.name,ST_X(ST_Centroid(mosque.geom)) 
// 				AS lon, ST_Y(ST_CENTROID(mosque.geom)) As lat) As l )) As properties 
// 				FROM mosque As mosque  
// 				) As f ) As fc ";

$querysearch="SELECT st_asgeojson(mosque.geom) as geom, mosque.name, ST_X(ST_centroid(mosque.geom)) 
as lon, ST_Y(ST_Centroid(mosque.geom)) as lat from mosque"; 
$hasil=mysqli_query($conn, $querysearch);

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
			// 'id' => $data['id'],
			'name' => $data['name'],

			'lat' =>$data['lat'],
			'lon' =>$data['lon']
		)
	);
		array_push($result['features'], $features);
}
echo json_encode($result);
// while($data=mysqli_fetch_array($hasil))
// 	{
// 		$load=$data['row_to_json'];
// 	}
// 	echo $load;
?>