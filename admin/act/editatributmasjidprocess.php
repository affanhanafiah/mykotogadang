<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$capacity = $_POST['capacity'];
//$selectjns = $_POST['selectjns'];
$sql = mysqli_query($conn, "update mosque set name='$name', address='$address', capacity='$capacity' where id ='$id'");
if ($sql){
	echo "Success Updated!<br>";
	echo "Back to <a href='../?page=masjid'>Dashboard</a>";
}else {
	echo 'error';
}
?>