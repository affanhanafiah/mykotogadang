<div class="row">
<div class="col-xs-12">
	<div class="box">
		<div class="box-body">
			<div id="form">
			<?php if (isset($_GET['id'])){
				$id=$_GET['id'];
				$sql = mysqli_query($conn, "SELECT * FROM mosque where id='$id'");
				$data =  mysqli_fetch_array($sql);
			?>
				<h4 style="text-transform:capitalize;">Ubah Data Atribut <?php echo $data['nama'] ?></h4>
				<form role="form" action="act/editatributmasjidprocess.php" method="post">
				<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-floppy-o"></i> Save</button>
					<input type="text" class="form-control hidden" name="id" value="<?php echo $id; ?>">
					<div class="form-group" style="clear:both">
						<label for="name">Name</label>
						<input type="text" class="form-control" name="name" value="<?php echo $data['name'] ?>">
					</div>
					
					<div class="form-group">
						<label for="address">Address</label>
						<input type="text" class="form-control" name="address" value="<?php echo $data['address'] ?>">

					</div>

					<div class="form-group">
						<label for="capacity">Capacity</label>
						<input type="text" class="form-control" name="capacity" value="<?php echo $data['capacity'] ?>">
					</div>	


					</div>
					
				</form>
			<?php }	?>
			</div>
		</div>
	</div><!-- /.box -->
</div><!-- /.col -->
</div>