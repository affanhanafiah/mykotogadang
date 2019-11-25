<?php
require 'inc/connect.php';
$id=$_GET["id"];
// date_default_timezone_set('Asia/Jakarta');
// $day=date("w");
// $query="SELECT distinct a.id_kecamatan, a.id,a.nama_industri, a.pemilik,a.cp,a.alamat,a.produk,a.harga_barang,a.foto,a.jumlah_karyawan, b.status, c.nama_jenis_industri, ,ST_X(ST_Centroid(a.geom)) AS lng, ST_Y(ST_CENTROID(a.geom)) As lat

//  FROM industri_kecil_region as a left join status_tempat as b on a.id_status_tempat=b.id_status_tempat left join jenis_industri as c on a.id_jenis_industri=c.id_jenis_industri, kecamatan where st_contains(kecamatan.geom, a.geom) and kecamatan.id_kecamatan='$id2' and c.id_jenis_industri='$' and b.id_status_tempat = '$cari' order by a.nama_industri ASC";


$query="select umkm.id, umkm.name as k, umkm.address, umkm.owner, umkm.cp, umkm.employee, 
umkm_type.name, place_status.status, ST_X(ST_Centroid(umkm.geom)) as lng, ST_Y(ST_Centroid(umkm.geom)) as lat
from umkm
left join umkm_type on umkm.id_type=umkm_type.id_type
left join place_status on umkm.id_place_status=place_status.id_place_status
where umkm.id='$id'";


$hasil=mysqli_query($conn, $query);
while($row = mysqli_fetch_array($hasil)){
	$id=$row['id'];
	$nama=$row['k'];
	$alamat=$row['address'];
	$pemilik=$row['owner'];
	$hp=$row['cp'];
	$jumlah_pek=$row['employee'];
	$foto=$row['foto'];
	$name=$row['name'];
	$status=$row['status'];
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
	<div class="col-lg-7 col-xs-11 col-r-0">
		<div class="box">
			<div class="box-header with-border">
			  <h2 class="box-title" style="text-transform:capitalize;"><b> <?php echo $nama ?></b></h2>
			</div>
			<div class="box-body">
				<table>
					<tbody  style='vertical-align:top;'>
						<tr><td><b>Pemilik</b></td><td> :&nbsp; </td><td style='text-transform:capitalize;'><?php echo $pemilik ?></td></tr>
						<tr><td><b>Alamat</b></td> <td> :</td><td><?php echo $alamat ?></td></tr>
						<tr><td><b>Telepon</b></td><td>:</td><td><?php echo $hp ?></td></tr>
						<tr><td><b>Jumlah Pekerja<b> </td><td>: </td><td><?php echo $jumlah_pek ?></td></tr>
						<tr><td><b>Jenis umkm<b> </td><td>: </td><td><?php echo $name ?></td></tr>
						<tr><td><b>Status Tempat<b> </td><td>: </td><td><?php echo $status ?></td></tr>
						<tr><td><b>Data Spasial<b> </td><td>: </td><td><b>Latitude</b> : <?php echo $lat ?> <b>Longitude</b> : <?php echo $lng ?></td></tr>
					</tbody>
				</table>
			</div>
			<br><!-- /.box-body -->
			<div class="box-footer">
				<div class="btn-group">
					<a href="?page=formeditatribut&id=<?php echo $id ?>" class="btn btn-default"><i class="fa fa-edit"></i> Data atribut</a>
					<a href="?page=formeditspasial&id=<?php echo $id ?>" class="btn btn-default"><i class="fa fa-edit"></i> Data spasial</a>
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
					<a href="?page=formeditphoto&id=<?php echo $id ?>" class="btn btn-primary"><i class=""></i> Upload Foto</a>
				</div>
			</div><!-- /.box-footer-->
		</div>
	</div>
</div>
<script>
	
</script>