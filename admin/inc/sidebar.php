<aside class="main-sidebar">
<section class="sidebar">

      <?php
      if (isset($_GET['page'])) {
        $page=$_GET['page'];
      } else {
        $page="home";        
      }


      ?>


<p class="centered"><a href="#"><img src="../assets/img/ui-zac.jpg" class="img-circle" width="150" height="120"></a></p>
              <h5 class="centered">Admin</h5>

                  <li class="sub-menu">
                      <a class="active" href="../">
                          <i class=""></i>
                          <span>User Access</span>
                      </a>
                  </li>
                  <!-- <div style="display:none;" id="tampilik"> -->
          <?php
            if($page == "industry"){
          ?>
                  
			           	<li class="sub-menu">
                      <a class="active" href="?page=jenis">
                          <i class=""></i>
                          <span>Type of UMKM</span>
                      </a>
                  </li>
                  <!-- </div> -->
          <?php
            }
            if($page == "souvenirs"){
          ?>
                 <!--  <div style="display:none;" id="tampilsouv"> -->
              <h6 class="centered" style="color: #f7d976;">MASJID</h6>
                 <li class="sub-menu">
                      <a class="active" href="?page=souvenirs">
                          <i class=""></i>
                          <span>Data of Masjid</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a class="active" href="?page=jenissouvenirs">
                          <i class=""></i>
                          <span>Type of Masjid</span>
                      </a>
                  </li>
                  <!-- </div> -->
          <?php
            }
            if($page == "culinary"){
          ?>
                  <!-- <div style="display:none;" id="tampilkul"> -->
                 
          <?php
            }
         
      if (isset($_GET['page'])) {

      ?>
                 <li class="sub-menu">
                      <a class="active" href="./">
                          <i class="fa fa-arrow-left"></i>
                          <span>Back</span>
                      </a>
                  </li>

          <?php
            }
         
      ?>

        <!-- <li class="sub-menu">
                      <a class="active" href="?page=jenis">
                          <i class="fa fa-dashboard"></i>
                          <span>Type of Culinary</span>
                      </a>
                  </li> -->
                    <!-- </div> -->


                  </section>
</aside>