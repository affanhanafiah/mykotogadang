<?php
include('connect.php');
$latit=$_GET["lat"];
$longi=$_GET["long"];
$rad=$_GET["rad"];


// $querysearch="SELECT id, name, 
// 	st_x(st_centroid(geom)) as lng,st_y(st_centroid(geom)) as lat,
// 	st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")',-1), umkm.geom) as jarak 
// 	FROM umkm where st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")',-1),
// 	 umkm.geom) <= ".$rad."	

// SELECT id, name, st_x(st_centroid(geom)) as lng, 
// st_y(st_centroid(geom)) as lat, 
// st_distance_sphere(ST_GeomFromText('POINT(-0.31718152315815884 100.35843401503189)'), 
// 				   ST_Centroid(umkm.geom)) as jarak 
// 				   FROM umkm 
// 				   where st_distance_sphere(ST_GeomFromText
// 											('POINT(-0.31718152315815884 100.35843401503189)'), 
// 											ST_Centroid(umkm.geom)) <= 10000000000000000

// 			 "; 

	$querysearch="SELECT id, name, st_x(st_centroid(geom)) as lng, st_y(st_centroid(geom)) as lat, 
	st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(umkm.geom)) as jarak 
	FROM umkm where st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), 
	ST_Centroid(umkm.geom)) <= ".$rad.""; 
    //var_dump($querysearch);
    //die();

    // $querysearch = "SELECT
	// 		 id, (
	// 		   6371 * acos (
	// 			 cos ( radians('$latit') )
	// 			 * cos( radians( ST_Y(ST_CENTROID(geom)) ) )
	// 			 * cos( radians( ST_X(ST_CENTROID(geom)) ) - radians('$longi') )
	// 			 + sin ( radians('$latit') )
	// 			 * sin( radians( ST_Y(ST_CENTROID(geom)) ) )
	// 		   )
	// 		 ) AS jarak, name, ST_Y(ST_CENTROID(geom)) as lat, ST_X(ST_CENTROID(geom)) as lng
	// 	   FROM umkm
	// 	   HAVING jarak <= $rad";

	$hasil = mysqli_query($conn, $querysearch);
    //var_dump($hasil);
    //die();
        while($baris = mysqli_fetch_array($hasil))
            {
                $id=$baris['id'];
                $name=$baris['name'];
                $jarak=$baris['jarak']; 
                $lat=$baris['lat'];
                $lng=$baris['lng'];
                $dataarray[]=array('id'=>$id,'name'=>$name, 'jarak'=>$jarak,'lng'=>$lng,'lat'=>$lat);
            }
            echo json_encode ($dataarray);

// $hasil=mysqli_query($conn, $querysearch);
// while($row = mysqli_fetch_array($hasil))
// 	{
// 		  $id=$row['id'];
// 		  $name=$row['name'];
		  
// 		  $longitude=$row['lng'];
// 		  $latitude=$row['lat'];
// 		  $jarak=$row['jarak'];
// 		  $dataarray[]=array('id'=>$id,'name'=>$name,
// 		  'longitude'=>$longitude,'latitude'=>$latitude, 'jarak'=>$jarak);
// 	}
// echo json_encode ($dataarray);
?>