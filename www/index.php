<?php
session_start();

function gen_token() {

		$pos_digit = '123456789';
		$pos_char  = 'QWERTYUIOPASDFGHJKLZXCVBNM'; 
      $code = '';
      $i = 0;
      while ($i < 3) {
         $code .= substr($pos_char, mt_rand(0, strlen($pos_char)-1), 1);
         $i++;
      }
 
      $i = 0;
      while ($i < 3) {
         $code .= substr($pos_digit, mt_rand(0, strlen($pos_digit)-1), 1);
         $i++;
      } 
 		
 		$_SESSION['token'] = $code;

      return $code;
}

?>
<html> 
  <head>
    <title>CIA Login Terminal</title> 
<style type="text/css">
div { color: white; position: absolute; top: 200px; left: 530px;}
td { color: white; font-size: 20px; }
</style>    
    
  </head>
  <body style=" background-image:url('img/cia1.jpg'); ">
  <form action="verify.php" method="post">
    <div>
    	<table border=0>
      <tr><td>Username:</td><td><input type="text" name="username" checked="true" /></td><tr/>
      
      <tr><td>Password:</td><td><input type="password" name="password" checked="true" /></td><tr/>
      
      <tr><td> Token for <?php echo gen_token(); ?>:</td><td>
      <input type="text" name="otp" checked="true" /></td><tr/>
      
      </table>
      <br /><br /><input type="submit" value="Login" /> 
   </div>
   </form> 
  </body>
</html>
