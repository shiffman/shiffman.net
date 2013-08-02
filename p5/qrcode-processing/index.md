---
title: Processing QRCode Library
author: Daniel
layout: page
dsq_thread_id:
  - 249553949
pvc_views:
  - 22910
---
<p><img src="http://qrcode.kaywa.com/img.php?s=8&#038;d=I%20love%20Processing%21%21%21" alt="qrcode"  /></p>
<p>Welcome to the QRCode Processing library page!</p>
<p><a href="http://www.shiffman.net/p5/libraries/qrcode/qrcodeprocessing.zip">Download the Processing QRCode library</a> (includes source and example)<br />
(Special thanks to <a href="http://www.tigoe.net/intro.shtml">Tom Igoe</a> for the original code that I started with.)</p>
<p>This library is really nothing other than a bridge to using the <a href="http://qrcode.sourceforge.jp/">Open Source QR Code library</a> with Processing.  From the QR Code web site:  &#8220;QR Code is a two-dimensional barcode, used widely in Japan. The advantage of QR Code from well-known barcode is larger data capacity (more than 100 bytes, typically) and error correction.&#8221;</p>
<p>QRCode images can be generated at <a href="http://qrcode.kaywa.com/">http://qrcode.kaywa.com/</a>.</p>
<p>Here is a basic summary of how to use the library:</p>
<p>Download and install the library, and import it like so:</p>
<pre lang="java">
import qrcodeprocessing.*;
</pre>
<p>Create and initialize a decoder object:</p>
<pre lang="java">
Decoder decoder;

void setup() {
  decoder = new Decoder(this);
}
</pre>
<p>You can ask the Decoder object to decode any image:</p>
<pre lang="java">
PImage img = loadImage("qrcode.jpg");
decoder.decodeImage(img);
</pre>
<p>It runs in a separate thread and whenever it is finished, an event is triggered via the decoderEvent() method:</p>
<pre lang="java">
void decoderEvent(Decoder decoder) {
  String statusMsg = decoder.getDecodedString(); 
  println(statusMsg);
}
</pre>
<p>Ok, have fun!!</p>
