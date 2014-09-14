// Scramble what the user enters into a text field

// The scrambled text
var inputElt;
var regexElt;
var outputElt;

var globalElt;
var caseElt;

var lorum = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.';


function setup() {
  noCanvas();
  
  // See how we can make a div
  inputElt = getElement('input');
  regexElt = getElement('regex');
  outputElt = getElement('output');
  var button = getElement('button');
  button.mousePressed(process);
  globalElt = getElement('global');
  caseElt = getElement('case');
}

function process() {
  
  var flags = '';
  if (globalElt.elt.checked) flags += 'g';
  if (caseElt.elt.checked)   flags += 'i';
  
  var regex = new RegExp(regexElt.value(),flags);
  console.log(regex);  
  var input = inputElt.value();
  var results = input.match(regex);
  console.log(results);

  outputElt.html(results);

}
