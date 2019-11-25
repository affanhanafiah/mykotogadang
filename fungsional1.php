<?php
    include('connect.php');
    $latit=$_GET["lat"];
    $longi=$_GET["long"];
    $rad=$_GET["rad"]*100;
    $cari_name = $_GET["cari_name"];
    $cari_jenis = $_GET["cari_jenis"];
    $cari_produk = $_GET["cari_product"];

    $querysearch	="SELECT umkm.id, umkm.name, umkm_type.id_type as type, product.id_product as produk, st_x(st_centroid(umkm.geom)) as lng, st_y(st_centroid(umkm.geom)) as lat, st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(umkm.geom)) as jarak 
    FROM umkm 
    left join detail_product on detail_product.id_umkm=umkm.id
    left join product on detail_product.id_product=product.id_product
    left join umkm_type on umkm.id_type=umkm_type.id_type
    where lower(umkm.name)like lower('%$cari_name%') and umkm.id_type=$cari_jenis and detail_product.id_product=$cari_produk and st_distance_sphere(ST_GeomFromText('POINT(".$longi." ".$latit.")'), ST_Centroid(umkm.geom)) <= $rad"; 
    $hasil=mysqli_query($conn, $querysearch);
    while($row = mysqli_fetch_array($hasil))
        {
              $id=$row['id'];
              $name=$row['name'];
              $id_type=$row['type'];
              $id_produk=$row['produk'];
              $longitude=$row['lng'];
              $latitude=$row['lat'];
              $jarak=$row['jarak'];
              $dataarray[]=
                array('id'=>  $id,
                    'name'=>$name, 
                    'type'=>$id_type,
                    'produk'=>$id_produk,
                    'longitude'=>$longitude,
                    'latitude'=>$latitude,
                    'jarak'=>$jarak
                );
        }
    echo json_encode ($dataarray);
?>