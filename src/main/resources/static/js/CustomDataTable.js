let editorLanguage = {
    "create": {
        "button": "Tạo mới",
        "title": "Tạo dữ liệu mới",
        "submit": "Đồng ý"
    },

    "edit": {
        "button": "Sửa",
        "title": "Chỉnh sửa dữ liệu",
        "submit": "Cập nhật"
    },

    "remove": {
        "button": "Xóa",
        "title": "Xóa",
        "submit": "Xóa",
        "confirm": {
            "_": "Bạn có chắc chắn xóa %d dòng này?",
            "1": "Bạn có chắc chắn xóa bỏ dòng này?"
        }
    },

    "error": {
        "system": "A system error has occurred (More information)"
    },

    "datetime": {
        "previous": 'Trước',
        "next": 'Tiếp theo',
        "months": ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
        "weekdays": ['CN', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7'],
        "amPm": ['Sáng', 'Chiều'],
        "unknown": '-'
    }
};

function createDataTable($table, ajaxUrls, columns, buttons) {
    if (buttons === undefined) {
        buttons = [
            {extend: "create", editor: editor},
            {extend: "edit", editor: editor},
            {extend: "remove", editor: editor}
        ];
    }
    return $table.DataTable({
        ajax: ajaxUrls.getUrl,
        dom: "Bfrtip",
        responsive: true,
        paging: false,
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.10.19/i18n/Vietnamese.json'
        },
        columns: columns,
        select: {
            style: 'single'
        }, // select row for edit and remove
        buttons: buttons
    });
}

function createEditor(tableId, ajaxUrls, fields) {
    return new $.fn.dataTable.Editor({
        url: ajaxUrls.getUrl,
        table: "#" + tableId,
        idSrc: 'id',
        display: 'bootstrap',
        i18n: editorLanguage,
        fields: fields,
        serverSide: true,
        ajax: {
            create: {
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                url: ajaxUrls.createUrl,
                data: (d) => stringifyDataEditor(d),
            },
            edit: {
                type: 'PUT',
                contentType: 'application/json; charset=utf-8',
                url: ajaxUrls.editUrl,
                data: (d) => stringifyDataEditor(d),
            },
            remove: {
                type: 'POST',
                contentType: 'application/json; charset=utf-8',
                url: ajaxUrls.removeUrl,
                data: (d) => stringifyDataEditor(d),
            }
        },
    });
}

function updateEditorField(editor, fieldName, ajaxUrl) {
    $.ajax({
        url: ajaxUrl,
        type: 'GET',
        success: function (d) {
            let names = [];
            $.each(d.data, function (key, value) {
                names.push(value.name);
            });
            editor.field(fieldName).update(names);
        }
    });
}

function validatedEditor(editor, hiddenColumns) {
    let hidden = hiddenColumns === undefined ? ['id'] : hiddenColumns.concat(Array.of('id'));
    editor.on('preSubmit', function (e, o, action) {
        if (action !== 'remove') {
            editor.fields().forEach(function (fieldName) {
                if (!hidden.includes(fieldName)) { // ignore hidden columns
                    let field = editor.field(fieldName);
                    if (!field.isMultiValue()) { // check if input type
                        if (!field.val()) { // empty input
                            field.error('Không được để trống');
                        }
                    }
                }
            });

            if (this.inError()) {
                return false;
            }
        }
    });
    return editor;
}

function stringifyDataEditor(d) {
    let newData;
    $.each(d.data, function (key, value) {
        newData = JSON.stringify(value);
        console.log(newData);
    });
    return newData;
}

