      <?php
      // include('../act/session.php');
      
if(isset($_GET['id']))
  {
  $id=$_GET["id"];

  $sql="DELETE from culinary where id='$id'";

  if(mysqli_query($conn, $sql)){
      echo"<script>alert ('Data Berhasil Dihapus');</script>";
      //header("location:../admin");
    }else
    {
      echo"<script>alert ('Data Gagal Dihapus');</script>";
    }
  }

      ?>


                    <div class="col-lg-15 ds" id="hide2"> 
                       
          
              <div class="box-body">

       
    <div class="btn-group pull-right">
    <a href="?page=formkul" class="btn btn-default">Add Data <i class="fa fa-plus"></i></a>
    </div>
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
      <th>Name</th>
      <th>Address</th>
      <th>CP</th>
      <th>Aksi</th>
      </tr>
    </thead>
    <tbody>
    
<?php
    include("inc/connect.php");
    $id = $_GET['id'];
    $querysearch  ="SELECT culinary.id, culinary.name, culinary.address, culinary.cp FROM culinary order by id ASC ";
             
    $hasil=mysqli_query($conn, $querysearch);
    while($baris = mysqli_fetch_array($hasil))
    {
      $id=$baris['id'];
          $name=$baris['name'];
          $address=$baris['address'];
          $cp=$baris['cp'];
          $dataarray[]=array('id'=>$id,'name'=>$name,'address'=>$address,'cp'=>$cp);
?>
    <tr>
     
      <td><?php echo "$name" ?></td>
      <td><?php echo "$address" ?></td>
      <td><?php echo "$cp" ?></td>

      <td><div class="btn-group">
        <a href="?page=detailculinary&id=<?php echo $id; ?>" class="btn btn-sm btn-default" title='Detail'><i class="fa fa-exclamation-circle"></i> Detail</a>
        </div>
        <div class="btn-group">
        <a href="?page=culinary&id=<?php echo $id; ?>" onclick="return confirm('Are You Sure To Delete?')" class="btn btn-sm btn-default" title='Delete'><i class="fa fa-trash"></i></a>
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
            
           
             