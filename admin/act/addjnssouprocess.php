<?php
include ('../inc/connect.php');
$id_jenis_oleh = $_POST['id_jenis_oleh'];
$jenis = $_POST['jenis'];


$sql = mysqli_query($conn, "insert into jenis_oleh_oleh (id_jenis_oleh, jenis_oleh) values ('$id_jenis_oleh', '$jenis')");


if ($sql){
	header("location:../?page=jenissouvenirs");
}else{
	echo 'error';
}

?>