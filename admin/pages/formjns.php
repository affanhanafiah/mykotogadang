<div class="row">
<div class="col-xs-12">
<div class="box">
    <div class="box-body">
<form role="form" action="act/addjnsprocess.php" method="post">
             <!--menampilkan map-->
    <?php
          $query = mysqli_query($conn, "SELECT MAX(id_jenis_umkm) AS id_jenis_umkm FROM jenis_umkm");
          $result = mysqli_fetch_array($query);
          $idmax = $result['id_jenis_umkm'];
          if ($idmax==null) {$idmax=1;}
          else {$idmax++;}
        ?>
           <!-- menampilkan form tambah mesjid-->
            <h4>Please Add The Data</h4>
                      <div class="desc" >

       <!-- <div class="form-group">
          <label for="id"><span style="color:red">*</span>ID</label>
          <input type="text" class="form-control" name="id" value="" required>
        </div> -->
      <input type="text" class="form-control hidden" id="id_jenis_umkm" name="id_jenis_umkm" value="<?php echo $idmax;?>">

        <div class="form-group">
          <label for="jenis"><span style="color:red">*</span>Type of Industry</label>
          <input type="text" class="form-control" name="jenis" value="" required>
        </div>
   
    
        <button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>   


                      </div>
                                           
                  
                  </form>
                  </div>
                  </div>
                  </div>
                  </div>

        