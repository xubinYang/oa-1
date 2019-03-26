<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <title>FrozenUI Demo</title>
    <!-- 引入 FrozenUI -->
    <!--<link rel="stylesheet" href="../static/css/oa.css"/>-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/frozenui.css"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>



    <div class="ui-col ui-col-4" >
        <h1 class="title">创建会议</h1>
        <hr>
        <form action="/addMeeting">
            <div class="ui-form-item ui-border-b">
                <label>
                    会议日期：
                </label>
                <input type="date" id="meetingDate" name="meetingDate" placeholder="Email Address">
                <a href="#" class="ui-icon-close">
                </a>
            </div>
            <div class="ui-form-item ui-border-b">
                <label>
                    开始时间：
                </label>
                <input type="time" id="beginTime" name="beginTime" placeholder="Email Address">
                <a href="#" class="ui-icon-close">
                </a>
            </div>
            <div class="ui-form-item ui-border-b">
                <label>
                    结束时间：
                </label>
                <input type="time" class="form-control form-control-user" id="endTime" name="endTime"
                       placeholder="Email Address">

            </div>

            <div class="ui-btn-wrap" id="showButton">
                <button type="button" class="ui-btn-lg ui-btn-primary" id="findAddress" onclick="findMeeting()">
                    搜索会议室
                </button>

            </div>
            <div class="ui-btn-wrap" id="showButton">
                <button type="submit" class="ui-btn-lg ui-btn-primary" id="sub" >创建会议室</button>

            </div>
            <div id="showAddress">
                <input type="radio" id="addressid" name="addressid" value="" hidden>
            </div>
        </form>
    </div>



<script type="text/javascript">
    $('input[name="addressid"]').change(function () {
        var button = '<button type="submit" class="ui-btn-lg ui-btn-primary" id="sub">创建会议室</button>';
        if($('input[name="addressid"]').val() != null){
            $("#showButton").append(button);
        }
    });



    function findMeeting() {

        $.post("/findMeeting", {
            meetingDate: $("#meetingDate").val(),
            beginTime: $("#beginTime").val(),
            endTime: $("#endTime").val()
        }, function (data) {

            $('#showAddress').empty();
            var nowDom = '';
            for (var i in data) {
                if (data[i].status === 1) {
                    nowDom += '<div class="ui-form-item ui-form-item-radio ui-border-b" id="address">\n' +
                            '<label class="ui-radio" for="addressRadio'+ data[i].addressid + '">\n'+
                            '       <input type="radio"  id="addressid ' + '"\n' +
                            '           value="' + data[i].addressid + '"' + 'name="addressid'+ '"\n' +
                            '           onchange="radioChecked(this)">' + data[i].addressname +
                            '</div>\n' +
                            '</label>\n' +
                            '<hr>\n';
                } else {
                    nowDom += '<div class="form-group" id="address">' + data[i].addressname +
                        '</div>\n' +
                        '<hr>\n';
                }
            }
            $('#showAddress').append(nowDom);
        });
    }




</script>

</body>
</html>
