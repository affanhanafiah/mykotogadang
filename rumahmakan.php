<?php
require 'connect.php';
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(culinary.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT culinary.name,ST_X(ST_Centroid(culinary.geom)) 
// 				AS lon, ST_Y(ST_CENTROID(culinary.geom)) As lat) As l )) As properties 
// 				FROM culinary As culinary  
// 				) As f ) As fc ";

$querysearch="SELECT st_asgeojson(culinary.geom) as geom, culinary.name, ST_X(ST_centroid(culinary.geom)) 
as lon, ST_Y(ST_Centroid(culinary.geom)) as lat from culinary"; 

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
// while($data=mysqli_fetch_array($hasil))
// 	{
// 		$load=$data['row_to_json'];
// 	}
// 	echo $load;
?>