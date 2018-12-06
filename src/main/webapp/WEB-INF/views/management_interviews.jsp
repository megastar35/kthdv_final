<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
                    <h3 class="page-title">Quản lý phỏng vấn</h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">

                                    <table id="interviews-table">
                                        <thead>
                                        <tr>
                                            <th>ID Phỏng vấn</th>
                                            <th>Người phỏng vấn</th>
                                            <th>Ứng viên tiềm năng</th>
                                            <th>Ngày phỏng vấn</th>
                                            <th>Điểm phỏng vấn (Thang 100)</th>
                                            <th>Mô tả</th>
                                        </tr>
                                        </thead>
                                    </table>

                                </div>
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
<!-- /#page-wrapper -->

<!-- /#wrapper -->

<script>
    let fields = [{
        name: 'id',
        type: 'hidden'
    }, {
        label: 'Người phỏng vấn',
        name: 'interviewer.name',
        type: 'select'
    }, {
        label: 'Ứng viên tiềm năng',
        name: 'candidate.name',
        type: 'select'
    }, {
        label: 'Ngày phỏng vấn',
        name: 'interviewDate',
        type: 'datetime',
        def: function () {
            return new Date();
        }
    }, {
        label: 'Điểm phỏng vấn',
        name: 'interviewScore'
    }, {
        label: 'Mô tả',
        name: 'description'
    }];
    let ajaxUrls = {
        getUrl: '/interview/get/all',
        createUrl: '/interview/dataTable/create',
        editUrl: '/interview/dataTable/edit',
        removeUrl: '/interview/dataTable/delete',
    };
    let columns = [{
        data: 'id'
    }, {
        data: 'interviewer',
        render: ".name"
    }, {
        data: 'candidate',
        render: ".name"
    }, {
        data: 'interviewDate',
        render: (d) => d.day + "/" + d.month + "/" + d.year,
    }, {
        data: 'interviewScore'
    }, {
        data: 'description'
    }];
    let editor;
    let dataTable;

    $(document).ready(function () {

        editor = validatedEditor(createEditor('interviews-table',
            ajaxUrls, fields));

        dataTable = createDataTable($("#interviews-table"),
            ajaxUrls, columns);

        editor.on('preOpen', function (action) {
            if (action !== 'remove') {
                updateEditorField(editor, 'candidate.name', '/potential/get/all')
                updateEditorField(editor, 'interviewer.name',
                    '/admin/get/all');
            }
        });

    });
</script>
</body>
</html>
