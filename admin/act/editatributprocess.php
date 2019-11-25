<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$pemilik = $_POST['pemilik'];
$jenis = $_POST['selectprd'];
$hp = $_POST['hp'];
$produk = $_POST['produk'];
$jumlah_pek = $_POST['jumlah_pek'];
$selectjns = $_POST['selectjns'];
$selectstat = $_POST['selectstat'];
$sql = mysqli_query($conn, "update umkm set name='$nama', owner='$pemilik', cp='$hp', address='$alamat', 
	employee='$jumlah_pek',
	id_type='$selectjns', 
	id_place_status=$selectstat 
	where id='$id'");
$sql1 = mysqli_query($conn, "update detail_product set id_product='$jenis' where id_umkm='$id'");

if ($sql || $sql1){
	echo "Success Updated!<br>";
	echo "Back to <a href='../'>Dashboard</a>";
}else {
	echo 'error';
}
?>