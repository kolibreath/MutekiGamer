<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
   <!-- <meta http-equiv="Content-Type" content="text/html" /> -->
    <meta name="keyword" content="HTML, CSS, image, navigation" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>VULCAN! - clubIndex</title>
    <link rel="stylesheet" type="text/css" href="css/index.css" />
    <link rel="stylesheet" type="text/css" href="css/clubHomepage.css" />
</head>

<body>oss
    <!-- Navigation Bar -->
    <div class="barContainer">
        <div class="nav-content">
            <!-- logo -->
            <div class="nav-title">
                <span>
                    <img src="img/logo-new.png" alt="VULCAN logo">
                </span>
            </div>
            <!-- context -->
            <div class="nav-text">
                <span style="float: left"><a href="HomePage.jsp">Home</a></span>
                <span style="float: left"><a href="BBSIndex.jsp">BBS</a></span>
                <span style="float: left"><a href="#Reservation">Reservation</a></span>
                <span class="active" style="float: left"><a href="#Clubs">Clubs</a></span>
                <span style="float: left"><a href="#School's Index">School's Index</a></span>
                <span class="user">
                    <a href="Login.jsp">Login</a>
                </span>
                <!-- <span class="user">
                    <a href="#Login">user1</a>
                </span>
                <span class="user">
                    <img src="">
                </span> -->
            </div>  
        </div>
    </div>
    <div class="clear"></div>
    <!-- body -->
    <div class="main-body">
        <div class="main-content">
            <!-- topPic -->
            <div class="picFrame">
                <div class="picContent">
                    <img src="img/Club-Event.jpg">
                    <button>Gallery</button>
                </div>
            </div>
            <div class="division clear"></div>
            <!-- clubNotice -->
            <div class="contextFrame">
                <div class="notice">
                    <div class="clubInfoContent">
                        <div class="clubLogo">
                            <img src="img/scCyberPunk2/scCyberPunk2/D A R K A R T/Venus.gif">
                        </div>
                        <div class="clubIntro">
                            <div class="clubName">
                                <h1>About US</h1>
                            </div>
                            <div class="intro">
                                <p>blablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablablabla</p>
                                <div class="joinButton">
                                    <button onclick="Jump()">Join US!</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="division clear"></div>
            <!-- clubIntroduction -->
            <div class="contextFrame">
                <div class="notice">
                    <div class="listContent">
                        <div class="noticeTitle"><h1>Latest Notice</h1></div>
                        <div class="noticeBBS"><a href="">Soccer Club: The training this Sunday will begin on 7:00 a.m.</a></div>
                        <div class="noticeBBS"><a href="">Anime Club: A famous dancer "man-eater cat" will pay a visit to our school next Monday</a></div>
                        <div class="noticeBBS"><a href="">Soccer Club: The training this Sunday will begin on 7:00 a.m.</a></div>
                        <div class="noticeBBS"><a href="">Soccer Club: The training this Sunday will begin on 7:00 a.m.</a></div>
                        <div class="noticeBBS"><a href="">Soccer Club: The training this Sunday will begin on 7:00 a.m.</a></div>
                    </div>
                </div>
            </div>        
        </div>
        <div class="division clear"></div>
        <div class="division clear"></div>
        <div class="division clear"></div>
    </div>
    <div id="jumpLogin" class="jumpOut">
        <div class="jumpOutMain">
            <div class="jumpOutTitle">
                <h1>Join US!</h1>
            </div>
            <div class="jumpOutContent">
                <div class="inputReason">
                    <form action="" method="post" id="jumpOutForm">
                        <b>Why you want to join us?</b>
                        <textarea wrap="virtual" ></textarea>
                    </form>
                </div>
            </div>
            <div class="jumpOutBottom">
                <input form="jumpOutForm" type="submit" value="Submit" onclick="submit()"></input>
                <button onclick="Hide()">Cancel</button>
            </div>
        </div>
    </div>
    <!-- footer -->
    <footer class="bottom-bar">
        Designed by Vulcan.
    </footer>
    <!-- JavaScript -->
    <script type="text/javascript">
        function Jump(){
            document.getElementById("jumpLogin").style.display = "block";
            document.getElementById("jumpLogin").style.height = document.body.offsetHeight+"px";
        }
        function Hide(){
            document.getElementById("jumpLogin").style.display = "none";
        }
        function submit(){
            alert("Your request has been sent successfully");
        }
    </script>
</body>

</html>
