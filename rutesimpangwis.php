<?php
    include("connect.php");
    $id_wis_ = $_GET['id_wis'];
    $id_angkot = $_GET['id_angkot'];
    $result=  mysqli_query($conn, "SELECT * FROM detail_tourism WHERE id_tourism='1' AND id_angkot='06'");


    
    while($baris = mysqli_fetch_array($result))
    {
        $id_tourism=$baris['id_tourism'];
        $id_angkot=$baris['id_angkot'];
        $lat=$baris['lat'];
        $lng=$baris['long'];
        $ket=$baris['information'];
        $dataarray[]=array('id_tourism'=>$id_tourism,'id_angkot'=>$id_angkot,'lng'=>$lng,'lat'=>$lat,'ket'=>$ket);
    }
echo json_encode ($dataarray);
?>
