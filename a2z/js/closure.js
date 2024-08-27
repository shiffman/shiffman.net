

function setup() {
  var p1 = select('#count1');
  var p2 = select('#count2');
  var p3 = select('#count3');
  animate(p1);
  animate(p2, 100);
  animate(p3, 50);

}

function animate(elt, howlong) {

  var count = 0;

  function increment() {
    count = count + 1;
    elt.html(count);
  }

  setInterval(increment, howlong);
}
