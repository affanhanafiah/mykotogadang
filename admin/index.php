<?php
session_start();
if(!isset($_SESSION['admin'])){
  echo"<script language='JavaScript'>document.location='login.php'</script>";
  exit();
}
 
include("inc/connect.php");?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>ADMIN</title>

    <!-- <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBNnzxae2AewMUN0Tt_fC3gN38goeLVdVE&sensor=true&libraries=drawing&callback=initialize"  async defer></script> -->
    <!-- <script src="mapedit.js" type="text/javascript"></script> -->
    <link href="../assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="../assets/libs/jquery.min.js"></script>
    
    <link href="../assets/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <script src="../assets/js/chart-master/Chart.js"></script>
        <link href="plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
    <link href="plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet"/>
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM2fDXHmGzCDmDBk3bdPIEjs6zwnI1kGQ&libraries=drawing"></script>
    
  </head>

   <body>

  <section id="container" >
      <header class="header black-bg">
      <?PHP include("inc/header.php"); ?>
      </header>

    <aside>
    <div id="sidebar"  class="nav-collapse " >
              <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">

            <?PHP include("inc/sidebar.php"); ?>
             </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>


        <section id="main-content">
          <section class="wrapper">
                    <div class="col-lg-15 ds" id="hide2"> 
               <h2>MANAGE DATA OF NAGARI KOTO GADANG</h2>  
               </div>
               
        <!-- Main content -->
        <section class="content">  
      <?php
      $p=$_GET['page'];
      $page="pages/".$p.".php";
      if(file_exists($page)){
        include($page);
      }elseif($p==""){
        include('pages/home.php');
      }else{
        include('pages/404.php');
      }
      ?>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->



      </section>

       
      <!--footer end-->
  </section>

    <script src="plugins/timepicker/bootstrap-timepicker.min.js" type="text/javascript"></script>
    <script class="include" type="text/javascript" src="../assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="../assets/js/jquery.scrollTo.min.js"></script>
    <script src="../assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="../assets/js/jquery.sparkline.js"></script>
    <script src="plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
    <!-- <script src="dist/js/app.min.js" type="text/javascript"></script> -->
    
    <!-- <script src="../assets/js/common-scripts.js"></script> -->
    
    <script type="text/javascript" src="../assets/js/gritter/js/jquery.gritter.js"></script>
    

        <script type="text/javascript">
      $(function () {
        $('#example1').dataTable({
          "bPaginate": true,
          "bLengthChange": true,
          "bFilter": true,
          "bSort": true,
          "bInfo": true,
          "bAutoWidth": false,
      "iDisplayLength": 25,
      "oLanguage": {
       "sInfo": "Menampilkan _START_ sampai _END_ dari _TOTAL_ data",
       "sLengthMenu": "_MENU_ data per halaman",
       "sSearch": "Search:"
      }
        });
        $(".timepicker").timepicker({
      showInputs: false,
      showMeridian: false,
      defaultTime: 'value'
        });
      });
    </script>


      </body>
      </html>
