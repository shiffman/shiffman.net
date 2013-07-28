<?php
// PHP Example, Week 1
// Daniel Shiffman
// Programming from A2Z, Spring 2006
?>
<html>

<head>
<title>Form Input / Output</title>

</head>

<!-- ============= CONTENT ============= -->

<?php

// Retrieve fields from form
$text = $_POST['stuff'];   // keep intact for text field

// DO YOUR MAGIC HERE!!!!


if (isset($text)) {
  // Replace carriage returns with '<br>', just for HTML formatting
  $pattern = '/\r/';
  $replacement = "<br>";
  $newtext = preg_replace($pattern, $replacement, $text);
}
?>

<?php
// If we've got new text, display it
if (isset($newtext)) {
  echo "<br>";
  echo $newtext;
}
?>

<!-- ============= FORM ============= -->
  <form action="inputoutput.php" method="post">
<p>
  Enter text:
</p>
<p>
  <textarea name="stuff" COLS=64 ROWS=12>
<?php
// Display the previous text or else use default Dickinson Poem
if (isset($text)) {
    echo $text;
} else {
?>
blah blah blah
<?php } ?>
</textarea>
<p>
  <input type="submit" name="submit" value="submit">
</p>
</form>

<!-- ============= END CONTENT ============= -->

<!-- ============= -->
</body>
</html>

