<?php
// $host = "localhost" ;
// $user = "postgres" ;
// $password = "admin" ;
// $dbname = "kotogadang";
// $port = "5432";

// //Koneksi dan memilih database di server
// $link= pg_connect("host=".$host." port=".$port." dbname=".$dbname." user=".$user." password=".$password) or die("Koneksi gagal");
$conn = mysqli_connect("localhost" ,"newuser" ,"root","kotogadang") or die("Gagal");
?>