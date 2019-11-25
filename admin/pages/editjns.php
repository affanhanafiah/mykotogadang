<div class="row">
<div class="col-xs-12">
	<div class="box">
		<div class="box-body">
				<h4 style="text-transform:capitalize;">Type of UMKM</h4>
				<?php if (!isset($_GET['id_type'])){ ?>
				<form role="form" action="act/layananins.php" method="post">
					
					<button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>
					<div class="form-group" style="clear:both" id="l_form" >
						<label for="name">Type of UMKM</label>
						<input type="text" class="form-control" name="" value="" style="margin-bottom:3px;" autofocus required>
					</div>
				</form>
				<?php } if (isset($_GET['id_type'])){
					$id_type=$_GET['id_type'];
					$sql = mysqli_query($$conn, "SELECT * FROM umkm_type where id_type=$id_type");
					$data = mysqli_fetch_array($sql)
				?>
				<form role="form" action="act/layananupd.php" method="post">
					<button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>
					<input type="text" class="form-control hidden" name="id_type" value="<?php echo $data['id_type'] ?>">
					<div class="form-group" style="clear:both">
						<label for="name">UMKM Type</label>
						<input type="text" class="form-control" name="name" value="<?php echo $data['name'] ?>" required>
					</div>
				</form>
				<?php } ?>
			</div>
		</div>
	</div><!-- /.box -->
</div><!-- /.col -->
</div>
<script>

</script>