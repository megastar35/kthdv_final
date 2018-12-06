<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DTC Software career</title>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- - ------------------>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/css/css.css">
    <style>
        .mySlides {
            display: none
        }

        .w3-left, .w3-right, .w3-badge {
            cursor: pointer;
            opacity: 0.4;
        }

        .w3-badge {
            bottom: 0px;
            height: 13px;
            width: 13px;
            padding: 0px;
        }

        .panel {
            opacity: 1;
            display: block;
            width: 100%;
            height: auto;
            transition: .5s ease;
            backface-visibility: hidden;
        }

        .middle {
            transition: .5s ease;
            opacity: 0;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            text-align: center;
        }

        .panel:hover .image {
            opacity: 0.3;
        }

        .panel:hover .middle {
            opacity: 1;
        }

        .text {
            background-color: #2F2F2F;
            color: white;
            font-size: 16px;
            padding: 16px 32px;
        }

        .mySlides {
            display: none
        }

        img {
            vertical-align: middle;
        }

        /* Slideshow container */
        .slideshow-container {
            max-width: 1000px;
            position: relative;
            margin: auto;
        }

        .member__row {
            height: auto;
        }

        /* Caption text */
        .text1 {
            background: rgba(0, 0, 0, 0.5);
            color: #f2f2f2;
            font-size: 18px;
            padding: 8px 12px;
            position: absolute;
            bottom: 7px;
            width: 100%;
            height: 65px;
            text-align: center;
        }

        .demo {
            cursor: pointer;
            height: 15px;
            width: 15px;
            margin: 0 2px;
            background-color: #bbb;
            border-radius: 50%;
            display: inline-block;
            transition: background-color 0.6s ease;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row member__row1">
        <div class="col-md-12 col-sm-12 col-sm-12">
            <img src="images/dtcsoft.jpg" alt="fsoft">
        </div>
    </div>
    <div class="row member__row2">
        <div class="col-md-8 col-sm-12 col-sm-12 member__image">
            <div class="w3-content w3-display-container slideshow-container"
                 style="max-width: 720px; width: 100%">

                <div class="mySlides">
                    <a href="#tuyendung" onclick="td()"> <img src="http://tintoantap.com/wp-content/uploads/2017/09/VNPT-Software-tuyen-dung-tai-da-nang.png"
                                                              style="width: 720px; height: 465px; width: 100%;">
                        <div class="text1">Quy trình phỏng vấn DTC-Software</div>
                    </a>
                </div>
                

                <div class="mySlides">
                    <a href="#xemchitiet" onclick="xct()"> <img
                            src="http://tintoantap.com/wp-content/uploads/2017/09/Tuyen-dung-lap-trinh-Python-VNPT.png"
                            style="width: 720px; height: 465px; width: 100%;">
                        <div class="text1">Tuyển 300 Fresher</div>
                    </a>
                </div>
                <div class="mySlides">
                    <img src="http://tintoantap.com/wp-content/uploads/2017/09/VNPT-Software-tuyen-dung-angular-Js.png"
                         style="width: 720px; height: 465px; width: 100%;">
                    <div class="text1">Lập Trình viên Java</div>
                </div>

                <div
                        class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle"
                        style="width: 100%">
                    <div class="w3-left w3-hover-text-khaki" onclick="plusDivs(-1)">&#10094;</div>
                    <div class="w3-right w3-hover-text-khaki" onclick="plusDivs(1)">&#10095;</div>
                    <span
                            class="w3-badge  demo w3-border w3-transparent w3-hover-white"
                            onclick="currentDiv(1)"></span> <span
                        class="w3-badge  demo  w3-border w3-transparent w3-hover-white"
                        onclick="currentDiv(2)"></span> <span
                        class="w3-badge  demo w3-border w3-transparent w3-hover-white"
                        onclick="currentDiv(3)"></span>
                </div>
            </div>
        </div>
        <div class="col-md-4 col-sm-12 col-sm-12 member__content">
            <div class="member__form">
                <form action="/home" method="post" accept-charset="utf-8">
                    <div class="member__title">
                        <div class="member__subtitle member--color-orange">login</div>
                    </div>
                    <div class="member__inputForm">
                        <label for="username" class="member__text">Username</label> <input
                            class="member__inp" autofocus="autofocus"
                            placeholder="Your username" id="username" type="text"
                            name="username"> <label for="password"
                                                    class="member__text">password</label> <input class="member__inp"
                                                                                                 placeholder="Your password"
                                                                                                 id="password"
                                                                                                 type="password"
                                                                                                 name="password">
                        <button type="submit" class="member__submit"
                                style="padding-bottom: 10px;">login
                        </button>
                    </div>
                </form>
                <div class="member__hiring">
                    <img src="images/recruitment.jpg" alt="hiring">
                </div>
            </div>
        </div>


    </div>
</div>
<!-- ------------------------------Tin Tuyển Dụng--------------------- -->


<div class="container member__row">
    <div id="xemchitiet" class="row">
        <div class="col-sm-12">
            <h2 class="w3-text-grey"
                style="margin-top: 4%; text-align: center; font-family: sans-serif; font-size: 50px">
                Tin Tuyển Dụng</h2>
        </div>

        <c:forEach items="${recruitments}" var="recruitment">
            <div class="col-lg-4">

                <div class="panel panel_row">

                    <div class="panel-head">
                        <h3 style="text-align: center;">${recruitment.name }</h3>
                    </div>

                    <div class="panel-body">
                        <img style="width: 100%; height: 250px" class="image"
                             src="${recruitment.image.webPath}">

                    </div>
                    <div class="middle">
                        <div>
                            <a href="/chitiet?id=${recruitment.id}" target="_blank">
                                <button class="text" id="aa">Xem chi tiết</button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>

        </c:forEach>
    </div>
</div>

<br/>

<div class="panel panel-head container" align="center">
    <div id="tuyendung" class="panel panel-body"
         style="display: block; height: auto">
        <h1 class="w3-text-grey"
            style="font-family: sans-serif; margin: 40px; text-align: center; font-size: 50px">
            Quy trình phỏng vấn DTC-software</h1>
            <img alt="" src="http://www.bigoutsource.com/assets/hiring-process-banner.jpg">

    </div>
</div>


<footer class="footer" style="text-align: center;">
    <div>

			<span
                    class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
				© 2018 <a
                        href="https://gst.fsoft.com.vn/cas/login?service=https%3A%2F%2Fgst.fsoft.com.vn%2Fsakai-login-tool%2Fcontainer"
                        target="_blank">DTC Software</a>. All rights reserved.
			</span>
    </div>
    <br>
</footer>

<script>
    var slideIndex = 0;
    carousel();
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

    function currentDiv(n) {
        showDivs(slideIndex = n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("demo");
        if (n > x.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = x.length
        }
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" w3-white", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-white";

    }

    function carousel() {
        var i;
        var x = document.getElementsByClassName("mySlides");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        slideIndex++;

        if (slideIndex > x.length) {
            slideIndex = 1
        }
        x[slideIndex - 1].style.display = "block";
        setTimeout(carousel, 4000);

    }

    function td() {
        var x = document.getElementById("tuyendung");
        /* if (x.style.display == "none") {
            x.style.display = "block";
        } else {
            x.style.display = "block";
        }
         */
        x.style.display = "block";
    }

    function xct() {
        var x = document.getElementById();
        x.style.display = "block";
    }
</script>
</body>

</html>