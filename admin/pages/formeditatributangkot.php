<div class="row">
<div class="col-xs-12">
	<div class="box">
		<div class="box-body">
			<div id="form">
			<?php if (isset($_GET['id'])){
				$id=$_GET['id'];
				$sql = mysqli_query($conn, "SELECT * FROM angkot where id_angkot='$id'");
				$data =  mysqli_fetch_array($sql);
			?>
				<h4 style="text-transform:capitalize;">Ubah Data Atribut <?php echo $data['nama'] ?></h4>
				<form role="form" action="act/editatributangkotprocess.php" method="post">
				<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-floppy-o"></i> Save</button>
					<input type="text" class="form-control hidden" name="id" value="<?php echo $id ?>">
					<div class="form-group" style="clear:both">
						<label for="destination">destination</label>
						<input type="text" class="form-control" name="destination" value="<?php echo $data['destination'] ?>">
					</div>
					<div class="form-group" style="clear:both">
						<label for="track">track</label>
						<input type="text" class="form-control" name="track" value="<?php echo $data['track'] ?>">
					</div>
					<div class="form-group">
						<label for="color">Produk</label>
						<select required name="color" id="color" class="form-control">
							<?php
								  $sql = mysqli_query($conn, "select * from angkot_color	 
								  	");
								while($dt = mysqli_fetch_array($sql)){
								if ($data[id]==$dt[color]){
									echo "<option value=\"$dt[id]\" selected>$dt[color]</option>";}
								else{
									echo "<option value=\"$dt[id]\">$dt[color]</option>";}
								}
							?>
						</select>
					</div>
					<div class="form-group">
						<label for="cost">cost</label>
						<input type="text" class="form-control" name="cost" value="<?php echo $data['cost'] ?>">
					</div>
					
				</form>
			<?php }	?>
			</div>
		</div>
	</div><!-- /.box -->
</div><!-- /.col -->
</div>