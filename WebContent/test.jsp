<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <link rel="shortcut icon" href="#" />
  
  <style>
  	nav ul {
		  background-color: #247BA0;
		  padding-left: 0px;
		  margin-bottom: 0px;
		}
		nav li {
		  padding-top: 15px;
		  padding-bottom: 15px;
		}
		a {
		  text-decoration: none;
		  color: #E1FFEB;
		  font-size: 20px;
		  font-weight: bold;
		  font-family: 'Poiret One', cursive;
		  text-shadow: 2px 2px #083C52;
		  opacity: 0.5;
		}
		a:hover {
		  color: #FFFFFF;
		  -webkit-transition: opacity 0.5s ease-in-out;
		  -moz-transition: opacity 0.5s ease-in-out;
		  -ms-transition: opacity 0.5s ease-in-out;
		  -o-transition: opacity 0.5s ease-in-out;
		  opacity: 1;
		}
		#menu_div {
		  display: none;
		  
		}
		@media screen and (min-width: 680px) {
		  nav li:first-child {
		    padding-left: 30px;
		  }
		  nav li {
		    display: inline-block;
		    padding-right: 30px;
		  }
		}
		
		@media screen and (max-width: 680px) {
		  nav li {
		    text-align: center;
		  }
		  nav ul {
		    display: none;
		    columns: 1;
		    -webkit-columns: 1;
		    -moz-columns: 1;
		  }
		  #menu_div {
		  	height:48px;
		    display: block;
		    text-align: left;
		    width: 100%;
		    background-color: #0F455C;
		    padding-top: 10px;
		    //padding-bottom: 15px;
		  }
		  #menu {
		    padding-left: 15px;
		  }
		}
  	
  </style>

<!--   <link rel="stylesheet" href="./css/font-awesome.min.css"> -->
  
  <style>
  	/* cyrillic */
		@font-face {
		  font-family: 'Poiret One';
		  font-style: normal;
		  font-weight: 400;
		  src: url(https://fonts.gstatic.com/s/poiretone/v9/UqyVK80NJXN4zfRgbdfbo5pcV_cx.woff2) format('woff2');
		  unicode-range: U+0400-045F, U+0490-0491, U+04B0-04B1, U+2116;
		}
		/* latin-ext */
		@font-face {
		  font-family: 'Poiret One';
		  font-style: normal;
		  font-weight: 400;
		  src: url(https://fonts.gstatic.com/s/poiretone/v9/UqyVK80NJXN4zfRgbdfbo5BcV_cx.woff2) format('woff2');
		  unicode-range: U+0100-024F, U+0259, U+1E00-1EFF, U+2020, U+20A0-20AB, U+20AD-20CF, U+2113, U+2C60-2C7F, U+A720-A7FF;
		}
		/* latin */
		@font-face {
		  font-family: 'Poiret One';
		  font-style: normal;
		  font-weight: 400;
		  src: url(https://fonts.gstatic.com/s/poiretone/v9/UqyVK80NJXN4zfRgbdfbo55cVw.woff2) format('woff2');
		  unicode-range: U+0000-00FF, U+0131, U+0152-0153, U+02BB-02BC, U+02C6, U+02DA, U+02DC, U+2000-206F, U+2074, U+20AC, U+2122, U+2191, U+2193, U+2212, U+2215, U+FEFF, U+FFFD;
		}
  </style>
  
  <script src="./js/jquery-3.3.1.js"></script>
  
</head>

<body>
  <div id="menu_div">
<!--     <a id="menu" href="#"><i class="fa fa-bars"></i> MENU</a>\ -->
		<a href="#" style="float:left; font-size:32px;">三合一加值系統</a>
    <a id="menu" href="#" style="float:right;"><img alt="" src="./images/32_32.png"></a>
  </div>
  <nav>
    <ul id="nav">
      <li><a href="#">HOME</a></li>
      <li><a href="#">ABOUT</a></li>
      <li><a href="#">PICTURE</a></li>
      <li><a href="#">MUSIC</a></li>
      <li><a href="#">FACEBOOK</a></li>
      <li><a href="#">CONTACT</a></li>
    </ul>
  </nav>


	<script>
	 $(function() {
	      var nav = $('#nav');
	      var win = $(window);
	      $('#menu').on('click', function(e) {
	        e.preventDefault();
	        nav.slideToggle();
	        nav.css('margin-top',"-16px");
	        
	        $('#menu_div').css('margin-top',"-16px");
	        
	      });

	      win.resize(function() {
	        if (win.width() > 480 && nav.is(':hidden')) {
	          nav.removeAttr('style');
	        }
	      });
	    });
	</script>
</body>
</html>