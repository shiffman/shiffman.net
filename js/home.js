var shiffmanChannelId = "UCvjgXvBlbQiydffZU7m1_aw";
var apiKey 				= "AIzaSyAInyAq_CBIJ0W7p_pqJlnA7dBHvC5FG8M";
let getPlaylistsStr = 'https://www.googleapis.com/youtube/v3/playlists?key=' + apiKey;
var RightAside = {};
var ESCAPE_CODE = 27;
$(document).ready(function() {
  expandNav(); 
  getVideos();

  var HomeRightAside = {};
  var rightActive;
	HomeRightAside.App = (function() {
		var quicklinksButton = $('#quicklinks-btn-home');
		var quicklinksMenu = $('#quicklinks-section-home');
		var qlLinks = quicklinksMenu.find('a');
		var qlInput = $('#user-email');
		var qlBtn = $('.email-btn');

		function initApp() {
      quicklinksMenu.on('keydown', handleKeydown);
      qlInput.on('keydown', handleKeydown);
			quicklinksButton.on('click', handleClick);

			if ($(document).width() > 700) {
				rightActive = true;
				enableQuicklinks();
				$('.nav-links a, .nav-logo').click(function(e){
					e.preventDefault();
					var url = $(this).attr("href");
					$('.left-container, .right-container').addClass('out');
					$('nav').addClass('moving');
					setTimeout(function(){
						window.location = url;
					}, 600);
				});
			} else {
				rightActive = false;
				disableQuicklinks();
			}
		}
		function handleKeydown() {
			if (event.which === ESCAPE_CODE) {
				rightActive = false;
				disableQuicklinks();
				quicklinksButton.focus();
			}
		}
	
		function handleClick() {
			if (rightActive === true) {
				rightActive = false;
				disableQuicklinks();
			} else {
				rightActive = true;
				enableQuicklinks();
				qlInput.focus();
				}
		}
		function enableQuicklinks() {
			quicklinksButton.html('Hide quick links');
			$('.right-container').removeClass('out');
			$('.right-container').addClass('in');
			quicklinksButton.attr('aria-expanded', 'true');
			quicklinksMenu.removeAttr('aria-hidden');
			qlLinks.removeAttr('tabindex');
			qlInput.removeAttr('tabindex');
			qlBtn.removeAttr('tabindex');		
		}
		
		function disableQuicklinks() {
			quicklinksButton.html('View quick links');
			$('.right-container').removeClass('in');
			$('.right-container').addClass('out');
			quicklinksButton.attr('aria-expanded', 'false');
			quicklinksMenu.attr('aria-hidden', 'true');
			qlLinks.attr('tabindex', '-1');
			qlInput.attr('tabindex', '-1');
			qlBtn.attr('tabindex', '-1');	
		}
		return {
			init: function(){
			initApp();
			}
		}
	})();
	new HomeRightAside.App.init();
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
    .fail(function() {
      displayApiError();
    });

}
function displayApiError() {
  var message = 'Please <a href="https://www.youtube.com/channel/UCvjgXvBlbQiydffZU7m1_aw">visit the Coding Train on YouTube</a> for the latest videos.'
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
