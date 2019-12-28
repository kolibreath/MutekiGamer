<!DOCTYPE html>
<html>
<head>

	<%--TODO 有点问题 暂时不用这个作为index--%>
<meta charset="UTF-8">

<link rel="stylesheet" type="text/css" href="./static/self-defined.css">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap-responsive.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.min.css">

<script type="text/javascript" src="js/self-defined-js.js"></script>

<title>学生社团自主管理平台</title>

</head>

<body>
	<div id="container" class="bodyBG">
		<div class="coverBG">
			<!-- title -->
			<div class="top-bar clear">
				<div class="logo clear">
					<img src="../img/logo-new.png" alt="VULCAN logo">
				</div>
				<div class="row-fluid">
					<div class="span1"></div>
					<div class="span1 pointer-div"><a href="">Home</a></div>
					<div class="span1 pointer-div"><a href="">BBS</a></div>
					<div class="span1 pointer-div"><a href="">Reservation</a></div>
					<div class="span1 pointer-div"><a href="">Clubs</a></div>
					<div class="span1 pointer-div"><a href="">School Index</a></div>
					<div class="span2"></div>
					<!-- <div class="span2">
						<img src="" alt="user1">
						<a href="">user1</a>
					</div> -->
					<div class="span2">
						<img src="" alt="user1">
						<a href="./Login.html"><%= request.getSession().getAttribute("email") %></a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<!-- body -->
			<div class="main-body">
				<div >

				</div>
			</div>
			<!-- bottom -->
			<div class="bottom-bar">
				<p>designed by VULCAN</p>
			</div>
		</div>	
	</div>	
</body>

</html>