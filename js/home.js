var shiffmanChannelId 	= "UCvjgXvBlbQiydffZU7m1_aw";

$(document).ready(function(){

	getVideos();

});

function getVideos() {

	$.get('https://www.googleapis.com/youtube/v3/search?key=AIzaSyADOKEHZag2UMG52bd7ApxDOssdzVo0j8I', { part: 'snippet' , channelId: shiffmanChannelId, order: 'date' })
	.done(function(data){

		console.log(data);
		populateData(data);

	}, 'JSON')
	.error(function(){

		alert('Ahh sorry we couldn\' theres a problem');

	});

}

function populateData(data){

	var videos = data.items;

	$.each(videos, function(index, video){

		var snippet = video.snippet;

		var block = '<div class="video-entry row">'+
        				'<div class="col-left">'+
        					'<div class="thumbnail"><img src="'+snippet.thumbnails.medium.url+'"/></div>'+
        				'</div>'+
        				'<div class="col-right">'+
        					'<div class="content">'+
        						'<h2>'+snippet.title+'</h2>'+
        						'<p>'+
        							snippet.description+
        						'</p>'+
        						'<div class="actions">'+
        							'<a href="http://youtube.com/video/'+video.id.videoId+'" class="body-link primary">Watch on Youtube</a>'+
        						'</div>'+
        					'</div>'+
        				'</div>'+
        			'</div>';

        $('.latest-videos').append(block);

	});

}
