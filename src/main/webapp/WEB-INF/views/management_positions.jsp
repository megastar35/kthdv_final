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
                    <h3 class="page-title">Quản lý vị trí</h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="positions-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Lương</th>
                                            <th>Ngành</th>
                                            <th>Kĩ năng</th>
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
    let fields = [{
        name: 'id',
        type: 'hidden'
    }, {
        label: 'Vị trí',
        name: 'name',
    }, {
        label: 'Lương',
        name: 'salary',
    }, {
        label: 'Ngành',
        name: 'major.name',
        type: 'select',
    }, {
        label: 'Kĩ năng',
        name: 'skills[].name',
        type: 'checkbox',
    }];
    let ajaxUrls = {
        getUrl: '/position/get/all',
        createUrl: '/position/dataTable/create',
        editUrl: '/position/dataTable/edit',
        removeUrl: '/position/dataTable/delete',
    };
    let columns = [{
        data: 'id'
    }, {
        data: 'name'
    }, {
        data: 'salary',
    }, {
        data: 'major',
        render: '.name'
    }, {
        data: 'skills',
        render: "[, ].name",
    },];

    let editor;
    let dataTable;

    $(document).ready(
        function () {
            editor = validatedEditor(createEditor('positions-table',
                ajaxUrls, fields));

            dataTable = createDataTable($("#positions-table"),
                ajaxUrls, columns);

            editor.on('preOpen', function (action) {
                if (action !== 'remove') {
                    updateEditorField(editor, 'major.name', '/major/get/all');
                    updateEditorField(editor, 'skills[].name', '/skill/get/all');
                }
            });
        });
</script>
</body>
</html>
