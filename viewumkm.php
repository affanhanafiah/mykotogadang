<?php
include 'connect.php';
$id = $_GET['id'];
$querysearch	="SELECT umkm.id, umkm.name, umkm.address, umkm.cp, ST_X(ST_Centroid(umkm.geom)) AS lng, ST_Y(ST_CENTROID(umkm.geom)) 
            As lat FROM umkm";
			   
$hasil=mysqli_query($conn, $querysearch);
while($baris = mysqli_fetch_array($hasil))
	{
		$id=$baris['id'];
        $name=$baris['name'];
        $address=$baris['address'];
        $cp=$baris['cp'];
        $longitude=$baris['lng'];
		$latitude=$baris['lat'];
        $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'cp'=>$cp,'lng'=>$longitude,'lat'=>$latitude);
    }
echo json_encode ($dataarray);
?>

