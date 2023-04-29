---
title: Deploying Node Bot to Heroku
layout: a2z-post
permalink: /a2z/bot-heroku/
---

<iframe width="525" height="300" src="https://www.youtube.com/embed/DwWPunpypNA?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>

## Notes on Deploying to Heroku

* [Heroku Getting Started Guide](https://devcenter.heroku.com/articles/getting-started-with-nodejs#introduction)
* [How to Deploy a Node.js App to Heroku](https://scotch.io/tutorials/how-to-deploy-a-node-js-app-to-heroku)

### Sign up and create an app

* [Sign up for Heroku](https://heroku.com/)
* [Install Heroku Toolbelt](https://toolbelt.heroku.com/)

### Create the app

* Go to your [dashboard](https://dashboard.heroku.com/apps)
* Select "Create new app" (top right)
* Name your app something (only letters, numbers, and dashes)
* Click "Create App"

### Push your code to heroku

* Login with toolbelt:

{% highlight text %}
$ heroku login
{% endhighlight %}

* Navigate terminal to your project directory.
* If your project is not already a git repository (otherwise skip this step):

{% highlight text %}
$ git init
{% endhighlight %}

* Add heroku as a remote:

{% highlight text %}
$ heroku git:remote -a your-app-name
{% endhighlight %}

* Commit your code (it it's not already).

{% highlight text %}
$ git add .
$ git commit -am "commiting the code"
{% endhighlight %}

* Send to heroku!

{% highlight text %}
$ git push heroku master
{% endhighlight %}

You should then see a whole lot of stuff telling you about how your app is starting and running!

### Was your app a bot?

If your app was a bot, you need an additional step.  Since it's not a web server, you have to tell heroku that this app is a ["worker"](https://devcenter.heroku.com/articles/background-jobs-queueing) app.  This is done with a ["Procfile"](https://devcenter.heroku.com/articles/procfile).  This is a file called exactly "Procfile" in your node directory with a single line:

{% highlight text %}
worker: node bot.js
{% endhighlight %}

[Here's an example](https://github.com/shiffman/A2Z-F15/blob/gh-pages/week9/08_twitter_bot/Procfile).

You then need to login to your dashboard and navigate to the app.  The "worker" dyno must be enabled rather than the default web one (`npm start`).  The app's dashboard should look like the following:

![dynos](/a2z/images/dynos.png)

Depending on the order in which you have done things, you might need to restart your app.

{% highlight text %}
$ heroku restart
{% endhighlight %}

### Heroku config settings

Something else you can do with heroku is set variables specific to your app.  This is convenient for, say, API keys.  In other words if you say:

{% highlight text %}
$ heroku config:set api_key=99999999999999
{% endhighlight %}

In your code, you can then have:

{% highlight javascript %}
var key = process.env.api_key;
{% endhighlight %}

More about [config variables in Heroku's help pages](https://devcenter.heroku.com/articles/config-vars).
