<?php
    include('connect.php');
    $latit=$_GET["lat"];
    $longi=$_GET["long"];
    $rad=$_GET["rad"]*100;
    $cari_name = $_GET["cari_name"];
    $cari_fas = $_GET["cari_fas"];
    $cari_angkot = $_GET["cari_angkot"];

    $querysearch = "SELECT distinct tourism.id, tourism.name, detail_tourism.id_angkot as angkot, st_x(st_centroid(tourism.geom)) as lng, st_y(st_centroid(tourism.geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(tourism.geom)) as jarak 
    FROM tourism 
    left join detail_facility on detail_facility.id_tourism=tourism.id
    left join detail_tourism on detail_tourism.id_tourism=tourism.id 
    left join angkot on detail_tourism.id_angkot=angkot.id_angkot
    where lower(tourism.name)like lower('%$cari_name%') and detail_facility.id_facility=$cari_fas and detail_tourism.id_angkot=$cari_angkot and st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(tourism.geom)) <= $rad"; 
    //var_dump($querysearch);
    $hasil=mysqli_query($conn, $querysearch);
    while($row = mysqli_fetch_array($hasil))
        {
              $id=$row['id'];
              $name=$row['name'];
              $id_angkot=$row['angkot'];
              $longitude=$row['lng'];
              $latitude=$row['lat'];
              $jarak=$row['jarak'];
              $dataarray[]=
                array('id'=>  $id,
                    'name'=>$name, 
                    'angkot'=>$id_angkot,
                    'longitude'=>$longitude,
                    'latitude'=>$latitude,
                    'jarak'=>$jarak
                );
        }
    echo json_encode ($dataarray);
?>