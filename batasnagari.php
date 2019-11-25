<?php
require 'connect.php';
/*$querysearch="	SELECT row_to_json(fc) 
				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(nagari.geom)::json As geometry , row_to_json((SELECT l 
				FROM (SELECT nagari.name,ST_X(ST_Centroid(nagari.geom)) AS lon,
				 ST_Y(ST_CENTROID(nagari.geom)) As lat) As l )) As properties 
				FROM nagari As nagari  
				) As f ) As fc ";
*/
$querysearch="SELECT ST_AsGeoJSON(nagari.geom) as geom, nagari.name, ST_X(ST_CENTROID(nagari.geom)) 
as lon, ST_Y(ST_CENTROID(nagari.geom)) as lat from nagari"; 
$hasil=mysqli_query($conn, $querysearch);
//var_dump($hasil);
//die();
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
/*while($data=mysqli_fetch_array($hasil))
	{
		$load=$data['row_to_json'];
	}
	echo $load;
*/
	?>