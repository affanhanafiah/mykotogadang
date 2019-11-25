<?php
include 'connect.php';
$id = $_GET['id'];
$querysearch	="SELECT culinary.id, culinary.name, culinary.address, ST_X(ST_Centroid(culinary.geom)) AS lng, ST_Y(ST_CENTROID(culinary.geom)) 
            As lat FROM culinary";
			   
$hasil=mysqli_query($conn, $querysearch);
while($baris = mysqli_fetch_array($hasil))
	{
		$id=$baris['id'];
        $name=$baris['name'];
        $address=$baris['address'];
        $longitude=$baris['lng'];
		$latitude=$baris['lat'];
        $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'lng'=>$longitude,'lat'=>$latitude);
    }
echo json_encode ($dataarray);
?>

