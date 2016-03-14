$(document).ready(function(){

	$('.left-container, .right-container').removeClass('out');

	$('.nav-links a').click(function(e){

		e.preventDefault();

		var url = $(this).attr("href");

		$('.left-container, .right-container').addClass('out');
		$('nav').addClass('moving');

		setTimeout(function(){

			window.location = url;

		}, 600);

	});

	$('.fun-link').hover(function(){

		var array = ["blue", "red", "yellow", "purple"];
		var colour = array[Math.floor(Math.random() * array.length)];
		console.log('here');
		
		$(this).removeClass('red blue yellow purple')
		$(this).addClass(colour);

	});

});