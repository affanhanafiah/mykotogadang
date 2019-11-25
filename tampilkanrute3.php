<?php
require 'connect.php';
$id_angkot=$_GET["id_angkot"];
$id_angkot2=$_GET["id_angkot2"];
// $querysearch="	SELECT row_to_json(fc) 
// 				FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features 
// 				FROM (SELECT 'Feature' As type , ST_AsGeoJSON(a.geom)::json As geometry , row_to_json((SELECT l 
// 				FROM (SELECT a.id_angkot, a.destination, a.track,a.cost,  ST_X(ST_Centroid(a.geom)) AS longitude, 
//				ST_Y(ST_CENTROID(a.geom)) As latitude) As l )) As properties 
// 				FROM angkot As a
// 				where a.id_angkot='Family'
// 				) As f ) As fc
// 			  ";

$querysearch="SELECT st_asgeojson(angkot.geom) as geom, angkot.destination, angkot.track, angkot.cost, ST_X(ST_centroid(angkot.geom)) 
as longitude, ST_Y(ST_Centroid(angkot.geom)) as latitide from angkot where angkot.id_angkot='Family'" ; 

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
			// 'name' => $data['name'],
			'destination'=>$data['destination'],
			'track'=>$data['track'],
			'cost'=>$data['cost'],
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

