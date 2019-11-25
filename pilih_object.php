<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script language="javascript">
   
 function cekpegawai()
 {
    if (cek1.checked && cek2.checked && cek3.checked && cek4.checked && cek5.checked){
    window.onunload = function (e) {  
     opener.CallParentfunction();
     opener.CallParentfunction2();
     opener.CallParentfunction3();
     opener.CallParentfunction4();
     opener.CallParentfunction5();  
     }  
  }
      else if (cek1.checked && cek2.checked && cek3.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction3();
      opener.CallParentfunction4();  
     }  
      }
      else if (cek1.checked && cek2.checked && cek3.checked && cek5.checked){
        window.onunload = function (e) {  
     opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction3();
      opener.CallParentfunction5();  
     }  
      }
      else if (cek1.checked && cek2.checked && cek4.checked && cek5.checked){
        window.onunload = function (e) {  
     opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction4();
      opener.CallParentfunction5();    
     }  
      }
      else if(cek1.checked && cek3.checked && cek4.checked && cek5.checked){
        window.onunload = function (e) {  
     opener.CallParentfunction();
      opener.CallParentfunction3();
      opener.CallParentfunction4();
      opener.CallParentfunction5();  
     }  
      }
      else if(cek2.checked && cek3.checked && cek4.checked && cek5.checked){
        window.onunload = function (e) {  
     opener.CallParentfunction2();
      opener.CallParentfunction3();
      opener.CallParentfunction4();
      opener.CallParentfunction5();  
     }  
      }
      else if(cek1.checked && cek2.checked && cek3.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction3();
    }  
      }
      else if(cek1.checked && cek2.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction4();
    }  
      }
      else if(cek1.checked && cek2.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction2();
      opener.CallParentfunction5();
    }  
      }
      else if(cek1.checked && cek3.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction3();
      opener.CallParentfunction4();
    }  
      }
      else if(cek1.checked && cek3.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction3();
      opener.CallParentfunction5();
    }  
      }
      else if(cek2.checked && cek3.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction2();
      opener.CallParentfunction3();
      opener.CallParentfunction4();
    }  
      }
      else if(cek2.checked && cek3.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction3();
      opener.CallParentfunction5();
    }  
      }
      else if(cek1.checked && cek2.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction2();
     }  
    }
      else if(cek1.checked && cek3.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction3();
     }  
    }
      else if(cek1.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction4();
     }  
    }
      else if(cek1.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
      opener.CallParentfunction5();
     }  
    }
      else if(cek2.checked && cek3.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction2();
      opener.CallParentfunction3();
     }  
    }
      else if(cek2.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction2();
      opener.CallParentfunction4();
     }  
    }
      else if(cek2.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction2();
      opener.CallParentfunction5();
     }  
    }
      else if(cek3.checked && cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction3();
      opener.CallParentfunction4();
     }  
    }
      else if(cek3.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction3();
      opener.CallParentfunction5();
     }  
    }
      else if(cek4.checked && cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction4();
      opener.CallParentfunction5();
     }  
    }
      else if(cek1.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction();
     }  
    }
      else if(cek2.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction2();
     }  
    }
      else if(cek3.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction3();
     }  
    }
      else if(cek4.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction4();
     }  
    }
      else if(cek5.checked){
        window.onunload = function (e) {  
      opener.CallParentfunction5();
     }  
    }
 }
</script>
</head>

<body>
Choose The Object<br />
<?php // jika ingin dinamis datanya, query ke database while { ?>
<input type="checkbox" name="cek1" id="cek1" value="1" />
<a>UMKM</a>
<br />
<input type="checkbox" name="cek2" id="cek2" />
<a>Mosque</a>
<br />
<input type="checkbox" name="cek3" id="cek3" />
<a >Culinary</a>
<br />
<input type="checkbox" name="cek4" id="cek4" />
<a >Tourism</a>
<br />
<input type="checkbox" name="cek5" id="cek5" />
<a >Great Charater</a>
<br />
<input type="button" name="button3" id="button3" value="Ok" onclick="cekpegawai(); window.close(); " />
</body>
</html>