<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<h3 class="page-title">
							<span class="page-title-icon bg-gradient-primary text-white mr-2">
								<i class="mdi mdi-home"></i>
							</span> Trang chủ
						</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item active" aria-current="page"><span></span>Tổng
									quan <i
									class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
								</li>
							</ul>
						</nav>
					</div>
					<div class="row">
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-danger card-img-holder text-white">
								<div class="card-body">
									<img src="/cms/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h5 class="font-weight-normal mb-3">
										Tổng số ứng viên <i
											class="mdi mdi-account-multiple mdi-24px float-right"></i>
									</h5>
									<h1 id="totalCandidates" class="mb-5"
										style="text-align: center; font-size: 50px;"></h1>
								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-success card-img-holder text-white">
								<div class="card-body">
									<img src="/cms/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h5 class="font-weight-normal mb-3">
										Tổng số ứng viên tiềm năng<i
											class="mdi mdi-account-check mdi-24px float-right"></i>
									</h5>
									<h1 id="totalPotentials" class="mb-5"
										style="text-align: center; font-size: 50px;"></h1>
								</div>
							</div>
						</div>
						<div class="col-md-4 stretch-card grid-margin">
							<div class="card bg-gradient-info  card-img-holder text-white">
								<div class="card-body">
									<img src="/cms/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h5 class="font-weight-normal mb-3">
										Tổng số ngành nghề<i
											class="mdi mdi-layers mdi-24px float-right"></i>
									</h5>
									<h1 id="totalMajors" class="mb-5"
										style="text-align: center; font-size: 50px;"></h1>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 stretch-card grid-margin">
							<div class="card bg-gradient-dark card-img-holder text-white">
								<div class="card-body">
									<img src="/cms/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h5 class="font-weight-normal mb-3">
										Tổng số vị trí tuyển dụng<i
											class="mdi mdi-debug-step-into mdi-24px float-right"></i>
									</h5>
									<h1 id="totalPositions" class="mb-5"
										style="text-align: center; font-size: 50px;"></h1>
								</div>
							</div>
						</div>
						<div class="col-md-6 stretch-card grid-margin">
							<div class="card bg-gradient-warning card-img-holder text-white">
								<div class="card-body">
									<img src="/cms/images/dashboard/circle.svg"
										class="card-img-absolute" alt="circle-image" />
									<h5 class="font-weight-normal mb-3">
										Tổng số kĩ năng<i
											class="mdi mdi-language-javascript mdi-24px float-right"></i>
									</h5>
									<h1 id="totalSkills" class="mb-5"
										style="text-align: center; font-size: 50px;"></h1>
								</div>
							</div>
						</div>
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
	<script type="text/javascript">
		//Tong so Ki nang
		$(document).ready(function() {
			$.ajax({
				url : '/skill/get/all',
				dataType : 'json',
				type : 'GET',
				success : function(data) {
					$('#totalSkills').html(data['data'].length);
				}
			});
		});

		//Tong so vi tri tuyen dung
		$(document).ready(function() {
			$.ajax({
				url : '/position/get/all',
				dataType : 'json',
				type : 'GET',
				success : function(data) {
					$('#totalPositions').html(data['data'].length);
				}
			});
		});

		//Tong so ung vien tiem nang
		$(document).ready(function() {
			$.ajax({
				url : '/potential/get/all',
				dataType : 'json',
				type : 'GET',
				success : function(data) {
					$('#totalPotentials').html(data['data'].length);
				}
			});
		});

		//Tong so ung vien
		$(document).ready(function() {
			$.ajax({
				url : '/candidate/get/all',
				dataType : 'json',
				type : 'GET',
				success : function(data) {
					$('#totalCandidates').html(data['data'].length);
				}
			});
		});

		//tong so nganh nghe
		$(document).ready(function() {
			$.ajax({
				url : '/major/get/all',
				dataType : 'json',
				type : 'GET',
				success : function(data) {
					$('#totalMajors').html(data['data'].length);
				}
			});
		});
		
		//

	</script>
</body>
</html>
