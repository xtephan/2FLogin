<?php
session_start();
include('otp.php');


$link = mysql_connect('localhost', 'root', 'jdq511jd');
if (!$link) {
    die('Could not connect: ' . mysql_error());
}


mysql_select_db("otp");

$q="SELECT * FROM user WHERE username='" . $_POST['username'] . "'";

$result = mysql_query($q);

$row = mysql_fetch_array($result); 


if ( $row["password"] == $_POST['password'] && check_token($_POST['otp']) ) {
	echo "<h1>Good morning mister OBAMA!<br/><br/><br/>Click here in order to bomb Irak</h1>";
} else { 
	echo "<h1>ERROR!! A cleanup team will be send to see if you are who you say you are</h1>";
}


mysql_close($link);
?>