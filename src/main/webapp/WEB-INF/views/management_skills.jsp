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
                    <h3 class="page-title">Quản lý kĩ năng</h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="skills-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
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
    let editorFields = [{
        name: 'id',
        type: 'hidden'
    }, {
        label: 'Tên',
        name: 'name'
    }, {
        label: 'Mô tả',
        name: 'description',
        type: 'textarea'
    }];

    let ajaxUrls = {
        getUrl: '/skill/get/all',
        createUrl: '/skill/dataTable/create',
        editUrl: '/skill/dataTable/edit',
        removeUrl: '/skill/dataTable/delete',
    };

    let columns = [{
        data: 'id'
    }, {
        data: 'name'
    }, {
        data: 'description',
        orderable: false,
        searchable: false
    },];

    let editor;
    let dataTable;

    $(document).ready(
        function () {
            editor = validatedEditor(createEditor('skills-table',
                ajaxUrls, editorFields));

            dataTable = createDataTable($("#skills-table"), ajaxUrls,
                columns);
        });
</script>
</body>
</html>
