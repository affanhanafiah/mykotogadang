<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$destination = $_POST['destination'];
$track = $_POST['track'];
$id_color = $_POST['color'];
$cost = $_POST['cost'];
$sql = mysqli_query($conn, "update angkot set id_angkot='$id', destination='$destination', track='$track', id_color=$id_color, 
	cost=$cost 
	where id_angkot='$id'");

if ($sql){
	echo "Success Updated!<br>";
	echo "Back to <a href='../'>Dashboard</a>";
}else {
	echo 'error';
}
?>