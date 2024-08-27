---
title: Database as Service - Firebase
layout: a2z-post
permalink: /a2z/firebase/
---

### Database as Service

<iframe width="560" height="315" src="https://www.youtube.com/embed/JrHT1iqSrAQ?list=PLRqwX-V7Uu6agS82Le9lLCBbeaW8inATT" frameborder="0" allowfullscreen></iframe>

In [Building an API with node](/a2z/node-api), I discussed strategies for storing data associated with a web application from a local JSOn file to creating a custom database on your own server.  Another strategy is to use a "database as service" to store your data.  On this page, I'll explore the service [Firebase](https://firebase.google.com) which allows you to send and retrieve data from your client-side or server-side JavaScript code.  Firebase is a commercial service (from Google), but it has [a pretty decent free plan](https://firebase.google.com/pricing/).

### Examples:
* [Saving web form data](https://shiffman.github.io/A2Z-F16/week9-firebase/01_firebase_form/) (from client) -- [source](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-firebase/01_firebase_form)
* [Saving coordinates for drawings](https://shiffman.github.io/A2Z-F16/week9-firebase/02_firebase_drawing) (from client) -- [source](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-firebase/02_firebase_drawing)
* [Saving data for mad libs](https://shiffman.github.io/A2Z-F16/week9-firebase/03_firebase_madlibs) (from client) -- [source](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-firebase/03_firebase_madlibs)
* [Creating permalinks for generated text](https://shiffman.github.io/A2Z-F16/week9-firebase/04_firebase_markov) (from client) -- [source](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-firebase/04_firebase_markov)
* [Saving and retrieving with node](https://github.com/shiffman/A2Z-F16/blob/gh-pages/week9-firebase/05_node_firebase/server.js) (from server)

<iframe width="560" height="315" src="https://www.youtube.com/embed/7lEU1UEw3YI?list=PLRqwX-V7Uu6agS82Le9lLCBbeaW8inATT" frameborder="0" allowfullscreen></iframe>

### Important links:
* [Firebase Console](https://console.firebase.google.com/)
* [Reading and Writing Data from firebase documentation](https://firebase.google.com/docs/database/web/read-and-write)

There are a few things to note when working with Firebase.   Here are some steps to get you started.

1. Create an account with the [Firebase console](https://console.firebase.google.com/).
2. Select "Create a new project."
3. Once you have created a project navigate to "Add Firebase to your web app."

There you will see all the code you need to initialize Firebase to your JavaScript code.

![firebase](/a2z/images/firebase1.png)

Before you can start adding code, for my examples you'll also need to make the data public.  This is certainly [a security issue](https://firebase.google.com/docs/database/security/quickstart) and I would not recommend this for any projects that require private data.  Navigate to the "RULES" tab in the firebase console and add the following:

![firebase](/a2z/images/firebaserules.png)

{% highlight json %}
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
{% endhighlight %}

Now you can go ahead and start adding code to work with Firebase.  If you are working with a separate JS file for your code (like in my examples), make sure you add a reference to the firebase library in `index.html`.

{% highlight html %}
<script src="https://www.gstatic.com/firebasejs/3.5.2/firebase.js"></script>
{% endhighlight %}

The copy paste the initialize code and add to `setup()` (or whatever event you are using for when the page is loaded):

{% highlight javascript %}
function setup() {
  var config = {
    apiKey: "your_key",
    authDomain: "your-project.firebaseapp.com",
    databaseURL: "https://your-project.firebaseio.com",
    storageBucket: "your-project.appspot.com",
    messagingSenderId: "your_sender_id"
  };
  firebase.initializeApp(config);
}
{% endhighlight %}

You then need to create a "database" instance from the firebase object.

{% highlight javascript %}
var database = firebase.database();
{% endhighlight %}


Everything you send to Firebase is ultimately saved as a JavaScript object and everything is saved in the database as a "path" to that object.  Paths are specified using the `ref()` function.  So let's say you want to store an inventory of fruit.  You might create a path to "fruits":

{% highlight javascript %}
var fruits = database.ref('fruits');
{% endhighlight %}

And then you can create an object with the data you want to save:

{% highlight javascript %}
var data = {
  name: 'pear',
  count: 7
}
{% endhighlight %}

There are a [variety of ways you can add data to Firebase](https://firebase.google.com/docs/database/web/read-and-write), but in this case the easiest way is the `push()` function.  In other words, you want to push (aka "add") a new fruit entry to the database.

{% highlight javascript %}
fruits.push(data);
{% endhighlight %}

This could be written as one line of code:

{% highlight javascript %}
database.ref('fruits').push(data);
{% endhighlight %}

You can also get a callback.

{% highlight javascript %}
fruits.push(data, finished);
function finished(error) {
  if (error) {
    console.log('ooops');
  } else {
    console.log('data saved!');
  }
}
{% endhighlight %}

You can then navigate to the firebase console to view the data.

![firebase](/a2z/images/firebase2.png)

### Retrieving Data

<iframe width="560" height="315" src="https://www.youtube.com/embed/NcewaPfFR6Y?list=PLRqwX-V7Uu6agS82Le9lLCBbeaW8inATT" frameborder="0" allowfullscreen></iframe>

The flip side of this is asking Firebase for data.  An easy thing to do is assign a callback that returns all of the data for a specific reference (i.e. path).

{% highlight javascript %}
var ref = database.ref("fruits");
ref.on("value", gotData, errData);
{% endhighlight %}

The "value" event is triggered when changes are made to the database.  You can then read all the data in a `gotData()` callback (and see errors in a separate callback.)  The `val()` function returns an object with everything that lives as part of the "fruits" reference.

{% highlight javascript %}
function gotData(data) {
  var fruits = data.val();
  // Grab the keys to iterate over the object
  var keys = Object.keys(fruits);

  for (var i = 0; i < keys.length; i++) {
    var key = keys[i];
    // Look at each fruit object!
    var fruit = fruits[key];
  }
}
{% endhighlight %}

If you know the id of a specific fruit you could set a callback for just that one:

{% highlight javascript %}
var id = '-KVKnwa-MsPXzNbNHdmK';
var ref = database.ref("fruits/" + id);
ref.on("value", gotOne, errData);

function gotOne(data) {
  var fruit = data.val();
}
{% endhighlight %}

<iframe width="560" height="315" src="https://www.youtube.com/embed/RUSvMxxm_Jo?list=PLRqwX-V7Uu6agS82Le9lLCBbeaW8inATT" frameborder="0" allowfullscreen></iframe>
