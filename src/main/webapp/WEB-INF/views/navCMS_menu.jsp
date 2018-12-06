<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item nav-profile">
            <a href="#" class="nav-link">
                <div class="nav-profile-image">
                    <img src="https://www.timeshighereducation.com/sites/default/files/byline_photos/default-avatar.png"
                         alt="profile">
                    <span class="login-status online"></span> <!--change to offline or busy as needed-->
                </div>
                <div class="nav-profile-text d-flex flex-column">
                    <span class="font-weight-bold mb-2">Chào, ${username}</span>
                </div>
                <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_portal">
                <span class="menu-title">Trang chủ</span>
                <i class="mdi mdi-home menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-title">Quản lý ứng viên</span>
                <i class="menu-arrow"></i>
                <i class="menu-icon"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"><a class="nav-link" href="/management_candidates">Ứng viên</a></li>
                    <li class="nav-item"><a class="nav-link" href="/management_potentials">Ứng viên tiềm năng</a></li>
                </ul>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_majors">
                <span class="menu-title">Quản lý ngành nghề</span>
                <i class="mdi mdi-layers menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_positions">
                <span class="menu-title">Quản lý vị trí tuyển dụng</span>
                <i class="mdi mdi-debug-step-into menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_skills">
                <span class="menu-title">Quản lý kĩ năng</span>
                <i class="mdi mdi-language-javascript menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_recruimentProcess">
                <span class="menu-title">Quản lý tuyển dụng</span>
                <i class="menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/management_interviews">
                <span class="menu-title">Quản lý phỏng vấn</span>
                <i class="mdi mdi-face menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/admin">
                <span class="menu-title">Quản lý Users</span>
                <i class="mdi mdi-face menu-icon"></i>
            </a>
        </li>

    </ul>
</nav>