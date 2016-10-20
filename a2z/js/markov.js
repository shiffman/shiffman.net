
var json;
var possibilities = [];

function preload() {
  json = loadJSON('/a2z/js/letterfreq.json');
}

function setup() {

  console.log('JS happening!');

  var ngramarea = select('#ngramarea');
  var order = select('#order');
  var ngrambutton = select('#ngrambutton');
  var ngramsresult = select('#ngramsresult');
  ngrambutton.mousePressed(function() {
    var result = nGrams(ngramarea.value(),Number(order.value()));
    ngramsresult.html(result.join('<br/>'));
  });

  var letters = 'abcdefghijklmnopqrstuvwxyz';
  for (var i = 0; i < letters.length; i++) {
    var prob = json[letters[i]];
    var n = floor(prob*1000);
    for (var j = 0; j < n; j++) {
      possibilities.push(letters[i]);
    }
  }

  for (var i = 0; i < 10000; i++) {
    possibilities.push(' ');
  }

  setInterval(function() {
   var possible = 'abcdefghijklmnopqrstuvwxyz';
   possible += possible.toUpperCase();
   possible += ' .';
   var randomit = select('#randomit');
   var tobe = '';
   for (var i = 0; i < 18; i++) {
     var r = possible.charAt(floor(random(0,possible.length)));
     tobe += r;
   }
   randomit.html(tobe);
  },100);

  setInterval(function() {
   var randomfreq = select('#randomfreq');
   var tobe = '';
   for (var i = 0; i < 18; i++) {
     var r = possibilities[floor(random(0,possibilities.length))];
     tobe += r;
   }
   randomfreq.html(tobe);
  },100);

  setInterval(function() {
    var ngramtest = select('#ngramtest');
    var ngrams = {
      't': ['o', '_', 'o'],
      'o': ['_', 'r', 't', '_']
    };

    var txt = 't';
    var index = 0;

    for (var i = 0; i < 10; i++) {
      var possible = ngrams[txt.charAt(index)];
      if (!possible) {
        break;
      }
      var r = floor(random(possible.length));
      var next = possible[r];
      txt += next;
      index++;
    }
    ngramtest.html(txt);
  },100);
}


function nGrams(sentence, n) {
  // Split sentence up into words
  var words = sentence.split(/\W+/);
  // Total number of n-grams we will have
  var total = words.length - n;
  var grams = [];
  // Loop through and create all sequences
  for(var i = 0; i <= total; i++) {
    var seq = '';
    for (var j = i; j < i + n; j++) {
      seq += words[j] + ' ';
    }
    // Add to array
    grams.push(seq);
  }
  return grams;
}
