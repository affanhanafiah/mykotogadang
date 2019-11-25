<?php 
	include ('../inc/connect.php');
	$id = $_POST['id'];
	$kode = mysqli_query($conn, "select max(serial_number) + 1 as max from tourism_gallery");
	$kode2= mysqli_fetch_array($kode);	
	$kode3=$kode2['max'];

	$jenis_gambar=$_FILES['image']['type'];
	if(($jenis_gambar=="image/jpeg" || $jenis_gambar=="image/jpg" || $jenis_gambar=="image/gif"  || $jenis_gambar=="image/png") && ($_FILES["image"]["size"] <= 700000)){
		$sourcename=$_FILES["image"]["name"];
		$name=$id.'_'.$sourcename;
		$filepath="../../galeri/".$name;
		$tmpt="galeri/".$name;
		move_uploaded_file($_FILES["image"]["tmp_name"],$filepath);
		$sql = mysqli_query($conn, "insert into tourism_gallery values ('$kode3','$id','$tmpt')");
		if($sql){
			header("location:../?page=detailwisata&id=$id");
		}
	}
	else{
		echo "The Picture Isn't Valid!<br>";
		echo "Go Back To <a href='../?page=detailwisata&id=$id'>halaman detail</a>";
	}
?>