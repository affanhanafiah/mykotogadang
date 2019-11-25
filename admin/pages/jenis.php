     <?php
if(isset($_GET['id_type']))
  {
  $id_type=$_GET["id_type"];

  $sql="DELETE from umkm_type where id_type=$id_type";

  if(mysqli_query($conn, $sql)){
      echo"<script>alert ('Data Berhasil Dihapus');</script>";
      //header("location:./?page=jenis");
    }else
    {
      echo"<script>alert ('Data Gagal Dihapus');</script>";
    }
  }

      ?>       

              <div class="col-lg-15 ds" id="hide2"> 
                       
          
              <div class="box-body">
              
              
    <div class="btn-group pull-right">
    <a href="?page=formjns" class="btn btn-default">Tambah <i class="fa fa-plus"></i></a>
    </div>
    </div>
              <table id="" class="table table-hover table-bordered table-striped">
    <thead>
      <tr>
     <!--  <th>ID Jenis UMKM</th> -->
      <th>Jenis UMKM</th>
      <th>Aksi</th>

      </tr>
    </thead>
    <tbody>
    
<?php
    include '../connect.php';
    $id_type = $_GET['id_type'];
    $querysearch  ="SELECT umkm_type.id_type, umkm_type.name FROM umkm_type order by id_type ASC ";
             
    $result=mysqli_query($conn, $querysearch);
    while($baris = mysqli_fetch_array($result))
    {
          $id_type=$baris['id_type'];
          $name=$baris['name'];
          
          
          $dataarray[]=array('id_type'=>$id_type,'namr'=>$name);
?>
    <tr>
      
      <td><?php echo "$name" ?></td>
       <td><div class="btn-group">

      
      <a href="?page=editjns&id_jenis_umkm=<?php echo $id_jenis_umkm; ?>" class="btn btn-sm btn-default" title='Edit'><i class="fa fa-edit"></i> Edit</a>
      </div>
      <div class="btn-group">
        <a href="?page=jenis&id_jenis_umkm=<?php echo $id_jenis_umkm; ?>" onclick="return confirm('Are You Sure To Delete?')" class="btn btn-sm btn-default" title='Delete'><i class="fa fa-trash"></i></a>
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
              

