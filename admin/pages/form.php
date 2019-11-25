<div class="row">
<form role="form" action="act/digitprocess.php" method="post">
              <div class="col-lg-8 ds" id="hide2"> <!--menampilkan map-->
               <h3>                    
          <input id="latlng" type="text" value="" placeholder="  latitude, longitude">
          <button class="btn btn-default my-btn" id="btnlatlng" type="button" title="Geocode"><i class="fa fa-search"></i></button>
          <button class="btn btn-default my-btn" id="delete-button" type="button" title="Remove shape"><i class="fa fa-trash"></i></button> </h3>
          <div class="desc">
                    <div id="map" style="width:100%;height:400px; z-index:50"></div>
                  </div>
             
              </div>




        <div class="col-lg-4 ds" id="hide3"> 
                    <?php
          $query = mysqli_query($conn, "SELECT MAX(id) AS id FROM umkm");
          $result = mysqli_fetch_array($query);
          $idmax = $result['id'];
          if ($idmax==null) {$idmax=1;}
          else {$idmax++;}
        ?>
            <h3>Please Add The Data</h3>
        <div class="desc" >
                        <!-- <form role="form" action="insertik.php" method="post"> -->
       <!--  
        <input type="text" class="form-control hidden" id="id" name="id" value="<?php echo $idmax;?>"> -->
        <div class="form-group"> 
          <label for="id"><span style="color:red"></span>ID</label>
          <input type="text" class="form-control" id="id" name="id" value="" required >
        </div>
        <div class="form-group">
          <label for="geom"><span style="color:red">*</span> Coordinate</label>
          <textarea class="form-control" id="geom" name="geom" readonly required></textarea>
        </div>
        <div class="form-group">
          <label for="nama"><span style="color:red">*</span>Name of Industry</label>
          <input type="text" class="form-control" name="nama" value="" required>
        </div>
        <div class="form-group">
          <label for="pemilik"><span style="color:red">*</span>Owner</label>
          <input type="text" class="form-control" name="pemilik" value="" required>
        </div>
        <div class="form-group">
          <label for="cp"><span style="color:red">*</span> Contact Person</label>
          <input type="text" class="form-control" name="hp" value="" required>
        </div>
        <div class="form-group">
          <label for="alamat"><span style="color:red">*</span>Address</label>
          <input type="text" class="form-control" name="alamat" value="" required>
        </div>
        <!--  -->
         <div class="form-group">
          <label for="stat"><span style="color:red">*</span>Status of Place</label>
          <select required name="selectstat" id="status_tem" class="form-control">
          <option value="">-Choose-</option>
             <?php
                                
              $stat=mysqli_query($conn, "select * from place_status ");
              while($rowstat = mysqli_fetch_assoc($stat))
              {
              echo"<option value=".$rowstat['id_place_status'].">".$rowstat['status']."</option>";
              }
              ?>
                              
          </select>
        </div> 
        <div class="form-group">
          <label for="jml"><span style="color:red">*</span>Number of Employees</label>
          <input type="text" class="form-control" name="jumlah_pek" value="" required>
        </div>
        <div class="form-group">
          <label for="jns"><span style="color:red">*</span>Type of Industry</label>
          <select required name="selectjns" id="jns" class="form-control">
          <option value="">-Choose-</option>
             <?php
                                
              $jns=mysqli_query($conn, "select * from umkm_type ");
              while($rowjns = mysqli_fetch_assoc($jns))
              {
              echo"<option value=".$rowjns['id_type'].">".$rowjns['name']."</option>";
              }
              ?>
                              
          </select>
        </div>  
        
    
        <button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>   
<!-- </form> -->

                      </div>
                                            
                  </div>
                  </form>
                  </div>
<script src="inc/mapedit.js" type="text/javascript"></script>
        