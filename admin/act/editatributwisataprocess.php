<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$open = $_POST['open'];
$close = $_POST['close'];
$ticket = $_POST['ticket'];
$information = $_POST['information'];

$sql = mysqli_query($conn, "update tourism set name='$name', address='$address', open='$open', close='$close', ticket='$ticket', information='$information' where id='$id'");
if ($sql){
	echo "Success Updated!<br>";
	echo "Back to <a href='../?page=wisata'>Dashboard</a>";
}else {
	echo 'error';
}
?>