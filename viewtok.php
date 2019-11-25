<?php
include 'connect.php';
$id = $_GET['id'];
$querysearch	="SELECT greatcharacter.id, greatcharacter.name, greatcharacter.address, ST_X(ST_Centroid(greatcharacter.geom)) AS lng, ST_Y(ST_CENTROID(greatcharacter.geom)) 
            As lat FROM greatcharacter";
			   
$hasil=mysqli_query($conn, $querysearch);
while($baris = mysqli_fetch_array($hasil))
	{
		$id=$baris['id'];
        $name=$baris['name'];
        $address=$baris['address'];
        $longitude=$baris['lng'];
		$latitude=$baris['lat'];
        $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address, 'lng'=>$longitude,'lat'=>$latitude);
    }
echo json_encode ($dataarray);
?>

