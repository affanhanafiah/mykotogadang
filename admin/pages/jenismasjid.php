     <?php
if(isset($_GET['id_jenis_mesjid']))
  {
  $id_jenis_mesjid=$_GET["id_jenis_mesjid"];

  $sql="DELETE from jenis_mesjid where id_jenis_mesjid=$id_jenis_mesjid";

  if(mysqli_query($conn, $sql)){
      echo"<script>alert ('Data Berhasil Dihapus');</script>";
      //header("location:./?page=jenissouvenirs");
    }else
    {
      echo"<script>alert ('Data Gagal Dihapus');</script>";
    }
  }

      ?>       

              <div class="col-lg-15 ds" id="hide2"> 
                       
          
              <div class="box-body">
              
              
    <div class="btn-group pull-right">
    <a href="?page=formjnssou" class="btn btn-default">Tambah <i class="fa fa-plus"></i></a>
    </div>
    </div>
              <table id="" class="table table-hover table-bordered table-striped">
    <thead>
      <tr>
     <!--  <th>ID Jenis Industri</th> -->
      <th>Jenis Mesjid</th>
      <th>Aksi</th>

      </tr>
    </thead>
    <tbody>
    
<?php
    include '../connect.php';
    $id_jenis_mesjid = $_GET['id_jenis_mesjid'];
    $querysearch  ="SELECT jenis_mesjid.id_jenis_mesjid, jenis_mesjid.nama_jenis_mesjid FROM jenis_mesjid order by id_jenis_mesjid ASC ";
             
    $result=mysqli_query($conn, $querysearch);
    while($baris = mysqli_fetch_array($result))
    {
          $id_jenis_mesjid=$baris['id_jenis_mesjid'];
          $nama_jenis_mesjid=$baris['nama_jenis_mesjid'];
          
          
          $dataarray[]=array('id_jenis_mesjid'=>$id_jenis_mesjid,'nama_jenis_mesjid'=>$nama_jenis_mesjid);
?>
    <tr>
      
      <td><?php echo "$nama_jenis_mesjid" ?></td>
       <td><div class="btn-group">

      
      <a href="?page=editjnssou&id_jenis_mesjid=<?php echo $id_jenis_mejid; ?>" class="btn btn-sm btn-default" title='Edit'><i class="fa fa-edit"></i> Edit</a>
      </div>
      <div class="btn-group">
        <a href="?page=jenissouvenirs&id_jenis_mesjid=<?php echo $id_jenis_mesjid; ?>" onclick="return confirm('Are You Sure To Delete?')" class="btn btn-sm btn-default" title='Delete'><i class="fa fa-trash"></i></a>
       </div>
    </tr>
<?php
    }
//echo json_encode ($dataarray);
?>

    </tbody>
    </table>


  </div><!-- /.box-body -->
              </div>
              

