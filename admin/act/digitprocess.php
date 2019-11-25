<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$nama = $_POST['nama'];
$alamat = $_POST['alamat'];
$pemilik = $_POST['pemilik'];
$hp = $_POST['hp'];
$jumlah_pek = $_POST['jumlah_pek'];
$id_jenis_umkm = $_POST['selectjns'];
$id_status_tempat = $_POST['selectstat'];
$geom = $_POST['geom'];
echo $geom;

$sql = mysqli_query($conn, "insert into umkm (id, name, address, owner, cp, employee , id_type , id_place_status, geom ) 
	values ('$id', '$nama', '$alamat', '$pemilik', '$hp', $jumlah_pek ,$id_jenis_umkm, $id_status_tempat, 
	ST_GeomFromText('$geom'))");
//var_dump($_POST);

if ($sql){
	
	echo "Success Create Data!<br>";
	echo "Back to <a href='../?page=industry'>Dashboard</a>";
}else{
	echo 'error';
	echo $conn->error;
	
}

?>