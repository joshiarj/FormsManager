<%@include file="../shared/header.jsp" %>
<c:set var="i"  value="0" />
<link href="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.css" rel="stylesheet" type="text/css"/>
<script src="${SITE_URL}/static/bootstrap/tagsinput/bootstrap-tagsinput.js" type="text/javascript"></script>
<script>var count=1;</script>
<style type="text/css">
    table.vertical-align > tbody > tr > td {vertical-align:middle;}
</style>
<h3 align="center">Create New Form</h3>
<c:if test="${param.success!=null}">
    <div style="color:green">Form successfully saved!</div>
</c:if>
<form method="post" action="${SITE_URL}/form/save">
    <!--modelAttribute="formOptDTO">-->
    <!--enctype="form-data">-->
    <div id="form-fixedtop">
        <div class="form-group">
            <label>Form Title:</label>
            <input type="text" name="title" class="form-control" required="required"/>
        </div>
        <div class="form-group">
            <label>Form Description:</label>
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
                    <select name="formOptionDisplayOrder[0]" id="formOptionDisplayOrder" class="ddown">
                        <c:forEach begin="0" end="9" step="1" varStatus="loop">
                            <option value="${loop.count}"><c:out value="${loop.count}"/></option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="formFieldName[0]" id="formFieldName" class="ddown">
                        <option value="">-------------------------------------</option>
                        <c:forEach var="ff" items="${formFields}">
                            <option value="${ff.formFieldId}">${ff.formFieldName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="formOptionType[0]" id="formOptionType" class="optType ddown">
                        <option value="">-------------------------------------</option>
                        <c:forEach var="ot" items="${optTypes}">
                            <c:set var="optValue" value="${fn:split(ot,' ')}"/>
                            <option value="${fn:toLowerCase(optValue[0])}">${ot}</option>
                        </c:forEach>
                    </select>
                    <div id="block-optionsInput" style="display:none">
                        <label>Options (comma-separated):</label><br>
                        <input id="fieldOptions" type="text" name="fieldOptions[0]" value=""/>
                    </div>
                </td>
                <td>
                    <label><input type="checkbox" id="fieldRequired" name="fieldRequired[0]" value="true"/> Required</label>
                </td>
            </tr>
        </tbody>
    </table>
    <div>
        <input type="hidden" id="field-count" value="0"/>
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

<c:set var="i"  value="${i+1}" />
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
//        var optType = "#formOptionType";
//        var optBlock = "#block-optionsInput";
//        $(optType).change(function () {
//            if ($(optType).val() === "radio" || $(optType).val() === "select") {
//                $(optBlock).show();
//            } else {
//                $(optBlock).hide();
//            }
//        });

        //****Repeat form field block****
        var repeatBlock = "#tbody";
        var repeatField = "#tr-formField";
        var delButton = '<td><a href="javascript:void(0)" class="removeField" style="color:red" title="Remove this field">\n\
                        <span class="glyphicon glyphicon-remove"></span></a></td>';
        var counter = 0;
        $("#btn-addfield").click(function (e) {
            e.preventDefault();
            counter++;
            var repeatedField = $(repeatField).clone().append(delButton).appendTo($(repeatBlock));
            repeatedField.find("#block-optionsInput").hide();
            repeatedField.find('#formOptionDisplayOrder').attr('name', 'formOptionDisplayOrder['+counter+']');
            repeatedField.find('#formFieldName').attr('name', 'formFieldName['+counter+']');
            repeatedField.find('#formOptionType').attr('name', 'formOptionType['+counter+']');
            repeatedField.find('#fieldOptions').attr('name', 'fieldOptions['+counter+']');
            repeatedField.find('#fieldRequired').attr('name', 'fieldRequired['+counter+']');
//            repeatedField.find('.ddown').attr('name', '['+counter+']')
        });
        
        //****Remove field/row****
        $(repeatBlock).on('click', '.removeField', function (e) {
            e.preventDefault();
            $(this).parent().parent().remove();
        });

        //****Show input field when dropdown/radio is selected****
        $(repeatBlock).on('change', '.optType', function (e) {
            e.preventDefault();
            var val = $(this).val();
            if (val === "radio" || val === "select") {
                $(this).closest('tr').find("#block-optionsInput").show();
                $(this).closest('tr').find("#block-optionsInput").find("#fieldOptions").val('');
//                $(this).closest('tr').find("#block-optionsInput").find("#options").tagsinput('refresh');
            } else {
                $(this).closest('tr').find("#block-optionsInput").hide();
//                $(this).closest('tr').find("#block-optionsInput").find("#options").tagsinput('removeAll');
            }
        });

//        $("#btn-saveform").on('click', function () {
//            console.log($(".optInput").val());
//        });
    });
</script>
<!--<script src="${SITE_URL}/static/js/newform.js"></script>-->
<%@include file="../shared/footer.jsp" %>
