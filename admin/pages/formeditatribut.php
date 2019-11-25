<div class="row">
<div class="col-xs-12">
	<div class="box">
		<div class="box-body">
			<div id="form">
			<?php if (isset($_GET['id'])){
				$id=$_GET['id'];
				$sql = mysqli_query($conn, "SELECT umkm.id,umkm.name as k,address,owner,cp,product.name as product,employee,
umkm_type.name as nama_jenis_umkm, place_status.status, 
ST_X(ST_Centroid(umkm.geom)) AS lng, ST_Y(ST_CENTROID(umkm.geom)) As lat 
FROM umkm 
left join detail_product on umkm.id=detail_product.id_umkm
left join product on detail_product.id_product=product.id_product
left join place_status on umkm.id_place_status=place_status.id_place_status
left join umkm_type on umkm.id_type=umkm_type.id_type 
where  umkm.id='$id'");
				$data =  mysqli_fetch_array($sql);
			?>
				<h4 style="text-transform:capitalize;">Ubah Data Atribut <?php echo $data['k'] ?></h4>
				<form role="form" action="act/editatributprocess.php" method="post">
				<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-floppy-o"></i> Simpan</button>
					<input type="text" class="form-control hidden" name="id" value="<?php echo $id ?>">
					<div class="form-group" style="clear:both">
						<label for="nama">Nama Industri</label>
						<input type="text" class="form-control" name="nama" value="<?php echo $data['k'] ?>">
					</div>
					<div class="form-group" style="clear:both">
						<label for="pemilik">Pemilik</label>
						<input type="text" class="form-control" name="pemilik" value="<?php echo $data['owner'] ?>">
					</div>
					<div class="form-group">
						<label for="telepon">Telepon</label>
						<input type="text" class="form-control" name="hp" value="<?php echo $data['cp'] ?>">
					</div>
					<div class="form-group">
						<label for="alamat">Alamat</label>
						<input type="text" class="form-control" name="alamat" value="<?php echo $data['address'] ?>">
					</div>
					<div class="form-group">
						<label for="selectjns">Produk</label>
						<select required name="selectprd" id="selectprd" class="form-control">
							<?php
								  $sql = mysqli_query($conn, "select * from product	 
								  	");
								while($dt = mysqli_fetch_array($sql)){
								if ($data[product]==$dt[product]){
									echo "<option value=\"$dt[id_product]\" selected>$dt[name]</option>";}
								else{
									echo "<option value=\"$dt[id_product]\">$dt[name]</option>";}
								}
							?>
						</select>
					</div>
					
					<div class="form-group">
						<label for="karyawan">Jumlah Karyawan</label>
						<input type="text" class="form-control" name="jumlah_pek" value="<?php echo $data['employee'] ?>">
					</div>
					<div class="form-group">
						<label for="selectjns">Jenis UMKM</label>
						<select required name="selectjns" id="selectjns" class="form-control">
							<?php
								  $sql = mysqli_query($conn, "select * from umkm_type	 
								  	");
								while($dt = mysqli_fetch_array($sql)){
								if ($data[id_type]==$dt[id_type]){
									echo "<option value=\"$dt[id_type]\" selected>$dt[name]</option>";}
								else{
									echo "<option value=\"$dt[id_type]\">$dt[name]</option>";}
								}
							?>
						</select>
					</div>
					
					<div class="form-group">
						<label for="selectstat">Status Tempat</label>
						<select required name="selectstat" id="selectstat" class="form-control">
							<?php
								  $sql = mysqli_query($conn, "select * from place_status 
								  	");
								while($dt = mysqli_fetch_array($sql)){
								if ($data[id_place_status]==$dt[id_place_status]){
									echo "<option value=\"$dt[id_place_status]\" selected>$dt[status]</option>";}
								else{
									echo "<option value=\"$dt[id_place_status]\">$dt[status]</option>";}
								}
							?>
						</select>
					</div>
					
									</form>
			<?php }	?>
			</div>
		</div>
	</div><!-- /.box -->
</div><!-- /.col -->
</div>