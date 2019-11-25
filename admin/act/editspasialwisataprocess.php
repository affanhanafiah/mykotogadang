<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$geom = $_POST['geom'];
$sql = mysqli_query($conn, "update tourism set geom=ST_GeomFromText('$geom') where id='$id'");
if ($sql){
	header("location:../?page=detailwisata&id=$id");
}else {
	echo 'error';
}
?>