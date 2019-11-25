      <?php
      // include('../act/session.php');
      
if(isset($_GET['id']))
  {
  $id=$_GET["id"];

  $sql="DELETE from angkot where id=$id";

  if(mysqli_query($conn, $sql)){
      echo"<script>alert ('Data Berhasil Dihapus');</script>";
      //header("location:../?page=souvenirs");
    }else
    {
      echo"<script>alert ('Data Gagal Dihapus');</script>";
    }
  }

      ?>


                    <div class="col-lg-15 ds" id="hide2"> 
                       
          
              <div class="box-body">

       
    
    <!-- <div class="col-xs-6">
    <div id=example_length" class"dataTables_length">
    <label>
    <select size="1" name="example1_length" aria-controls="example1">
    <option value="10">10</option>
    <option value="25">25</option>
    <option value="50">50</option>
    <option value="100">100</option>
    </select>
    "data per halaman"
    </label>
    </div>
    </div>
 -->
     

    </div>
    <br><br><br>

           
                        
          
           
              
              
              <table  id="example1" class="table table-hover table-bordered table-striped">
    <thead>
      <tr>
      <!-- <th>ID</th> -->
      <th>Destination</th>
      <th>Track</th>
      <th>Color</th>
      <th>Cost</th>
      <th>Aksi</th>

      </tr>
    </thead>
    <tbody>
    
<?php
    include("inc/connect.php");
    $id = $_GET['id'];
    $querysearch  ="SELECT id_angkot,destination,track,color,cost FROM angkot,angkot_color where angkot.id_color=angkot_color.id order by id_angkot ASC";
             
    $hasil=mysqli_query($conn, $querysearch);
    while($baris = mysqli_fetch_array($hasil))
    {
          $id_angkot=$baris['id_angkot'];
          $destination=$baris['destination'];
          $track=$baris['track'];
          $id_color=$baris['color'];
          $cost=$baris['cost'];
          
          $dataarray[]=array('id_angkot'=>$id_angkot,'destination'=>$destination,'track'=>$track,'id_color'=>$id_color,'cost'=>$cost );
?>
    <tr>
     
      <td><?php echo "$destination" ?></td>
      <td><?php echo "$track" ?></td>
      <td><?php echo "$id_color" ?></td>
      <td><?php echo "$cost" ?></td>
      <td><div class="btn-group">
        <a href="?page=detailangkot&id=<?php echo $id_angkot; ?>" class="btn btn-sm btn-default" title='Detail'><i class="fa fa-exclamation-circle"></i> Detail</a>
        </div>
        <div class="btn-group">
        <a href="?page=wisata&id=<?php echo $id; ?>" onclick="return confirm('Are You Sure To Delete?')" class="btn btn-sm btn-default" title='Delete'><i class="fa fa-trash"></i></a>
        </div>
      </td>

    </tr>
<?php
    }
//echo json_encode ($dataarray);
?>

    </tbody>  
    </table>


  </div><!-- /.box-body -->
            
           
             