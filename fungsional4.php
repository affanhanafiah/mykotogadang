<?php
    include('connect.php');
    $latit=$_GET["lat"];
    $longi=$_GET["long"];
    $rad=$_GET["rad"]*100;
    $cari_name = $_GET["cari_name"];
    $cari_jenis = $_GET["cari_jenis"];
    $cari_angkot = $_GET["cari_angkot"];

    $querysearch = "SELECT umkm.id, umkm.name, detail_umkm.id_angkot, st_x(st_centroid(umkm.geom)) as lng, st_y(st_centroid(umkm.geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(umkm.geom)) as jarak 
FROM umkm 
left join umkm_type on umkm.id_type=umkm_type.id_type 
left join detail_umkm on umkm.id=detail_umkm.id_umkm
left join angkot on detail_umkm.id_angkot=angkot.id_angkot
where lower(umkm.name)like lower('%$cari_name%') and umkm.id_type=$cari_jenis and detail_umkm.id_angkot=$cari_angkot and st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(umkm.geom)) <= $rad"; 
    //var_dump($querysearch);
    $hasil=mysqli_query($conn, $querysearch);
    while($row = mysqli_fetch_array($hasil))
        {
              $id=$row['id'];
              $name=$row['name'];
              $id_angkot=$row['id_angkot'];
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