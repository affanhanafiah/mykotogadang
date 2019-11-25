<?php
include ('../inc/connect.php');

$query = mysqli_query($conn, "SELECT MAX(id_jenis_umkm) AS id_jenis_umkm FROM jenis_umkm");
$result = mysqli_fetch_array($query);
$idmax = $result['id_jenis_umkm'];
if ($idmax==null) {$idmax=1;}
else {$idmax++;}
					
$jenis_umkm = $_POST['jenis_umkm'];


$count = count($jenis_umkm);
$sql  = "insert into jenis_umkm (id_jenis_umkm, nama_jenis_umkm) VALUES ";
 
for( $i=0; $i < $count; $i++ ){
	$sql .= "('{$idmax}','{$jenis_umkm[$i]}')";
	$sql .= ",";
	$idmax++;
}
$sql = rtrim($sql,",");
$insert = mysqli_query($conn, $sql);

if ($insert){
	header("location:../?page=jenis");
}else{
	echo 'error';
}
?>