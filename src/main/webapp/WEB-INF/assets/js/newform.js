$(document).on('ready', function () {
    var usersBlock = "#block-addusers";
    var userToAdd = "#adduser";
    var count = 2;
    $("#btn-addusers").on('click', function () {
        $(usersBlock).show();
        $("#btn-adduser").on('click', function (e) {
            e.preventDefault();
            if ($(userToAdd).val() !== "") {
                $($("#block-showusers")).append('<div style="display:inline;margin-left:5px">' + $(userToAdd).val() + '<a href="javascript:void(0)" class="removeUser" style="color:red" title="Remove this user"><span class="glyphicon glyphicon-remove"></span></a></div>');
                $(userToAdd).val("");
            }
            $(usersBlock).on('click', '.removeUser', function (e) {
                e.preventDefault();
                $(this).parent('div').remove();
            });
//                $.post(this.href, function (data) {
//                    if (data !== null) {
//                        $("#block-showusers").html(data);
//                    }
//                });
        });
        $("#btn-canceladduser").on('click', function () {
            $("#block-showusers").html("");
            $(userToAdd).val("");
            $(usersBlock).hide();
        });
    });
    var repeatBlock = $("#tbody");
    var repeatText = '</td><td><select name="formFieldName[]" id="formFieldName"><option value="">-------------------------------------</option><c:forEach var="ff" items="${formFields}"><option value="${ff.formFieldId}">${ff.formFieldName}</option></c:forEach></select></td><td><select name="formOptionType[]" id="formOptionType"><option value="">-------------------------------------</option><option value="text">Text</option><option value="textarea">Textarea</option><option value="select">Select Options (Dropdown)</option><option value="radio">Radio Buttons</option><option value="checkbox">Checkbox</option></select></td><td><label><input type="checkbox" name="fieldRequired[]"/> Required</label></td><td><a href="javascript:void(0)" class="removeField" id="btn-removefield" style="color:red" title="Remove this field"><span class="glyphicon glyphicon-remove"></span></a></td></tr>';
    $("#btn-addfield").click(function (e) {
        e.preventDefault();
        $(repeatBlock).append('<tr id="tr-formField"><td>' + count + repeatText);
        count++;
    });
    $(repeatBlock).on('click', '.removeField', function (e) {
        e.preventDefault();
        $(this).parent().parent().remove();
        count--;
    });
});