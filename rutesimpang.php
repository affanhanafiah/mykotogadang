<?php
    include("connect.php");
    $id_ik = $_GET['id_umkm'];
    $id_angkot = $_GET['id_angkot'];
    $result=  mysqli_query($conn, "SELECT * FROM detail_umkm WHERE id_umkm='$id_ik' AND id_angkot='$id_angkot'");


    
    while($baris = mysqli_fetch_array($result))
    {
        $id_umkm=$baris['id_umkm'];
        $id_angkot=$baris['id_angkot'];
        $lat=$baris['lat'];
        $lng=$baris['long'];
        $ket=$baris['information'];
        $dataarray[]=array('id_umkm'=>$id_umkm,'id_angkot'=>$id_angkot,'lng'=>$lng,'lat'=>$lat,'ket'=>$ket);
    }
echo json_encode ($dataarray);
?>
