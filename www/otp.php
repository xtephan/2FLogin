<?php
session_start();


function hashxs($x, $input) {

	if ($x==0) {
		return md5($input);
	} else {
		return sha1($input); 
	}
	
}
 

function check_token($userOTP) {

	$token = $_SESSION['token'];
	$correctOTP = strtolower($_SESSION['token']);  
	
	for($i=0; $i<$token[3]; $i++) {
		$correctOTP = hashxs($token[5]%2,$correctOTP);
	}

	$finalOTP = strtoupper(substr($correctOTP, $token[4]*2, 6));

	if($userOTP == $finalOTP) {
		return 1;
	} else {
		return 0;	
	}
} 

?> 