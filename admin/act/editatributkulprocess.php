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

$sql = mysqli_query($conn, "update culinary set name='$name', address='$address', cp='$cp', employee='$employee', price='$price', 
	capacity='$capacity', open='$open', close='$close' where id='$id'");
if ($sql){
	echo "Success Updated!<br>";
	echo "Back to <a href='../?page=culinary'>Dashboard</a>";
}else {
	echo 'error';
}
?>