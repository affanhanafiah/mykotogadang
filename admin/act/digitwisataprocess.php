<?php
include ('../inc/connect.php');
$id = $_POST['id'];
$name = $_POST['name'];
$address = $_POST['address'];
$open = $_POST['open'];
$close = $_POST['close'];
$ticket = $_POST['ticket'];
$information = $_POST['information'];
$geom = $_POST['geom'];

$sql = mysqli_query($conn, "insert into tourism (id, name, address, open, close, ticket, information, geom) values ('$id', '$name', '$address', '$open', '$close', '$ticket', '$information',  ST_GeomFromText('$geom'))");


if ($sql){
  
  echo "Success Create Data!<br>";
  echo "Back to <a href='../?page=wisata'>Dashboard</a>";
}else{
  echo 'error';
}

?>