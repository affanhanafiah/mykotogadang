<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$information = $_POST['information'];
$geom = $_POST['geom'];

$sql = mysqli_query($conn, "insert into greatcharacter (id, name, address, information, geom) values ('$id', '$name', '$address', '$information', ST_GeomFromText('$geom'))");


if ($sql){	
	echo "Success Create Data!<br>";
	echo "Back to <a href='../?page=tokohbesar'>Dashboard</a>";
}else{
	echo 'error';
}

?>