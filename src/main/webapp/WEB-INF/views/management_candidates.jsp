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
                    <h3 class="page-title">Quản lý ứng viên</h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Ứng viên</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Quản
                                lý ứng viên
                            </li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table" id="candidate-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên ứng viên</th>
                                            <th>Địa chỉ</th>
                                            <th>Số điện thoại</th>
                                            <th>Ngày sinh</th>
                                            <th>Email</th>
                                            <th>Mùa ứng tuyển</th>
                                            <th>Kĩ năng</th>
                                            <th>Vị trí ứng tuyển</th>
                                            <th>Chứng chỉ</th>
                                            <th>Kinh nghiệm</th>
                                            <th>Mô tả</th>
                                            <th>Là thí sinh tiềm năng</th>
                                            <th>Xem chi tiết</th>
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

    <script>
        let dataTable;
        let editor;

        let ajaxUrls = {
            getUrl: '/candidate/get/all',
            createUrl: '/candidate/dataTable/create',
            editUrl: '/candidate/dataTable/edit',
            removeUrl: '/candidate/dataTable/delete',
        };

        let fields = [
            {name: 'id', type: 'hidden'},
            {name: 'potential', type: 'hidden'},
            {label: 'Tên ứng viên', name: 'name'},
            {label: 'Địa chỉ', name: 'address'},
            {label: 'Số điện thoại', name: 'phoneNumber'},
            {label: 'Ngày sinh', name: 'dayOfBirth', type: 'datetime'},
            {label: 'Email', name: 'email'},
            {
                label: 'Mùa ứng tuyển',
                name: 'recruitment.name',
                type: 'select',
            },
            {
                label: 'Kĩ năng', name: 'skills[].name',
                type: 'checkbox'
            },
            {
                label: 'Vị trí ứng tuyển', name: 'positions[].name',
                type: 'checkbox'
            },
            {label: 'Kinh nghiệm', name: 'experience', type: 'textarea'},
            {label: 'Chứng chỉ', name: 'certificates', type: 'textarea'},
            {label: 'Mô tả', name: 'description', type: 'textarea'},
            {
                label: "Avatar",
                name: "image.id",
                type: "upload",
                ajax: "/uploadFile",
                display: function (file_id) {
                    return '<img src="' + editor.file('files', file_id).webPath + '"/>';
                },
                clearText: "Clear",
                noImageText: 'No image'
            },
            {
                label: "CV",
                name: "curriculumVitae.id",
                type: "upload",
                ajax: "/uploadFile",
                display: function (file_id) {
                    return editor.file('files', file_id).webPath;
                },
                clearText: "Clear",
                dragDrop: false,
            }
        ];

        let columns = [
            {data: 'id'},
            {data: 'name'},
            {data: 'address'},
            {data: 'phoneNumber'},
            {
                data: 'dayOfBirth',
                render: (date) => date.day + '/' + date.month + '/' + date.year
            },
            {data: 'email'},
            {
                data: 'recruitment',
                render: '.name'
            },
            {data: 'skills', render: '[, ].name'},
            {data: 'positions', render: '[, ].name'},
            {data: 'certificates', render: '[, ]'},
            {data: 'experience', render: '[, ]'},
            {data: 'description'},
            {data: 'potential'},
            {
                data: null,
                orderable: false,
                searchable: false,
                render: (data, type, row) =>
                    "<a href='/candidate_detail/" + row['id'] + "'>Xem chi tiết</a>",
            },
        ];


        $(document).ready(function () {
            let hiddenFields = ['potential', 'curriculumVitae.id', 'image.id'];
            editor = validatedEditor(createEditor('candidate-table', ajaxUrls, fields), hiddenFields);

            let buttons = [
                {extend: "create", editor: editor},
                {extend: "edit", editor: editor},
                {extend: "remove", editor: editor},
                {
                    extend: "selectedSingle",
                    text: 'Thêm vào thí sinh tiềm năng',
                    className: 'btn btn-success',
                    action: function (e, dt, node, config) {
                        let row = dataTable.row({selected: true});
                        editor
                            .edit(row, false)
                            .set('potential', 'true')
                            .submit();
                    }
                },
            ];

            dataTable = createDataTable($('#candidate-table'), ajaxUrls, columns, buttons);

            editor.on('preOpen', function (action) {
                if (action !== 'delete') {
                    updateEditorField(editor, 'recruitment.name', '/recruitment/get/all');
                    updateEditorField(editor, 'skills[].name', '/skill/get/all');
                    updateEditorField(editor, 'positions[].name', '/position/get/all');
                }
            });
        })
        ;
    </script>
</div>
<!-- /#wrapper -->

</body>
</html>
