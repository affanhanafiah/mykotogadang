<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$information = $_POST['information'];


$sql = mysqli_query($conn, "update greatcharacter set name='$name', address='$address', information='$information' where id ='$id'");
if ($sql){
	echo "Success Updated!<br>";
	echo "Back to <a href='../?page=tokohbesar'>Dashboard</a>";
}else {
	echo 'error';
}
?>