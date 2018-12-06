<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="headCMS.jsp"></jsp:include>

<body>
<div class="container-scroller">

    <jsp:include page="navCMS_header.jsp"></jsp:include>

    <div class="container-fluid page-body-wrapper">
        <jsp:include page="navCMS_menu.jsp"></jsp:include>
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <article class="resume-wrapper text-center position-relative">
                        <div
                                class="resume-wrapper-inner mx-auto text-left bg-white shadow-lg">
                            <header class="resume-header pt-4 pt-md-0">
                                <div id="candidateAvatar" class="media flex-column flex-md-row">
                                    <img class="mr-3 img-fluid picture mx-auto"
                                         style="width: 220px; height: 220px;"
                                         src="${candidate.image.webPath}" alt="">
                                    <div
                                            class="media-body p-4 d-flex flex-column flex-md-row mx-auto mx-lg-0">
                                        <div class="primary-info">
                                            <h1 id="name"
                                                class="name mt-0 mb-1 text-white text-uppercase text-uppercase">${candidate.name}</h1>

                                            <ul class="nav list-group">
                                                <li class="nav-item"><i class="mdi mdi-email-outline"></i>
                                                    <span id="emailCandidate" class="menu-title">
                                                        <c:out value="${candidate.email}"/></span></li>
                                                <li><i class="mdi mdi-cellphone-iphone"></i><span
                                                        id="phoneCandidate">${candidate.phoneNumber}</span></li>
                                            </ul>

                                        </div>
                                        <!--//primary-info-->

                                        <!--//secondary-info-->

                                    </div>
                                    <!--//media-body-->
                                </div>
                                <!--//media-->
                            </header>
                            <div class="resume-body p-5">
                                <section class="resume-section summary-section mb-5">
                                    <h2
                                            class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">Giới
                                        thiệu</h2>
                                    <div class="resume-section-content">

                                        <h3>Địa chỉ:</h3> <c:out value="${candidate.address}"/>
                                        <h3>Ngày sinh:</h3> <c:out value="${candidate.dayOfBirth}"/>
                                    </div>
                                </section>
                                <!--//summary-section-->
                                <div class="row">
                                    <div class="col-lg-7">
                                        <section class="resume-section experience-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                kinh nghiệm làm việc</h2>
                                            <c:forEach var="exp" items="${candidate.experience}">
                                                <h3><c:out value="${exp}"/><br></h3>
                                            </c:forEach>

                                        </section>
                                        <!--//experience-section-->
                                    </div>
                                    <div class="col-lg-5">
                                        <section class="resume-section skills-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                kĩ năng</h2>
                                            <div class="resume-section-content">
                                                <div class="resume-skill-item">
                                                    <ul class="list-unstyled">
                                                        <c:forEach var="skill" items="${candidate.skills}">
                                                            <li class="mb-2">
                                                                <div class="resume-skill-name">
                                                                    <h3><c:out value="${skill.name}"/></h3>
                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                                <!--//resume-skill-item-->


                                            </div>
                                            <!--resume-section-content-->
                                        </section>
                                        <!--//skills-section-->
                                        <section class="resume-section education-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                Chứng chỉ</h2>
                                            <div class="resume-section-content">
                                                <ul class="list-unstyled">
                                                    <c:forEach items="${candidate.certificates}" var="certificate">
                                                        <li class="mb-2 pl-4 position-relative"><i
                                                                class="resume-award-icon fas fa-trophy position-absolute"
                                                                data-fa-transform="shrink-2"></i>
                                                            <div class="resume-award-name">
                                                                <h3><c:out value="${certificate}"/></h3>
                                                            </div>
                                                            <div class="resume-award-desc">
                                                                Good
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </section>
                                        <!--//education-section-->
                                        <section class="resume-section reference-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                thành tựu</h2>
                                            <div class="resume-section-content">
                                                <ul class="list-unstyled resume-awards-list">
                                                    <li class="mb-2 pl-4 position-relative"><i
                                                            class="resume-award-icon fas fa-trophy position-absolute"
                                                            data-fa-transform="shrink-2"></i>
                                                        <div class="resume-award-name">Award Name Lorem</div>
                                                        <div class="resume-award-desc">Award desc goes
                                                            here, ultricies nec, pellentesque eu, pretium quis, sem.
                                                            Nulla consequat massa quis enim. Donec pede justo.
                                                        </div>
                                                    </li>
                                                    <li class="mb-0 pl-4 position-relative"><i
                                                            class="resume-award-icon fas fa-trophy position-absolute"
                                                            data-fa-transform="shrink-2"></i>
                                                        <div class="resume-award-name">Award Name Ipsum</div>
                                                        <div class="resume-award-desc">Award desc goes
                                                            here, ultricies nec, pellentesque.
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </section>
                                        <!--//interests-section-->
                                        <section class="resume-section language-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                ngôn ngữ</h2>
                                            <div class="resume-section-content">
                                                <ul class="list-unstyled resume-lang-list">
                                                    <li class="mb-2"><span
                                                            class="resume-lang-name font-weight-bold">English</span>
                                                        <small class="text-muted font-weight-normal">(Native)</small>
                                                    </li>
                                                    <li class="mb-2 align-middle"><span
                                                            class="resume-lang-name font-weight-bold">French</span>
                                                        <small
                                                                class="text-muted font-weight-normal">(Professional)
                                                        </small>
                                                    </li>
                                                    <li><span class="resume-lang-name font-weight-bold">Spanish</span>
                                                        <small class="text-muted font-weight-normal">(Professional)
                                                        </small>
                                                    </li>
                                                </ul>
                                            </div>
                                        </section>
                                        <!--//language-section-->
                                        <section class="resume-section interests-section mb-5">
                                            <h2
                                                    class="resume-section-title text-uppercase font-weight-bold pb-3 mb-3">
                                                Link CV</h2>
                                            <div class="resume-section-content">
                                                <a href="<c:out value="${candidate.curriculumVitae.downloadUri}"/>">Link
                                                    tải về</a>
                                            </div>
                                        </section>
                                        <!--//interests-section-->

                                    </div>
                                </div>
                                <!--//row-->
                            </div>
                        </div>
                    </article>


                </div>
            </div>
            <footer class="footer">
                <div
                        class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
                                class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
							© 2018 <a
                                    href="https://gst.fsoft.com.vn/cas/login?service=https%3A%2F%2Fgst.fsoft.com.vn%2Fsakai-login-tool%2Fcontainer"
                                    target="_blank">DTC Software</a>. All rights reserved.
						</span>
                </div>
            </footer>
        </div>
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- <script>
    var full_url = document.URL;
    var url_array = full_url.split('/')
    var last_segment = url_array[url_array.length - 1];
    alert(last_segment);

    $(document).ready(function() {
        $.ajax({
            type : 'GET',
            url : '/candidate/get/' + last_segment,
            data : {
                get_param : 'value'
            },
            dataType : 'json',
            success : function(data) {
                console.log(data);
            }
        });
    });
</script> -->
<script>

</script>
</body>
</html>
