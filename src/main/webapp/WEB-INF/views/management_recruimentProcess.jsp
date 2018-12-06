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
                    <h3 class="page-title">Quản lý quá trình tuyển dụng</h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="recruitments-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên</th>
                                            <th>Ngày bắt đầu</th>
                                            <th>Ngày kết thúc</th>
                                            <th>Số lượng cần tuyển</th>
                                            <th>Chuyên ngành</th>
                                            <th>Vị trí</th>
                                            <th>Kĩ năng cần tuyển</th>
                                            <th>Mô tả thêm</th>
                                            <th>Hình tuyển dụng</th>
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
<!-- /#wrapper -->
<script>

    let fields = [
        {name: 'id', type: 'hidden'},
        {label: 'Tên', name: 'name'},
        {
            type: 'datetime',
            label: 'Ngày bắt đầu',
            name: 'startDate',
            def: function () {
                return new Date();
            },
        },
        {
            label: 'Ngày kết thúc',
            name: 'finishDate',
            type: 'datetime',
            def: function () {
                return new Date();
            },
        },
        {
            label: 'Số lượng cần tuyển',
            name: 'numberOfRecruits',
        },
        {
            label: 'Ngành',
            name: 'majors[].name',
            type: 'checkbox',
            options: [],
        },
        {
            label: 'Vị trí',
            name: 'positions[].name',
            type: 'checkbox',
            options: [],
        },
        {
            label: 'Kĩ năng',
            name: 'skills[].name',
            type: 'checkbox',
            options: [],
        },
        {label: 'Mô tả thêm', name: 'description', type: 'textarea'},
        {
            label: "Hình tuyển dụng",
            name: "image.id",
            type: "upload",
            ajax: "/uploadFile",
            display: function (file_id) {
                return '<img src="' + editor.file('files', file_id).webPath + '"/>';
            },
            clearText: "Clear",
            noImageText: 'No image'
        },
    ];

    let ajaxUrls = {
        getUrl: '/recruitment/get/all',
        createUrl: '/recruitment/dataTable/create',
        editUrl: '/recruitment/dataTable/edit',
        removeUrl: '/recruitment/dataTable/delete',
    };

    let columns = [
        {data: 'id'},
        {data: 'name'},
        {
            data: 'startDate',
            render: (d) => d.day + "/" + d.month + "/" + d.year
        },
        {
            data: 'finishDate',
            render: (d) => d.day + "/" + d.month + "/" + d.year
        },
        {data: 'numberOfRecruits'},
        {
            data: 'majors',
            render: "[, ].name"
        },
        {
            data: 'positions',
            render: "[, ].name"
        },
        {
            data: 'skills',
            render: "[, ].name"
        },
        {data: 'description'},
        {
            data: 'image.id',
            render: function (file_id) {
                return file_id ?
                    '<img width="150" height="150" src="' + editor.file('files', file_id).webPath + '"/>' :
                    null;
            },
            defaultContent: "Không có hình ảnh",
        }
    ];

    let editor;
    let dataTable;
    $(document).ready(function () {

        editor = validatedEditor(createEditor('recruitments-table', ajaxUrls, fields));

        dataTable = createDataTable($("#recruitments-table"), ajaxUrls, columns);

        editor.on('preOpen', function (action) {
            if (action !== 'delete') {
                updateEditorField(editor, 'majors[].name', '/major/get/all');
                updateEditorField(editor, 'positions[].name', '/position/get/all');
                updateEditorField(editor, 'skills[].name', '/skill/get/all');
            }
        });
    });
</script>
</body>
</html>
