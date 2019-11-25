<div class="row">
<div class="col-xs-12">
	<div class="box">
		<div class="box-body">
			<div id="form">
			<?php if (isset($_GET['id'])){
				$id=$_GET['id'];
				$sql = mysqli_query($conn, "SELECT * FROM tourism where id='$id'");
				$data =  mysqli_fetch_array($sql);
			?>
				<h4 style="text-transform:capitalize;">Ubah Data Atribut <?php echo $data['nama'] ?></h4>
				<form role="form" action="act/editatributwisataprocess.php" method="post">
				<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-floppy-o"></i> Save</button>
					<input type="text" class="form-control hidden" name="id" value="<?php echo $id ?>">
					<div class="form-group" style="clear:both">
						<label for="name">Name</label>
						<input type="text" class="form-control" name="name" value="<?php echo $data['name'] ?>">
					</div>
					<div class="form-group" style="clear:both">
						<label for="address">address</label>
						<input type="text" class="form-control" name="address" value="<?php echo $data['address'] ?>">
					</div>
					<div class="form-group">
						<label for="open">open</label>
						<input type="text" class="form-control" name="open" value="<?php echo $data['open'] ?>">
					</div>
					<div class="form-group">
						<label for="close">close</label>
						<input type="text" class="form-control" name="close" value="<?php echo $data['close'] ?>">
					</div>
					<div class="form-group">
						<label for="ticket">ticket</label>
						<input type="text" class="form-control" name="ticket" value="<?php echo $data['ticket'] ?>">
					</div>
					
					<div class="form-group">
						<label for="information">information</label>
						<input type="text" class="form-control" name="information" value="<?php echo $data['information'] ?>">
					</div>
					
				</form>
			<?php }	?>
			</div>
		</div>
	</div><!-- /.box -->
</div><!-- /.col -->
</div>