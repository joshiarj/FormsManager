<%@include file="../shared/header.jsp" %>
<c:set var="fieldCount"  value="1" />
<link href="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
<script src="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.js" type="text/javascript"></script>
<style type="text/css">
    table.vertical-align > tbody > tr > td {vertical-align:middle;}
</style>
<h3 align="center">Create New Form</h3>
<c:if test="${param.success!=null}">
    <div style="color:green">Form successfully saved!</div>
</c:if>
<form method="post">
    <!--action="${SITE_URL}/form/save">-->
    <div id="form-fixedtop">
        <div class="form-group">
            <label>Form Title:</label>
            <input type="text" name="formTitle" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Form Description</label>
            <textarea name="formDescription" class="form-control"></textarea>
        </div>
    </div>
    <div style="font-weight:bold">Choose Form Fields:</div>
    <table id="tbl-formfields" class="table vertical-align table-condensed" >
        <tr>
            <th width="4%">#</th>
            <th width="38%">Field</th>
            <th width="38%">Input Type</th>
            <th width="12%">Make Required</th>
            <th width="8%">Action</th>
        </tr>
        <tbody id="tbody">
            <tr id="tr-formField">
                <td>1</td>
                <td>
                    <select name="formFieldName[]" id="formFieldName">
                        <option value="">-------------------------------------</option>
                        <c:forEach var="ff" items="${formFields}">
                            <option value="${ff.formFieldId}">${ff.formFieldName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <div>
                        <span>
                            <select name="formOptionType[]" id="formOptionType">
                                <option value="">-------------------------------------</option>
                                <option value="text">Text</option>
                                <option value="textarea">Textarea</option>
                                <option value="select">Select Options (Dropdown)</option>
                                <option value="radio">Radio Buttons</option>
                                <option value="checkbox">Checkbox</option>
                            </select>
                        </span>
                    </div>
                    <div id="block-optionsInput" style="display:none">
                        <label>Options:</label><br>
                        <input id="options" type="text" name="options" data-role="tagsinput"/><!-- value="Male,Female"/>-->
                    </div>
                </td>
                <td>
                    <label><input type="checkbox" name="fieldRequired[]"/> Required</label>
                </td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <div>
        <button type="button" id="btn-addfield" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus"></span> Add Field
        </button>
        <button type="button" id="btn-addusers" class="btn btn-info">
            <span class="glyphicon glyphicon-user"></span> Add Users
        </button>
    </div>
    <div id="block-addusers" class="form-group" style="display:none">
        <div id="block-showusers" style="height:25px;vertical-align:bottom"></div>
        <div>
            <input type="text" name="adduser" id="adduser" placeholder="Add Users..."/>
            <a href="${SITE_URL}/user/search" id="btn-adduser" class="btn btn-default">
                Add
            </a>
            <button type="button" id="btn-canceladduser" class="btn btn-link">Cancel</button>
        </div>
    </div><br>
    <button type="button" id="btn-saveform" class="btn btn-success">
        <span class="glyphicon glyphicon-floppy-disk"></span> Save This Form
    </button>
</form>

<c:set var="fieldCount"  value="${fieldCount+1}" />
<script>
    $(document).on('ready', function () {

        //****Add users who can modify/view form****
        var usersBlock = "#block-addusers";
        var userToAdd = "#adduser";
        $("#btn-addusers").on('click', function () {
            $(usersBlock).show();
            $("#btn-adduser").on('click', function (e) {
                e.preventDefault();
//                if ($(userToAdd).val() !== "") {
//                    $($("#block-showusers")).append('<div style="display:inline;margin-left:5px">' + $(userToAdd).val() + '<a href="javascript:void(0)" class="removeUser" style="color:red" title="Remove this user"><span class="glyphicon glyphicon-remove"></span></a></div>');
//                    $(userToAdd).val("");
//                } 
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: '${SITE_URL}/user/search',
                    data: {adduser: $(userToAdd).val(), },
//                    dataType: "json",
                    success: function (data) {
                        alert($.parseJSON(data.result[0]));
                        if (data !== "") {
//                            var resultData = JSON.stringify(data);
                            $("#block-showusers").append('<div style="display:inline;margin-left:5px">' + data.result[0].username + '</div>');
//                            $.each(data, function (result) {
//                                $("#block-showusers").append('<div style="display:inline;margin-left:5px">' + result.username + '</div>');
//                            });
                        }
                    }
                });
//                $.post(this.href, {adduser:$(userToAdd).val()}, function (data) {
//                    if (data !== null) {
//                        $("#block-showusers").append('<div style="display:inline;margin-left:5px">' + data + '</div>');
//                    }
//                    $(userToAdd).val("");
//                },"json");
                $(usersBlock).on('click', '.removeUser', function (e) {
                    e.preventDefault();
                    $(this).parent('div').remove();
                });
            });
            $("#btn-canceladduser").on('click', function () {
                $("#block-showusers").html("");
                $(userToAdd).val("");
                $(usersBlock).hide();
            });
        });

//        inputOptions();

        //****Input options for dropdown/radio****
//        function inputOptions() {
        var optType = "#formOptionType";
        var optBlock = "#block-optionsInput";
        $(optType).change(function () {
            if ($(optType).val() === "radio" || $(optType).val() === "select") {
                $(optBlock).show();
            } else {
                $(optBlock).hide();
            }
        });
//        }

        //****Repeat form field block****
        var count = 2;
        var repeatBlock = $("#tbody");
        var repeatText = '</td><td><select name="formFieldName[]" id="formFieldName"><option value="">-------------------------------------</option><c:forEach var="ff" items="${formFields}"><option value="${ff.formFieldId}">${ff.formFieldName}</option></c:forEach></select></td><td><select class="optType" name="formOptionType[]" id="formOptionType"><option value="">-------------------------------------</option><option value="text">Text</option><option value="textarea">Textarea</option><option value="select">Select Options (Dropdown)</option><option value="radio">Radio Buttons</option><option value="checkbox">Checkbox</option></select><div class="optBlock" id="block-optionsInput" style="display:none"><br><input class="optInput" type="text" name="options" data-role="tagsinput"/></div></td><td><label><input type="checkbox" name="fieldRequired[]"/> Required</label></td><td><a href="javascript:void(0)" class="removeField" id="btn-removefield" style="color:red" title="Remove this field"><span class="glyphicon glyphicon-remove"></span></a></td></tr>';
        $(repeatBlock).on('change', $(".optType").val(), function (e) {
            e.preventDefault();
            if ($(".optType").val() === "radio" || $(".optType").val() === "select") {
                $(this).parent().find(".optBlock").show();
                $(".optInput").tagsinput('refresh');
            } else {
                $(this).parent().find(".optBlock").hide();
            }
        });
//        inputOptions();
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
//        $("#btn-saveform").on('click', function () {
//            console.log($(".optInput").val());
//        });
    });
    </script>
    <!--<script src="${SITE_URL}/static/js/newform.js"></script>-->
<%@include file="../shared/footer.jsp" %>
