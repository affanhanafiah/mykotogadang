<?php
    include("connect.php");
    $id_mosque = $_GET['id_tokoh'];
    $id_angkot = $_GET['id_angkot'];
    $result=  mysqli_query($conn, "SELECT * FROM detail_greatcharacter WHERE id_greatcharacter='$id_mosque' AND id_angkot='$id_angkot'");


    
    while($baris = mysqli_fetch_array($result))
    {
        $id_greatcharacter=$baris['id_greatcharacter'];
        $id_angkot=$baris['id_angkot'];
        $lat=$baris['lat'];
        $lng=$baris['long'];
        $ket=$baris['information'];
        $dataarray[]=array('id_mosque'=>$id_mosque,'id_angkot'=>$id_angkot,'lng'=>$lng,'lat'=>$lat,'ket'=>$ket);
    }
echo json_encode ($dataarray);
?>
