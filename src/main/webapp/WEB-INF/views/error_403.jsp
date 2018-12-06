

<%@ page pageEncoding="UTF-8" language="java"%>

<html>
<head>
<title>Error</title>
<style type='text/css'>
body {
	overflow: hidden !important
}

#error-page-bsw {
	position: relative;
	display: table;
	width: 100%;
	height: 100vh;
	margin: 0 !important;
	padding: 0 !important;
	z-index: 999;
	background:
		url(http://expressmagazine.net/sites/default/files/imagesArticle/better-accessed-denied-01.png)
		no-repeat center center !important;
	background-size: cover !important
}

#error-page-bsw:before {
	content: '';
	opacity: 1;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 0;
	background: linear-gradient(transparent, #050a18)
}

#error-page-bsw:after {
	content: ' ';
	z-index: 0;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background-image: linear-gradient(transparent, rgba(51, 51, 51, 0.15) 0%,
		rgba(12, 23, 36, 0.4))
}

.error-page-bsw_content {
	position: relative;
	z-index: 3
}

.intro_label h2 {
	font-size: 45px;
	font-weight: 700;
	color: rgba(255, 255, 255, .85);
	margin: 0 0 .5em;
	padding: 0;
	font-family: " Roboto Condensed ", sans-serif;
	text-transform: uppercase
}

.intro_label p {
	text-transform: uppercase;
	font-size: 16px;
	font-weight: 400;
	color: rgba(255, 255, 255, 0.7);
	margin: 0;
	padding: 0;
	letter-spacing: 2px
}

.intro_label {
	margin: 0 auto;
	text-align: center;
	padding: 0
}

.btn-error-page {
	color: rgba(255, 255, 255, .5) !important;
	padding: 8px 25px;
	display: inline-block;
	margin: 1.5em .5em;
	border-radius: 50px;
	text-transform: uppercase;
	border: 1px solid rgba(255, 255, 255, .3);
	font-size: 14px;
	font-weight: 500;
}

.btn-error-page:hover {
	background: rgba(255, 255, 255, .15)
}

.meta_ {
	display: table-cell;
	vertical-align: middle;
	text-align: center
}
</style>
</head>
<body>
	<div id='error-page-bsw'>
		<div class='meta_'>
			<div class='error-page-bsw_content'>
				<div class='intro_label'>
					<h2 class='_title'>403 - AccessDenied</h2>
					<p>Bạn không có quyền truy cập</p>
					<a class='btn-error-page' href='/home'>Về trang chủ</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
