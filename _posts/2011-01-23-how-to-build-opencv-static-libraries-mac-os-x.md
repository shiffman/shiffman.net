---
title: How to Build OpenCV Static Libraries Mac OS X (10.5 compatible!)
author: Daniel
layout: post
dsq_thread_id:
  - 249569486
pvc_views:
  - 8367
categories:
  - OpenCV
tags:
  - make
  - opencv
  - static libraries
  - xcode
---
<p>ITP student Patrick Hebron and I have been working on a new Processing video library built on top of OpenCV (with the goal of no vdig on windows and better movie playback support all around.)  This is all a bit obscure, but it was such a struggle to build stand-alone static libraries for OpenCV on Mac OS X (backwards compatible to 10.5) that we could link to via JNI that I thought I would document the process in case this comes up for anyone else in the future.</p>
<p>First of all, what is a static library and why should I care?</p>
<p>Some reading: <a href="http://en.wikipedia.org/wiki/Library_(computing)">http://en.wikipedia.org/wiki/Library_(computing)</a></p>
<p>Let&#8217;s say you are familiar with mac ports or homebrew or you&#8217;ve read some cryptic instructions online about how to download an open source project from a repository and built it via the command line.  Most likely, you built that project and installed some files to usr/lib or opt/local/lib, etc.  Most likely these files were &#8220;dynamic&#8221; or &#8220;shared&#8221; library files, meaning they can be used by various applications on your computer.  This is a good thing and saves you from having multiple copies of libraries, one for each application that uses say, openCV or FFMPEG or something like that.</p>
<p>A static library is the opposite and cannot be shared. It is a library that is used for building a target application and essentially becomes embedded into that application.  If you are an openFrameworks or Cinder user, you may have encountered static libraries. They are the .a files that are referenced by your xcode project.  For example:</p>
<p><img src="http://www.shiffman.net/images/opencv/dota.png"/></p>
<p>In the case of building a Processing library (or compiling an OF or Cinder application), we&#8217;re looking to distribute something that does not require a separate install.  We don&#8217;t want to say, here is some code you can use but before you can use it, take a whole day to figure out how to build opencv from the source and install it to this strange directory on your machine.  </p>
<p>So, if you need to build OpenCV from source to static libraries to link from an xcode project, here is how you do it.</p>
<p><b>Step 1. Get the OpenCV source</b></p>
<p>You can download it from here:</p>
<p>http://sourceforge.net/projects/opencvlibrary/</p>
<p>or use the command line to get it via SVN:</p>
{% highlight java %}
cd ~/wherever/you/want/to/put/it
svn co https://code.ros.org/svn/opencv/trunk
</pre>
<p><b>Step 2. Use the CMake GUI to create &#8220;make&#8221; files</b></p>
<p>You can do all this with unix commands via terminal, but I found the Cmake Gui to be quite useful.  You can download it here:</p>
<p><a href="http://www.cmake.org/cmake/resources/software.html">http://www.cmake.org/cmake/resources/software.html</a></p>
<p>When you run CMake, you have to select two directories.  &#8220;Where is the source code&#8221; &#8212; browse to the directory where you downloaded OpenCV.  &#8220;Where to build the binaries&#8221; &#8212; pick a directory where you want to build the shared libraries.  Here&#8217;s what it looks like on my machine:</p>
<p><img src="http://www.shiffman.net/images/opencv/cmake1.png"/></p>
<p>Now press the &#8220;configure&#8221; button.</p>
<p>You&#8217;ll then be asked to specify a generator.  If you prefer to use an IDE, select &#8220;Xcode.&#8221;  If you&#8217;d like to build it via terminal, select &#8220;Unix Make files.&#8221;  For the purposes of this tutorial, I&#8217;ll pick Xcode.</p>
<p><img src="http://www.shiffman.net/images/opencv/cmake2.png"/></p>
<p>Once you hit ok (and wait a minute or so), all of the build options will suddenly appear in the center of the CMake window.  Here&#8217;s where things get a little tricky and what you need to select can be an &#8220;it depends&#8221; on what exactly you are doing this for.  Here are the settings I am using:</p>
<ul>
<li>Uncheck &#8220;BUILD_NEW_PYTHON_SUPPORT&#8221; (I don&#8217;t need python)</li>
<li>Uncheck &#8220;BUILD_SHARED_LIBRARIES&#8221; (This is important, this will cause it to build the static libraries instead of shared!)</li>
<li>Uncheck &#8220;BUILD_TESTS&#8221; (Don&#8217;t need these)</li>
<li>Add &#8220;I386&#8243; to CMAKE_OSX_ARCHITECTURES.  I&#8217;m looking for these static libraries to be backwards compatible to 32-bit systems and Leopard (and Processing runs in 32-bit only) so this is the architecture I&#8217;m using, but you might choose some different.</li>
<li>Change &#8220;CMAKE_OSX_SYSROOT&#8221; to /Developer/SDKs/MacOSX10.5.sdk.  Same thing as previous item.</li>
<li>Uncheck &#8220;WITH_FFMPEG&#8221;.  You&#8217;ll get errors unless you have FFMPEG on your system</li>
<li>Uncheck &#8220;WITH_D1394&#8243;.  Same thing, you&#8217;ll get errors unless you have D1394 on your system.</li>
<li>Check &#8220;WITH_QUICKTIME&#8221;.  You may want to leave this out.  For me, I need to use QUICKTIME (instead of the newer QTKit) because of a weird threading error that happens with Java.  I&#8217;m hoping to eventually sort this threading error out so that we can use the QTKit framework.</li>
</ul>
<p><img src="http://www.shiffman.net/images/opencv/cmake3.png"/></p>
<p>After you have finished your settings, press &#8220;Configure&#8221; again.  Then press &#8220;Generate&#8221;.</p>
<p><b>Step 3. Build the static libs!</b></p>
<p>If you picked &#8220;Xcode&#8221;, you&#8217;ll now have an XCode project in your directory that you can open!</p>
<p><img src="http://www.shiffman.net/images/opencv/finder.png"/></p>
<p>If you used Unix make, you can then browse to the directory via terminal and type &#8220;make&#8221;!</p>
<p>Once you build the project, you&#8217;ll see you have the following static libs in these directorys:</p>
<p>/lib/libopencv_calib3d.a<br />
/lib/libopencv_flann.a<br />
/lib/libopencv_imgproc.a<br />
/lib/libopencv_video.a<br />
/lib/libopencv_contrib.a<br />
/lib/libopencv_gpu.a<br />
/lib/libopencv_legacy.a<br />
/lib/libopencv_core.a<br />
/lib/libopencv_haartraining_engine.a<br />
/lib/libopencv_ml.a<br />
/lib/libopencv_features2d.a<br />
/lib/libopencv_highgui.a<br />
/lib/libopencv_objdetect.a<br />
/3rdparty/lib/liblibjasper.a<br />
/3rdparty/lib/liblibjpeg.a<br />
/3rdparty/lib/liblibpng.a<br />
/3rdparty/lib/liblibtiff.a<br />
/3rdparty/lib/libopencv_lapack.a<br />
/3rdparty/lib/libzlib.a</p>
<p>Here are some good additional references I used:</p>
<p>For building OpenCV 1.0<br />
<a href="http://ildan.blogspot.com/2009/11/creating-universal-static-opencv.html">http://ildan.blogspot.com/2009/11/creating-universal-static-opencv.html</a></p>
<p>Some backwards compatibility tips:<br />
<a href="http://cocoawithlove.com/2009/09/building-for-earlier-os-versions-in.html">http://cocoawithlove.com/2009/09/building-for-earlier-os-versions-in.html</a></p>
<p>OF Forum discussion:<br />
<a href="http://www.openframeworks.cc/forum/viewtopic.php?f=10&#038;t=4943">http://www.openframeworks.cc/forum/viewtopic.php?f=10&#038;t=4943</a></p>
<p>Cinder forum discussion:<br />
<a href="http://forum.libcinder.org/topic/opencv-status">http://forum.libcinder.org/topic/opencv-status</a></p>
<p>Questions, comments, things I missed?  Let me know!</p>
