var shiffmanChannelId = "UCvjgXvBlbQiydffZU7m1_aw";
var apiKey 				= "AIzaSyAInyAq_CBIJ0W7p_pqJlnA7dBHvC5FG8M";
let getPlaylistsStr = 'https://www.googleapis.com/youtube/v3/playlists?key=' + apiKey;
$(document).ready(function() {
  expandNav(); 
  getVideos();

});
var navButton = $('#menu-button'),
navMenu = $('#global-nav');
var navLinks = navMenu.find('a');

function expandNav() {
  if ((!document.body.classList.contains('active') && ($(document).width() >= 700))) {
		document.body.classList.add('active');
		document.getElementById('menu-button').classList.add('active');
		enableNavLinks();
		navLinks.eq(0).focus();
	}
}
function enableNavLinks() {
  navButton.attr('aria-label', 'Close navigation menu');
  navButton.attr('aria-expanded', 'true');
  navMenu.removeAttr('aria-hidden');
  navLinks.removeAttr('tabIndex');
}
function getVideos() {
  let searchStr = 'https://www.googleapis.com/youtube/v3/search?key=' + apiKey;
  $.get(searchStr, {
      part: 'snippet',
      channelId: shiffmanChannelId,
      order: 'date'
    })
    .done(function(data) {

      populateData(data);

    }, 'JSON')
    .error(function() {

      displayApiError();

    });

}
function displayApiError() {
  var message = '<p>We\'ve hit Youtube API quota for the day. Please <a href="https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw">visit our Youtube Channel</a>.</p>'
  $('#apiError').append(message);
}
function populateData(data) {

  var videos = data.items;
  console.log(videos);

  $.each(videos, function(index, video) {

    if (index < 3) {
			// console.log(index);
			// console.log(video.id);

      var snippet = video.snippet;

			// Playlists are sneaking in here for some reason?
      if (!video.id.playlistId) {

        var block = '<div class="video-entry row">' +
          '<div class="col-left">' +
          '<a href="http://youtube.com/video/' + video.id.videoId + '" target="_blank"><div class="thumbnail"><img src="' + snippet.thumbnails.medium.url + '"/></div></a>' +
          '</div>' +
          '<div class="col-right">' +
          '<div class="content">' +
          '<a href="http://youtube.com/video/' + video.id.videoId + '" target="_blank"><h2>' + snippet.title + '</h2></a>' +
          '<p>' +
          snippet.description +
          '</p>' +
          '<div class="actions">' +
          '<a href="http://youtube.com/video/' + video.id.videoId + '" target="_blank" class="body-link primary">Watch on Youtube</a>' +
          '</div>' +
          '</div>' +
          '</div>' +
          '</div>';

        $('.latest-videos').append(block);
      }

    }


  });

}
