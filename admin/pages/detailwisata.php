<?php
require 'inc/connect.php';
$id=$_GET["id"];
// date_default_timezone_set('Asia/Jakarta');
// $day=date("w");
// $query="SELECT distinct a.id_kecamatan, a.id,a.nama_industri, a.pemilik,a.cp,a.alamat,a.produk,a.harga_barang,a.foto,a.jumlah_karyawan, b.status, c.nama_jenis_industri, ,ST_X(ST_Centroid(a.geom)) AS lng, ST_Y(ST_CENTROID(a.geom)) As lat

//  FROM industri_kecil_region as a left join status_tempat as b on a.id_status_tempat=b.id_status_tempat left join jenis_industri as c on a.id_jenis_industri=c.id_jenis_industri, kecamatan where st_contains(kecamatan.geom, a.geom) and kecamatan.id_kecamatan='$id2' and c.id_jenis_industri='$' and b.id_status_tempat = '$cari' order by a.nama_industri ASC";


$query="SELECT tourism.id, name, address, open, close, ticket, information, ST_X(ST_Centroid(tourism.geom)) AS lng, ST_Y(ST_CENTROID(tourism.geom)) As lat FROM tourism where tourism.id='$id' ";


$hasil=mysqli_query($conn, $query);
while($row = mysqli_fetch_array($hasil)){
	$id=$row['id'];
	$name=$row['name'];
	$address=$row['address'];
	$open=$row['open'];
	$close=$row['close'];
	$ticket=$row['ticket'];
	$information=$row['information'];
	$lat=$row['lat'];
	$lng=$row['lng'];
	if ($lat=='' || $lat==''){
		$lat='<span style="color:red">Kosong</span>';
		$lng='<span style="color:red">Kosong</span>';
	}
	
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
						<tr><td><b>Address</b></td><td> :&nbsp; </td><td style='text-transform:capitalize;'><?php echo $address ?></td></tr>
						<tr><td><b>Open</b></td><td>:</td><td><?php echo $open ?></td></tr>
						<tr><td><b>Close</b></td> <td> :</td><td><?php echo $close ?></td></tr>
						<tr><td><b>Ticket<b> </td><td>: </td><td><?php echo $ticket ?></td></tr>
						<tr><td><b>Information<b> </td><td>: </td><td><?php echo $information ?></td></tr>
						<tr><td><b>Data Spasial<b> </td><td>: </td><td><b>Latitude</b> : <?php echo $lat ?> <b>Longitude</b> : <?php echo $lng ?></td></tr>
					</tbody>
				</table>
			</div>
			<br><!-- /.box-body -->
			<div class="box-footer">
				<div class="btn-group">
					<a href="?page=formeditatributwisata&id=<?php echo $id ?>" class="btn btn-default"><i class="fa fa-edit"></i> Data atribut</a>
					<a href="?page=formeditspasialwisata&id=<?php echo $id ?>" class="btn btn-default"><i class="fa fa-edit"></i> Data spasial</a>
				</div>
			</div><!-- /.box-footer-->
		</div><!-- /.box -->
	</div>
	<div class="col-lg-5 col-xs-5">
		<div class="box">
			<div class="box-header with-border">
			  <h2 class="box-title">Foto</h2>
			</div>
			
			<br>
			<div class="box-footer">
				<div class="btn-group">
					<a href="?page=formeditphotowisata&id=<?php echo $id ?>" class="btn btn-primary"><i class=""></i> Upload Foto</a>
				</div>
			</div><!-- /.box-footer-->
		</div>
	</div>
</div>
<script>
	
</script>