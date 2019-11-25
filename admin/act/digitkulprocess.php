<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$cp = $_POST['cp'];
$employee = $_POST['employee'];
$price = $_POST['price'];
$capacity = $_POST['capacity'];
$open = $_POST['open'];
$close = $_POST['close'];

$geom = $_POST['geom'];

$sql = mysqli_query($conn, "insert into culinary (id, name, address, cp, employee, price, capacity, open, close, geom) values ('$id', '$name', '$address', '$cp', '$employee','$price', '$capacity','$open', '$close',  ST_GeomFromText('$geom'))");


if ($sql){
	
	echo "Success Create Data!<br>";
	echo "Back to <a href='../?page=culinary'>Dashboard</a>";
}else{
	echo 'error';
}

?>