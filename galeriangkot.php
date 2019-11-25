<!DOCTYPE html>
<html lang="en">
<head>
  <title>Koto Gadang Gallery</title>
  <link rel="stylesheet" type="text/css" href="asset/css/bootstrap.min.css">

    <!-- plugins -->     <script type="text/javascript"
src="https://maps.googleapis.com/maps/api/js?sensor=true"></script>     <link
rel="stylesheet" type="text/css" href="asset/css/plugins/font-
awesome.min.css"/>     <link rel="stylesheet" type="text/css"
href="asset/css/plugins/simple-line-icons.css"/>     <link rel="stylesheet"
type="text/css" href="asset/css/plugins/animate.min.css"/>     <link
rel="stylesheet" type="text/css"
href="asset/css/plugins/fullcalendar.min.css"/> <link
href="asset/css/style.css" rel="stylesheet"> <link rel="shortcut icon"
href="asset/img/logo.png"> </head>

<body>       <!-- start: Header --> <nav class="navbar navbar-default header
navbar-fixed-top">   <div class="col-md-12 nav-wrapper">     <div class
="navbar-header" style="width:100%;">       <ul class="nav navbar-nav navbar-
right user-nav">         <li class="user-name"><span>KOTO GADANG GALLERY</span></li>           <li class="dropdown avatar-dropdown">
<img src="asset/img/logo.png" class="img-circle avatar" alt="user name" aria-
haspopup="true" aria-expanded="true"/>         </li>       </ul>     </div>
</div> </nav>

<div class="container-fluid" style="margin-top:90px">     <div class="col-
md-12" style="padding:0px;">       <div class="col-md-12 padding-0">
<!-- Map md-8 -->         <div class="col-md-5 padding-2">           <div
class="col-md-12 padding-0">             <div class="panel box-v1">
<div class="panel-heading bg-white border-none">                 <div class
="col-md-6 col-sm-6 col-xs-6 text-left padding-0">                   <h4 class
="text-left">Foto</h4>                 </div>                 <div class="col-
md-6 col-sm-6 col-xs-6 text-right">                   <h4>
<span class="icon-picture icons icon text-right"></span>
</h4>                 </div>               </div>               <div class
="panel-body text-center" style="height:450px">                   <div
class="map" style="height:100%">
                        
                   <!-- Content START -->                    <div id
="slideshow-mudah" class="carousel slide" data-ride="carousel">
<!-- Indicators, Ini adalah Tombol BULET BULET dibawah. item ini dapat dihapus
jika tidak diperlukan -->                     <ol class="carousel-indicators">
<li data-target="#slideshow-mudah" data-slide-to="0" active></li>
<li data-target="#slideshow-mudah" data-slide-to="1"></li>
<li data-target="#slideshow-mudah" data-slide-to="2"></li>
</ol>                     <div class="carousel-inner">
<?php                       require 'connect.php';

                      $id = $_GET["idgallery"];

                         $querysearch    ="SELECT id_umkm, gallery_umkm from umkm_gallery where id_umkm='$id'";                       

                      $index = 1;
                      $hasil=mysqli_query($conn, $querysearch);
                      while($baris = mysqli_fetch_assoc($hasil))
                      {
                        if((($baris['gallery_umkm']=='')||($baris['gallery_umkm']=='-'))&&(($baris['gallery_umkm']=='')||($baris['gallery_umkm']=='-')))
                        {
                          echo "<div class='item active'><img src='img/noimage.jpg' ></div><div class=carousel-caption'></div>";
                        }
                        else
                        {
                          echo "<div class='item active'><img src=".$baris['gallery_umkm']." ></div><div class=carousel-caption'></div><div class='item'><img src=".$baris['foto2']." ></div><div class=carousel-caption'></div>";
                        }                              
                      } 

                      ?> 
                    </div>
                    <a class="left carousel-control" href="#slideshow-mudah" data-slide="prev">
                      <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#slideshow-mudah" data-slide="next">
                      <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>






<script src="asset/js/jquery.min.js"></script>
<script src="asset/js/jquery.ui.min.js"></script>
<script src="asset/js/bootstrap.min.js"></script>
<script src="asset/js/plugins/moment.min.js"></script>
<script src="asset/js/plugins/fullcalendar.min.js"></script>
<script src="asset/js/plugins/jquery.nicescroll.js"></script>
<script src="asset/js/plugins/chart.min.js"></script>
<script src="asset/js/main.js"></script>
  </body>
</html>