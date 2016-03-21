var apiKey 				= "AIzaSyADOKEHZag2UMG52bd7ApxDOssdzVo0j8I";
var shiffmanChannelId 	= "UCvjgXvBlbQiydffZU7m1_aw";

$(document).ready(function(){

	getPlaylists();

});

function getPlaylists() {

	$.get('https://www.googleapis.com/youtube/v3/playlists?key=AIzaSyADOKEHZag2UMG52bd7ApxDOssdzVo0j8I', { part: 'snippet' , channelId: shiffmanChannelId })
	.done(function(data){

		console.log(data);
		populateData(data);

	}, 'JSON')
	.error(function(){

		alert('Ahh sorry we couldn\' theres a problem');

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

		$.get('https://www.googleapis.com/youtube/v3/playlistItems?key=AIzaSyADOKEHZag2UMG52bd7ApxDOssdzVo0j8I', {part: 'snippet', playlistId: playlist.id} )
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
	                '<a href="http://youtube.com/playlist?list='+playlist.id+'" class="toplaylist" target="_blank"><h2>'+snippet.title+'<img src="images/icon-arrow.svg"/></h2></a>'+
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