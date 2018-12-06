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
						<h3 class="page-title">Quản lý Users</h3>
					</div>
					<div class="row">
						<div class="col-12 grid-margin">
							<div class="card">
								<div class="card-body">
									<div class="table-responsive">
										<table id="users-table">
											<thead>
												<tr>
													<th>ID</th>
													<th>Email</th>
													<th>Trạng thái</th>
													<th>Họ tên</th>
													<th>Số điện thoại</th>
													<th>Tên đăng nhập</th>
													<th>Mật khẩu</th>
													<th>Vai trò</th>
												</tr>
											</thead>
										</table>
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
		</div>
		<!-- /#wrapper -->

		<script>
			let fields = [ {
				name : 'id',
				type : 'hidden'
			}, {
				label : 'Họ Tên',
				name : 'name',
			}, {
				label : 'Email',
				name : 'email',
			}, {
				label : 'Tên đăng nhập',
				name : 'username',
			}, {
				label : 'Mật khẩu',
				name : 'password',
				type : 'password'
			}, {
				label : 'Số điện thoại',
				name : 'phone',
			}, {
				label : 'Trạng thái',
				name : 'isActive',
				type : 'select',
				options : [ {
					label : 'Có',
					value : 'true'
				}, {
					label : 'Không',
					value : 'false'
				} ]
			}, {
				label : 'Vai trò',
				name : 'roles[].name',
				type : 'checkbox',
			} ];
			let ajaxUrls = {
				getUrl : '/admin/get/all',
				createUrl : '/admin/dataTable/create',
				editUrl : '/admin/dataTable/edit',
				removeUrl : '/admin/dataTable/delete',
			};
			let columns = [ {
				data : 'id'
			}, {
				data : 'email'
			}, {
				data : 'isActive'
			}, {
				data : 'name'
			}, {
				data : 'phone'
			}, {
				data : 'username'
			}, {
				data : 'password'
			}, {
				data : 'roles',
				render : "[, ].name",
			} ];

			let editor;
			let dataTable;

			$(document).ready(
					function() {
						editor = validatedEditor(createEditor('users-table',
								ajaxUrls, fields));

						dataTable = createDataTable($("#users-table"),
								ajaxUrls, columns);

						editor.on('preOpen', function(action) {
							if (action !== 'remove') {
								updateEditorField(editor, 'roles[].name',
										'/role/get/all');
							}
						});

					});
		</script>
</body>
</html>
