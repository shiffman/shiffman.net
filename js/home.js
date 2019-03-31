var shiffmanChannelId = "UCvjgXvBlbQiydffZU7m1_aw";
var apiKey 				= "AIzaSyAInyAq_CBIJ0W7p_pqJlnA7dBHvC5FG8M";
let getPlaylistsStr = 'https://www.googleapis.com/youtube/v3/playlists?key=' + apiKey;
$(document).ready(function() {

  getVideos();

});

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
      alert('Apologies, there was an error communicating with the YouTube API.');
    });

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
