<?php
include ('../inc/connect.php');
$id_jenis_umkm = $_POST['id_jenis_umkm'];
$jenis = $_POST['jenis'];


$sql = mysqli_query($conn, "insert into jenis_umkm (id_jenis_umkm, nama_jenis_umkm) values ('$id_jenis_umkm', '$jenis')");


if ($sql){
	header("location:../?page=jenis");
}else{
	echo 'error';
}

?>