<?php
include 'connect.php';
$id = $_GET['id'];
$querysearch	="SELECT mosque.id, mosque.name, mosque.address, mosque.capacity, ST_X(ST_Centroid(mosque.geom)) AS lng, ST_Y(ST_CENTROID(mosque.geom)) 
            As lat FROM mosque";
			   
$hasil=mysqli_query($conn, $querysearch);
while($baris = mysqli_fetch_array($hasil))
	{
		$id=$baris['id'];
        $name=$baris['name'];
        $address=$baris['address'];
        $capacity=$baris['capacity'];
        $longitude=$baris['lng'];
		$latitude=$baris['lat'];
        $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'capacity'=>$capacity, 'lng'=>$longitude,'lat'=>$latitude);
    }
echo json_encode ($dataarray);
?>

