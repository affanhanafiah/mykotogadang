<?php
    include('connect.php');
    $latit=$_GET["lat"];
    $longi=$_GET["long"];
    $rad=$_GET["rad"]*100;
    $cari_name = $_GET["cari_name"];
    $cari_menu = $_GET["cari_menu"];
    $cari_angkot = $_GET["cari_angkot"];

    $querysearch	="SELECT culinary.id, culinary.name, detail_menu.id_menu as menu, detail_culinary.id_angkot as angkot, st_x(st_centroid(culinary.geom)) as lng, st_y(st_centroid(culinary.geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(culinary.geom)) as jarak 
    FROM culinary 
    left join detail_menu on detail_menu.id_culinary=culinary.id 
    left join detail_culinary on detail_culinary.id_culinary=culinary.id 
    left join angkot on detail_culinary.id_angkot=angkot.id_angkot 
    where lower(culinary.name)like lower('%$cari_name%') and detail_menu.id_menu=$cari_menu and detail_culinary.id_angkot='$cari_angkot' and st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(culinary.geom)) <= $rad"; 
    //var_dump($querysearch);
    $hasil=mysqli_query($conn, $querysearch);
    while($row = mysqli_fetch_array($hasil))
        {
              $id=$row['id'];
              $name=$row['name'];
              $id_menu=$row['menu'];
              $id_angkot=$row['angkot'];
              $longitude=$row['lng'];
              $latitude=$row['lat'];
              $jarak=$row['jarak'];
              $dataarray[]=
                array('id'=>  $id,
                    'name'=>$name, 
                    'menu'=>$id_menu,
                    'angkot'=>$id_angkot,
                    'longitude'=>$longitude,
                    'latitude'=>$latitude,
                    'jarak'=>$jarak
                );
        }
    echo json_encode ($dataarray);
?>