<div class="box box-primary">
	<div class="box-header">
	  <h3 class="box-title">Upload Foto</h3>
	</div><!-- /.box-header -->
	<!-- form start -->
	<form role="form" action="act/uploadphototokohbesar.php" enctype="multipart/form-data" method="post">
	  <div class="box-body">
		<?php $id=$_GET['id'] ?>
		<input type="text" class="form-control hidden" name="id" value="<?php echo $id ?>">
		<div class="form-group">
		  <label for="file">Upload Foto</label>
		  <input type="file" class="" style="background:none;border:none; width:1000px; " name="image" required>
		</div>
		<span style="color:red;">*Ukuran gambar maksimal 500kb</span>
	  </div><!-- /.box-body -->

	  <div class="box-footer">
		<button type="submit" class="btn btn-primary"><i class="fa fa-upload"></i> Upload</button>
	  </div>
	</form>
</div>