<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link
            href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
    <script
            src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- - ------------------>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
          crossorigin="anonymous">

    <title>DTC-Software Tuyển Dụng</title>
    <style type="text/css">
        .container {
            width: 100%;
            height: auto;
            background: linear-gradient(white, white, #CCCCCC, #CCCCCC, #BBBBBB);
            display: flex;
            border-radius: 7px;
            margin-top: 10px;
            position: relative;
        }

        body {
            background-color: lightgrey;
        }

        .panel-body {
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        .text {
            margin-left: 38%;
            font-size: 20px;
        }

        .text-check {
            color: #00a185;
            margin: 5px;
            font-size: 20px;
        }
    </style>

</head>

<body>

<div class=" container ">
    <div class="col-md-12 col-sm-12 col-sm-12">
        <img src="images/fsoft.jpg" alt="fsoft">
    </div>
</div>

<c:set var="recruitment" value="${requestScope.recruitment}"></c:set>
<div class="container" style="height: auto">
    <div class="col-lg-7 ">
        <div class="panel ">
            <div class="panel-body " style="margin-top: 10px">
                <p class="text"><b><i>Tên Tuyển Dụng:</i></b> ${recruitment.name }</p>
                <p class="text"><b><i>Ngày Bắt Đầu:</i></b> ${recruitment.startDate }</p>
                <p class="text"><b><i>Ngày Kết Thúc:</i></b> ${recruitment.finishDate }</p>
                <p class="text"><b><i>Số Lượng Tuyển:</i></b> ${recruitment.numberOfRecruits }</p>
                <p class="text"><b><i>Mô Tả:</i></b> ${recruitment.description }</p>
                <p class="text"><b><i>Lương:</i></b> ${minSalary} - ${maxSalary} </p>
            </div>
        </div>
    </div>

    <div class="col-lg-5 text-check">
        <h3>YOUR BENEFITS</h3>
        <p>
            <i class="fa fa-check"></i> Easy to GET A JOB
        </p>
        <p>
            <i class="fa fa-check"></i> Get “Global Software Talent”
            certificate
        </p>
        <p>
            <i class="fa fa-check"></i> Have many opportunities to work onsite
            in US, Europe, Japan and South East Asia
        </p>
        <p>
            <i class="fa fa-check"></i> No more entry-tests and 3 months
            training when applying to FPT Software - You can join directly to
            projects!
        </p>
    </div>

</div>

<br>


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

</body>
</html>