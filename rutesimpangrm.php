<?php
    include("connect.php");
    $id_culinary = $_GET['id_culinary'];
    $id_angkot = $_GET['id_angkot'];
    $result=  mysqli_query($conn, "SELECT * FROM detail_culinary WHERE id_culinary='$id_culinary' AND id_angkot='$id_angkot'");


    
    while($baris = mysqli_fetch_array($result))
    {
        $id_culinary=$baris['id_culinary'];
        $id_angkot=$baris['id_angkot'];
        $lat=$baris['lat'];
        $lng=$baris['long'];
        $ket=$baris['information'];
        $dataarray[]=array('id_culinary'=>$id_culinary,'id_angkot'=>$id_angkot,'lng'=>$lng,'lat'=>$lat,'ket'=>$ket);
    }
echo json_encode ($dataarray);
?>
