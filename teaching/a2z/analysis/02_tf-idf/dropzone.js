
// files is just in global space right now
var files;

function setupDropZone() {

  // Check for the various File API support.
  if (window.File && window.FileReader && window.FileList && window.Blob) {
    console.log('Great success! All the File APIs are supported');
  } else {
    alert('The File APIs are not fully supported in this browser.');
  }

  // <div id="drop_zone">Drop files here</div>
  // Make a div to drag a file on
  var dropZone = createDiv('Drop files here');
  dropZone.id('drop_zone');
  // Add some events
  dropZone.elt.addEventListener('dragover', handleDragOver, false);
  dropZone.elt.addEventListener('drop', handleFileSelect, false);
  dropZone.elt.addEventListener('dragleave', handleDragLeave, false);
  
  // A list of files
  list = createElement('ol','');
  
  // When you drag a file on top
  function handleDragOver(evt) {
    // Stop the default browser behavior
    evt.stopPropagation();
    evt.preventDefault();
    dropZone.style('background','#AAAAAA');
  }
  
  // If the mosue leaves
  function handleDragLeave(evt) {
    evt.stopPropagation();
    evt.preventDefault();
  }
  
  // If you drop the file
  function handleFileSelect(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    dropZone.style('background','');

    // A FileList
    files = evt.dataTransfer.files;

    // Show some properties
    for (var i = 0, f; f = files[i]; i++) {
      var link = createA('#',f.name);
      link.mousePressed(makeFileHandler(f));
      var file = createElement('li','');
      link.parent(file);
      file.parent(list);
    }
  }
}


