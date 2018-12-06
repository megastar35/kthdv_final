<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav
	class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
	<div
		class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
		<a class="navbar-brand brand-logo" href="/management_portal"><img
			style="width: 150px; height: 80px;"
			src="https://lapfptbinhthuan.com/wp-content/uploads/2018/01/cropped-fpt.png"
			alt="logo" /></a>
	</div>
	<div class="navbar-menu-wrapper d-flex align-items-stretch">
		<ul class="navbar-nav navbar-nav-right">
			<li class="nav-item nav-profile dropdown"><a
				class="nav-link dropdown-toggle" id="profileDropdown" href="#"
				data-toggle="dropdown" aria-expanded="false">
					<div class="nav-profile-img">
						<img
							src="https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png"
							alt="image"> <span class="availability-status online"></span>
					</div>
					<div class="nav-profile-text">
						<p class="mb-1 text-black">${username}</p>
					</div>
			</a>
				<div class="dropdown-menu navbar-dropdown"
					aria-labelledby="profileDropdown">
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#profileModal"> <i
						class="mdi mdi-cached mr-2 text-success"></i> Activity Log
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/logout"> <i
						class="mdi mdi-logout mr-2 text-primary"></i> Signout
					</a>
				</div></li>
			<li class="nav-item d-none d-lg-block full-screen-link"><a
				class="nav-link"> <i class="mdi mdi-fullscreen"
					id="fullscreen-button"></i>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link count-indicator dropdown-toggle"
				id="messageDropdown" href="#" data-toggle="dropdown"
				aria-expanded="false"> <i class="mdi mdi-email-outline"></i> <span
					class="count-symbol bg-warning"></span>
			</a>
				<div
					class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
					aria-labelledby="messageDropdown">
					<h6 class="p-3 mb-0">Messages</h6>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item preview-item">
						<div class="preview-thumbnail">
							<img src="/cms/images/faces/face4.jpg" alt="image"
								class="profile-pic">
						</div>
						<div
							class="preview-item-content d-flex align-items-start flex-column justify-content-center">
							<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Mark
								send you a message</h6>
							<p class="text-gray mb-0">1 Minutes ago</p>
						</div>
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item preview-item">
						<div class="preview-thumbnail">
							<img src="/cms/images/faces/face2.jpg" alt="image"
								class="profile-pic">
						</div>
						<div
							class="preview-item-content d-flex align-items-start flex-column justify-content-center">
							<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Cregh
								send you a message</h6>
							<p class="text-gray mb-0">15 Minutes ago</p>
						</div>
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item preview-item">
						<div class="preview-thumbnail">
							<img src="/cms/images/faces/face3.jpg" alt="image"
								class="profile-pic">
						</div>
						<div
							class="preview-item-content d-flex align-items-start flex-column justify-content-center">
							<h6 class="preview-subject ellipsis mb-1 font-weight-normal">Profile
								picture updated</h6>
							<p class="text-gray mb-0">18 Minutes ago</p>
						</div>
					</a>
					<div class="dropdown-divider"></div>
					<h6 class="p-3 mb-0 text-center">4 new messages</h6>
				</div></li>
			<li class="nav-item nav-settings d-none d-lg-block"><a
				class="nav-link" href="#"> <i
					class="mdi mdi-format-line-spacing"></i>
			</a></li>
		</ul>
		<button
			class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
			type="button" data-toggle="offcanvas">
			<span class="mdi mdi-menu"></span>
		</button>
	</div>
</nav>

<div id="profileModal" class="modal fade">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Thông tin cá nhân</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<form id="setting-form" action="setting" method="POST">
				 <div class="modal-body">
					 <div class="form-group row">
                        <div class="col-sm-4">
                            <label for="fullName" style="margin-top:3px;" class="control-label">Họ và tên: </label>
                        </div>
                        <div id="fullName" class="col-sm-8">
                            
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <div class="col-sm-4">
                            <label style="margin-top:3px;" class="control-label">Email: </label>
                        </div>
                        <div id="email" class="col-sm-8">
                            
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <div class="col-sm-4">
                            <label style="margin-top:3px;" class="control-label">Số điện thoại: </label>
                        </div>
                        <div id="phoneNumber" class="col-sm-8">
                            
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <div class="col-sm-4">
                            <label style="margin-top:3px;" class="control-label">Vai trò: </label>
                        </div>
                        <div id="role" class="col-sm-8">
                            
                        </div>
                    </div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal">Đóng</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$.ajax({
			type : 'GET',
			url : '/admin/get/current',
			data : {
				get_param : 'value'
			},
			dataType : 'json',
			success : function(data) {
				$('#fullName').html(data['name']);
				$('#email').html(data['email']);
				$('#phoneNumber').html(data['phone']);
				$('#role').html(data['roles']);
			}
		});
	});
</script>
