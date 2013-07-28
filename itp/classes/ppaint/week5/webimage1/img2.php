<?php
 header("Content-Type: image/jpeg");
 $url = $_GET["url"];  
 readfile($url) /* if fopen_wrappers is enabled */
?>

