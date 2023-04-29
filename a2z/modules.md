
The scenarios above demonstrate how the code works, but don't really produce any useful results.  Let's take a scenario where you have a server that analyzes a massive corpus of text which you want to visualize from the browser.  In previous weeks, we looked at analyzing text right in the client JavaScript code itself, but this was really only well-suited for smaller datasets.  If the server handles it, the client can be saved a lot of computation time and simple "query" the results from the server and visualize them.

Before I get to the actual server code, one thing that may prove useful is looking at how node "modules" work.  In client-side JavaScript, if you wanted to put some code in another JS file, you simply made something called, say, `file.js`, typed some code and included a reference in the HTML file.  With a node app, there is no HTML file gluing everything together.  Instead the "main" JS file (maybe called "server.js") has to refer to another file using `require()`.  For example, the following code assumes a file called `utilities.js`.

{% highlight javascript %}
var utilities = require('./utilities');
{% endhighlight %}

The objects and functions you want to have access to must be included inside something called `module.exports`.

{% highlight javascript %}
module.exports = {
  Something: function() {
    // Stuff you want to use
  }
}
{% endhighlight %}
