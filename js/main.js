

$(document).ready(function(){
	var MainNav = {};
	var RightAside = {};
	var ESCAPE_CODE = 27;
	MainNav.App = (function() {
		var navButton = $('#menu-button'),
		navMenu = $('#global-nav');

		var navLinks = navMenu.find('a');

		function initApp() {
			navMenu.on('keydown', handleKeydown);
			navButton.on('click', handleClick);
			disableNavLinks();
		}
		function handleKeydown(event) {
			if (event.keyCode === ESCAPE_CODE) {
			document.body.classList.toggle('active');
			document.getElementById('menu-button').classList.remove('active');
			disableNavLinks();
			navButton.focus();
			}
		}
		function handleClick(event) {
			if (document.body.classList.contains('active')) {
			document.body.classList.remove('active');
			document.getElementById('menu-button').classList.remove('active');
			disableNavLinks();
			}
			else {
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
		function disableNavLinks() {
			navButton.attr('aria-label', 'Open navigation menu');
			navButton.attr('aria-expanded', 'false');
			navMenu.attr('aria-hidden', 'true');
			navLinks.attr('tabIndex', '-1');
		}


		return {
			init: function(){
			initApp();
			}
		}
	})();
	RightAside.App = (function() {
		var quicklinksButton = $('#quicklinks-btn');
		var quicklinksMenu = $('#quicklinks-section');
//		var qlLinks = quicklinksMenu.find('a');
		var qlInput = $('#user-email');
//		var qlBtn = $('.email-btn');

		function initApp() {
			quicklinksMenu.on('keydown', handleKeydown);
			quicklinksButton.on('click', handleClick);
			enableQuicklinks();
		}
		function handleKeydown() {
			if (event.keyCode === ESCAPE_CODE) {
			//	document.body.classList.toggle('active');
			//	document.getElementById('menu-button').classList.remove('active');
				disableQuicklinks();
				qlInput.focus();
			}
		}
		function handleClick() {
			var active = 1;
			if (active === 1) {
				disableQuicklinks();
			} else {
				active = 1;
				enableNavLinks();
				qlInput.focus();
				}
		}
		function enableQuicklinks() {
			quicklinksButton.html('Hide quick links');
			quicklinksButton.attr('aria-expanded', 'true');
			quicklinksMenu.attr('aria-hidden', 'false');			
		}
		function disableQuicklinks() {
			quicklinksButton.html('View quick links');
			quicklinksButton.attr('aria-expanded', 'false');
			quicklinksMenu.attr('aria-hidden', 'true');

		}
		return {
			init: function(){
			initApp();
			}
		}
	})();
	new MainNav.App.init();
	new RightAside.App.init();
	$('.left-container, .right-container').removeClass('out');

	$('.nav-links a, .nav-logo').click(function(e){

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
	$('.mobile-quick-links').click(function(){
		$('.right-container').addClass('in');
	});
	$('.left-container').click(function(){
		$('.right-container').removeClass('in');
	});

});