<div class="row">
<form role="form" action="act/digitmasjidprocess.php" method="post">
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
          $query = mysqli_query($conn, "SELECT MAX(id) AS id FROM mosque");
          $result = mysqli_fetch_array($query);
          $idmax = $result['id'];
          if ($idmax==null) {$idmax=1;}
          else {$idmax++;}
        ?>
            <h3>Please Add The Data</h3>
                      <div class="desc" >
                        <!-- <form role="form" action="insertik.php" method="post"> -->
         <div class="form-group"> 
          <label for="id"><span style="color:red"></span>ID</label>
          <input type="text" class="form-control" id="id" name="id" value="" required >
        </div>
        
        <div class="form-group">
          <label for="geom"><span style="color:red">*</span> Coordinate</label>
          <textarea class="form-control" id="geom" name="geom" readonly required></textarea>
        </div>
        <div class="form-group">
          <label for="name"><span style="color:red">*</span>Name of Masjid</label>
          <input type="text" class="form-control" name="name" value="" required>
        </div>

        <div class="form-group">
          <label for="address"><span style="color:red">*</span>Address</label>
          <input type="text" class="form-control" name="address" value="" required>
        </div>

        <div class="form-group">
          <label for="capacity"><span style="color:red">*</span>Capacity</label>
          <input type="text" class="form-control" name="capacity" value="" required>
        </div>
        

         <!--div class="form-group">
          <label for="jns"><span style="color:red">*</span>Type of Object</label>
          <select required name="selectjns" id="jns" class="form-control">
          <option value="">-Choose-</option>
             <?php
                                
              $stat=mysqli_query($conn, "select * from jenis_mesjid ");
              while($rowstat = mysqli_fetch_assoc($stat))
              {
              echo"<option value=".$rowstat['id_jenis_mesjid'].">".$rowstat['nama_jenis_mesjid']."</option>";
              }
              ?>
                              
          </select>
        </div--> 

    
        <button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>   
<!-- </form> -->

                      </div>
                                            
                  </div>
                  </form>
                  </div>
<script src="inc/mapedit.js" type="text/javascript"></script>
        