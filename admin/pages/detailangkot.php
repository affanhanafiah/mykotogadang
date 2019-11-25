<?php
require 'inc/connect.php';
$id=$_GET["id"];


// date_default_timezone_set('Asia/Jakarta');
// $day=date("w");
// $query="SELECT distinct a.id_kecamatan, a.id,a.nama_industri, a.pemilik,a.cp,a.alamat,a.produk,a.harga_barang,a.foto,a.jumlah_karyawan, b.status, c.nama_jenis_industri, ,ST_X(ST_Centroid(a.geom)) AS lng, ST_Y(ST_CENTROID(a.geom)) As lat

//  FROM industri_kecil_region as a left join status_tempat as b on a.id_status_tempat=b.id_status_tempat left join jenis_industri as c on a.id_jenis_industri=c.id_jenis_industri, kecamatan where st_contains(kecamatan.geom, a.geom) and kecamatan.id_kecamatan='$id2' and c.id_jenis_industri='$' and b.id_status_tempat = '$cari' order by a.nama_industri ASC";


$query="SELECT id_angkot, Destination, Track, id_color, cost FROM angkot where angkot.id_angkot='$id' ";


$hasil=mysqli_query($conn, $query);
while($row = mysqli_fetch_array($hasil)){
	$id=$row['id_angkot'];
	$destination=$row['destination'];
	$track=$row['track'];
	$color=$row['id_color'];
	$cost=$row['cost'];
	
	
if ($foto=='null' || $foto=='' || $foto==null){
		$foto='foto.jpg';
	}

}
?>
<!-- Default box -->
<div class="row">
	<div class="col-lg-7 col-xs-7 col-r-0">
		<div class="box">
			<div class="box-header with-border">
			  <h2 class="box-title" style="text-transform:capitalize;"><b> <?php echo $name ?></b></h2>
			</div>
			<div class="box-body">
				<table>
					<tbody  style='vertical-align:top;'>
						<tr><td><b>Id Angkot</b></td><td>:</td><td><?php echo $id ?></td></tr>
						<tr><td><b>Destination</b></td><td> :&nbsp; </td><td style='text-transform:capitalize;'><?php echo $destination ?></td></tr>
						<tr><td><b>Track</b></td><td>:</td><td><?php echo $track ?></td></tr>
						<tr><td><b>Color</b></td> <td> :</td><td><?php echo $color ?></td></tr>
						<tr><td><b>Cost<b> </td><td>: </td><td><?php echo $cost ?></td></tr>
						
					</tbody>
				</table>
			</div>
			<br><!-- /.box-body -->
			<div class="box-footer">
				<div class="btn-group">
					<a href="?page=formeditatributangkot&id=<?php echo $id ?>" class="btn btn-default"><i class="fa fa-edit"></i> Data atribut</a>
				</div>
			</div><!-- /.box-footer-->
		</div><!-- /.box -->
	</div>
	
</div>
<script>
	
</script>