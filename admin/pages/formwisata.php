<div class="row">
<form role="form" action="act/digitwisataprocess.php" method="post">
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
          $query = mysqli_query($conn, "SELECT MAX(id) AS id FROM tourism");
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
          <label for="name"><span style="color:red">*</span>Name </label>
          <input type="text" class="form-control" name="name" value="" required>
        </div>
         <div class="form-group">
          <label for="address"><span style="color:red">*</span>Address</label>
          <input type="text" class="form-control" name="address" value="" required>
        </div>
         <div class="form-group">
          <label for="open"><span style="color:red">*</span> Open Time </label>
          <input type="text" class="form-control" name="open" value="" required>
        </div>
        <div class="form-group">
          <label for="close"><span style="color:red">*</span>Close Time</label>
          <input type="text" class="form-control" name="close" value="" required>
        </div>

        <div class="form-group">
          <label for="ticket"><span style="color:red">*</span>Ticket</label>
          <input type="text" class="form-control" name="ticket" value="" required>
        </div>
        

        <div class="form-group">
          <label for="information"><span style="color:red">*</span>Information</label>
          <input type="text" class="form-control" name="information" value="" required>
        </div>

         <!-- <div class="form-group">
          <label for="stat"><span style="color:red">*</span>Status of Place</label>
          <select required name="stat" id="stat" class="form-control">
          <option value="">-Choose-</option>
             <?php
                                
              // $stat=pg_query("select * from status_tempat ");
              // while($rowstat = pg_fetch_assoc($stat))
              // {
              // echo"<option value=".$rowstat['id_status_tempat'].">".$rowstat['status']."</option>";
              //}
              ?>
                              
          </select>
        <!-- <div class="form-group">
          <label for="jns"><span style="color:red">*</span>Type of Souvenirs</label>
          <select required name="jns" id="jns" class="form-control">
          <option value="">-Choose-</option>
             <?php
                                
              // $jns=pg_query("select * from jenis_oleh_oleh ");
              // while($rowjns = pg_fetch_assoc($jns))
              // {
              // echo"<option value=".$rowjns['id_jenis_oleh'].">".$rowjns['jenis_oleh']."</option>";
              // }
              ?>
                              
          </select>
        </div>   -->
        <button type="submit" class="btn btn-primary pull-right">Save <i class="fa fa-floppy-o"></i></button>   
<!-- </form> -->

                      </div>
                                            
                  </div>
                  </form>
                  </div>
<script src="inc/mapedit.js" type="text/javascript"></script>
        