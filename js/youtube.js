var apiKey 				= "AIzaSyAInyAq_CBIJ0W7p_pqJlnA7dBHvC5FG8M";
var shiffmanChannelId 	= "UCvjgXvBlbQiydffZU7m1_aw";
// Change playlist IDs Here
var shiffmanPlaylists 	= "PLRqwX-V7Uu6ZiZxtDDRCi6uhfTH4FilpH,PLRqwX-V7Uu6Zy51Q-x9tMWIv9cueOFTFA,PLRqwX-V7Uu6bI1SlcCRfLH79HZrFAtBvX,PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r,PLRqwX-V7Uu6ZMlWHdcy8hAGDy6IaoxUKf,PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV,PLRqwX-V7Uu6YVljJvFRCyRM6mmF5wMPeE";

$(document).ready(function(){

	getPlaylists();

});

function getPlaylists() {
		let getPlaylistsStr = 'https://www.googleapis.com/youtube/v3/playlists?key=' + apiKey;
		$.get(getPlaylistsStr, { part: 'snippet' , id: shiffmanPlaylists })
		.done(function(data){

			console.log(data);
			populateData(data);
		}, 'JSON')
		.fail(function(){
			console.error("The website has hit API quota for the day.")
		});
}

var playlistVideos;

function returnHtml(html) {

	playlistVideos = html;
	console.log(playlistVideos);

}

function populateData(data){
	var playlists = data.items;
	$.each(playlists, function(index, playlist){
		var snippet = playlist.snippet;
		let getPlaylistItemsStr = 'https://www.googleapis.com/youtube/v3/playlists?key=' + apiKey;
		$.get(getPlaylistItemsStr, {part: 'snippet', playlistId: playlist.id} )
		.done(function(data){
			var playlistHtml = '';
			$.each(data.items, function(index, item){
				if(index == 4) {
					return false
				} else {
					var vidSnip = item.snippet;
					var html = '<a class="video" href="http://youtube.com/video/'+vidSnip.resourceId.videoId+'" target="_blank">'+
			                    	'<div class="thumbnail"><img src="'+vidSnip.thumbnails.medium.url+'" /></div>'+
			                    	'<span class="video-label">'+vidSnip.title+'</span>'+
			                    	'<span class="watch-button">WATCH VIDEO</span>'
			                	'</a>';
					playlistHtml = playlistHtml + html;
				}
			});
			buildBlock(playlistHtml);
		}, 'JSON');
	
		function buildBlock(playlistList) {

			var block = $(
			'<div class="video-playlist">'+
	            '<span class="line-charm"></span>'+
	            '<div class="playlist-header">'+
	                '<a href="http://youtube.com/playlist?list='+playlist.id+'" class="toplaylist" target="_blank"><h2>'+snippet.title+'<img src="/images/icon-arrow.svg"/></h2></a>'+
	            '</div>'+
	            '<p>'+snippet.description+'</p>'+
	            '<div class="video-list">'+
	                playlistList+
	            '</div>'+
	        '</div>'
			);
			$('#video-list').append(block);
		}
	});
}
