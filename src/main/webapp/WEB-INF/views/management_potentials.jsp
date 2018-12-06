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
                    <h3 class="page-title">Quản lý ứng cử viên tiềm năng</h3>
                </div>
                <div class="row">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table id="candidate-table">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tên ứng viên</th>
                                            <th>ID bài thi đầu vào</th>
                                            <th>Điểm bài thi đầu vào</th>
                                            <th>Nhận vào làm?</th>
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

<script>
    let columns = [
        {data: 'id'},
        {data: 'name'},
        {data: 'entryTests', render: '[, ].name'},
        {data: 'testScores', render: '[, ]'},
        {data: 'suitable'},
    ];

    let editorFields = [
        {name: 'id', type: 'hidden'},
        {label: 'Tên', name: 'name'},
        {label: 'Test đầu vào', name: 'entryTests[].name', type: 'checkbox'},
        {label: 'Điểm đầu vào', name: 'testScores'},
        {
            name: 'suitable',
            type: 'hidden',
        }
    ];

    let ajaxUrls = {
        getUrl: '/potential/get/all',
        createUrl: '/potential/dataTable/create',
        editUrl: '/potential/dataTable/edit',
        removeUrl: '/potential/dataTable/delete',
    };

    let dataTable;

    let editor;

    $(document).ready(function () {
        editor = validatedEditor(createEditor('candidate-table', ajaxUrls, editorFields), ['suitable']);

        let buttons = [
            {extend: "edit", editor: editor},
            {extend: "remove", editor: editor},
            {
                extend: "selectedSingle",
                text: 'Nhận vào làm',
                className: 'btn btn-success',
                action: function (e, dt, node, config) {
                    // Immediately add `250` to the value of the salary and submit
                    // editor
                    //     .remove(dataTable.row({selected: true}), false).submit();
                    var row = dataTable.row({selected: true});
                    editor
                        .edit(row, false)
                        .set('suitable', 'true')
                        .submit();
                }
            },
        ];

        dataTable = createDataTable($('#candidate-table'), ajaxUrls, columns, buttons);

        editor.on('preOpen', function (e, o, action) {
            editor.field('suitable').set('false');
            updateEditorField(editor, 'entryTests[].name', '/entry-test/get/all');
        });
    });
</script>
</body>
</html>
