<%@include file="../shared/header.jsp" %>
<%--<c:set var="fieldCount"  value="1" />--%>
<link href="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
<script src="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.js" type="text/javascript"></script>
<style type="text/css">
    table.vertical-align > tbody > tr > td {vertical-align:middle;}
</style>
<h3 align="center">Create New Form</h3>
<c:if test="${param.success!=null}">
    <div style="color:green">Form successfully saved!</div>
</c:if>
    <form method="post" action="${SITE_URL}/form/save">
    <div id="form-fixedtop">
        <div class="form-group">
            <label>Form Title:</label>
            <input type="text" name="title" class="form-control" required="required"/>
        </div>
        <div class="form-group">
            <label>Form Description</label>
            <textarea name="description" class="form-control" required="required"></textarea>
        </div>
        <input type="hidden" name="userId" value="1"/>
    </div>
    <div style="font-weight:bold">Choose Form Fields:</div>
    <table id="tbl-formfields" class="table vertical-align table-condensed" >
        <thead>
            <tr>
                <th width="10%">Display Order</th>
                <th width="33%">Field</th>
                <th width="33%">Input Type</th>
                <th width="12%">Make Required</th>
                <th width="12%">Action</th>
            </tr>
        </thead>
        <tbody id="tbody">
            <tr id="tr-formField">
                <td>
                    <select name="formOptionDisplayOrder" id="formOptionDisplayOrder">
                        <c:forEach begin="0" end="9" step="1" varStatus="loop">
                            <option value="${loop.count}"><c:out value="${loop.count}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="formFieldName" id="formFieldName">
                        <option value="">-------------------------------------</option>
                        <c:forEach var="ff" items="${formFields}">
                            <option value="${ff.formFieldId}">${ff.formFieldName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <div>
                        <span>
                            <select name="formOptionType" id="formOptionType">
                                <option value="">-------------------------------------</option>
                                <option value="text">Text</option>
                                <option value="password">Password</option>
                                <option value="textarea">Textarea</option>
                                <option value="checkbox">Checkbox</option>
                                <option value="select">Select Options (Dropdown)</option>
                                <option value="radio">Radio Buttons</option>
                                <option value="file">File</option>
                            </select>
                        </span>
                    </div>
                    <div id="block-optionsInput" style="display:none">
                        <label>Options:</label><br>
                        <input id="options" type="text" name="fieldOptions" data-role="tagsinput" value=""/><!-- value="Male,Female"/>-->
                    </div>
                </td>
                <td>
                    <label><input type="checkbox" name="fieldRequired"/> Required</label>
                </td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <div>
        <button type="button" id="btn-addfield" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus"></span> Add Field
        </button>
        <!--        <button type="button" id="btn-addusers" class="btn btn-info">
                    <span class="glyphicon glyphicon-user"></span> Add Users
                </button>-->
    </div>
    <!--    <div id="block-addusers" class="form-group" style="display:none">
            <div id="block-showusers" style="height:25px;vertical-align:bottom"></div>
            <div>
                <input type="text" name="adduser" id="adduser" placeholder="Add Users..."/>
                <a href="${SITE_URL}/user/search" id="btn-adduser" class="btn btn-default">
                    Add
                </a>
                <button type="button" id="btn-canceladduser" class="btn btn-link">Cancel</button>
            </div>
        </div>-->
    <br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit" id="btn-saveform" class="btn btn-success">
        <span class="glyphicon glyphicon-floppy-disk"></span> Save This Form
    </button>
</form>

<%--<c:set var="fieldCount"  value="${fieldCount+1}" />--%>
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
                    contentType: "application/x-www-form-urlencoded",
//                    contentType: "application/json",
                    url: '${SITE_URL}/user/search',
                    data: {adduser: $(userToAdd).val()},
//                    dataType: "json",
                    success: function (data) {
                        console.log(data.userId);
                        alert($.parseJSON(data));
                        if (data !== "") {
//                            var resultData = JSON.stringify(data);
                            $("#block-showusers").html('<div style="display:inline;margin-left:5px">' + data.username + '</div>');
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

        //****Input options for dropdown/radio****
        var optType = "#formOptionType";
        var optBlock = "#block-optionsInput";
        $(optType).change(function () {
            if ($(optType).val() === "radio" || $(optType).val() === "select") {
                $(optBlock).show();
            } else {
                $(optBlock).hide();
            }
        });

        //****Repeat form field block****
        var repeatBlock = "#tbody";
        var repeatText = '<td>\n\
                <select name="formOptionDisplayOrder" id="formOptionDisplayOrder">\n\
                    <c:forEach begin="0" end="9" step="1" varStatus="loop">\n\
                        <option value="${loop.count}"><c:out value="${loop.count}"/></option>\n\
                    </c:forEach>\n\
                </select>\n\
            </td>\n\
            <td><select name="formFieldName">\n\
                <option value="">-------------------------------------</option>\n\
                <c:forEach var="ff" items="${formFields}">\n\
                    <option value="${ff.formFieldId}">${ff.formFieldName}</option>\n\
                </c:forEach>\n\
            </select></td>\n\
            <td>\n\
                <select class="optType" name="formOptionType">\n\
                    <option value="">-------------------------------------</option>\n\
                    <option value="text">Text</option>\n\
                    <option value="password">Password</option>\n\
                    <option value="textarea">Textarea</option>\n\
                    <option value="checkbox">Checkbox</option>\n\
                    <option value="select">Select Options (Dropdown)</option>\n\
                    <option value="radio">Radio Buttons</option>\n\
                    <option value="file">File</option>\n\
                </select>\n\
                <div class="optBlock" style="display:none">\n\
                    <label>Options:</label><br>\n\
                    <input class="optInput" type="text" name="fieldOptions" data-role="tagsinput" value=""/>\n\
                </div>\n\
            </td>\n\
            <td><label><input type="checkbox" name="fieldRequired"/> Required</label></td>\n\
            <td><a href="javascript:void(0)" class="removeField" style="color:red" title="Remove this field">\n\
                <span class="glyphicon glyphicon-remove"></span></a>\n\
            </td></tr>';
        $("#btn-addfield").click(function (e) {
            e.preventDefault();
            $(repeatBlock).append('<tr class="trRepeat">' + repeatText);
        });
        $(repeatBlock).on('click', '.removeField', function (e) {
            e.preventDefault();
            $(this).parent().parent().remove();
        });

        //****Show input field when dropdown/radio is selected****
        $(repeatBlock).on('change', '.optType', function (e) {
            e.preventDefault();
            var val = $(this).val();
            if (val === "radio" || val === "select") {
                $(this).closest('tr').find(".optBlock").show();
                $(this).closest('tr').find(".optBlock").find(".optInput").tagsinput('refresh');
            } else {
                $(this).closest('tr').find(".optBlock").hide();
//                $(this).closest('tr').find(".optBlock").find(".optInput").reset();
            }
        });

//        $("#btn-saveform").on('click', function () {
//            console.log($(".optInput").val());
//        });
    });
</script>
<!--<script src="${SITE_URL}/static/js/newform.js"></script>-->
<%@include file="../shared/footer.jsp" %>
