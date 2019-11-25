<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$capacity = $_POST['capacity'];
$geom = $_POST['geom'];

$sql = mysqli_query($conn, "insert into mosque (id, name, address, capacity, geom) values ('$id', '$name', '$address', '$capacity', ST_GeomFromText('$geom'))");


if ($sql){	
	echo "Success Create Data!<br>";
	echo "Back to <a href='../?page=masjid'>Dashboard</a>";
}else{
	echo 'error';
}

?>