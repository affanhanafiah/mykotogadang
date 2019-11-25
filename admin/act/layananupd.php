<?php
include ('../inc/connect.php');

$id_jenis_umkm	= $_POST['id_jenis_umkm'];
$nama_jenis_umkm = $_POST['nama_jenis_umkm'];

$sql  = "update jenis_umkm set nama_jenis_umkm='$nama_jenis_umkm' where id_jenis_umkm=$id_jenis_umkm";
$insert = mysqli_query($conn, $sql);

if ($insert){
	header("location:../?page=jenis");
}else{
	echo 'error';
}
?>