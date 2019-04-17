

$(document).ready(function(){
	var MainNav = {};
	var RightAside = {};
	var ESCAPE_CODE = 27;
	var rightActive;

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
		var showText, hideText;
		switch(window.location.pathname) {
			case '/learning/':
				showText = 'Show courses links';
				hideText = 'Hide courses links';
				break;
			case '/books/':
				showText = 'View books';
				hideText = 'Hide books';
				break;
			case '/videos/':
				showText = 'Video quick links';
				hideText = 'Hide quick links';
				break;
			default:
				showText = 'Show quick links';
				hideText = 'Hide quick links';
		}
		var quicklinksButton = $('#quicklinks-btn');
		var quicklinksMenu = $('#quicklinks-section');
		var qlLinks = quicklinksMenu.find('a');

		function initApp() {
			quicklinksMenu.on('keydown', handleKeydown);
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
				quicklinksButton.css('display', 'block');
			}
		}
		function handleKeydown() {
			if (event.keyCode === ESCAPE_CODE) {
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
				qlLinks.eq(0).focus();
				}
		}
		function enableQuicklinks() {
			quicklinksButton.html(hideText);
			$('.right-container').removeClass('out');
			$('.right-container').addClass('in');
			quicklinksButton.attr('aria-expanded', 'true');
			quicklinksMenu.removeAttr('aria-hidden');
			qlLinks.removeAttr('tabindex');	
		}
		
		function disableQuicklinks() {
			quicklinksButton.html(showText);
			$('.right-container').removeClass('in');
			$('.right-container').addClass('out');
			quicklinksButton.attr('aria-expanded', 'false');
			quicklinksMenu.attr('aria-hidden', 'true');
			qlLinks.attr('tabindex', '-1');
		}
		return {
			init: function(){
			initApp();
			}
		}
	})();
	new MainNav.App.init();
	new RightAside.App.init();
	$('.left-container').removeClass('out');
});

/* Remove - deprecated
	$('.fun-link').hover(function(){

		var array = ["blue", "red", "yellow", "purple"];
		var colour = array[Math.floor(Math.random() * array.length)];
		console.log('here');
		
		$(this).removeClass('red blue yellow purple')
		$(this).addClass(colour);

	});
*/

