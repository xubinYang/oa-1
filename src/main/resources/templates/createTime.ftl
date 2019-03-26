
<!DOCTYPE HTML>
<html>
<head>
    <meta content="text/html, charset=utf-8" http-equiv="Content-Type" />
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" name="viewport" />
    <meta content="telephone=no" name="format-detection" />
    <meta charset="utf-8" />
    <meta content="view" name="decorator">
    <title>表单</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/frozenui.css"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/form-list.css" />
</head>
<body>
<header class="header bg-col-1">
    <article>新建任务</article>
    <a href="index.html" >
        <i class="iconright iconfont-p iconfont"></i>
    </a>
</header>
<section class="content">

    <form action="/addMeeting">
        <article class="art_wrap">
            <div class="art_title">会议日期：</div>
            <div class="art_con">
                <input type="date" id="meetingDate" name="meetingDate" placeholder="Email Address" class="input1 timeinput">
            </div>
        </article>

        <article class="art_wrap">
            <div class="art_title">开始时间：</div>
            <div class="art_con">
                <input type="time" id="beginTime" name="beginTime" placeholder="Email Address" class="input1 timeinput">
            </div>
        </article>
        <article class="art_wrap">
            <div class="art_title">结束时间：</div>
            <div class="art_con">
                <input type="time" id="endTime" name="endTime" placeholder="Email Address" class="input1 timeinput">
            </div>
        </article>

        <div class="ui-btn-wrap" id="address">
            <button type="button" class="ui-btn-lg ui-btn-primary" id="findAddress" onclick="findMeeting()">
                搜索会议室
            </button>

        </div>
        <div class="ui-btn-wrap" id="meeting">
            <button type="submit" class="ui-btn-lg ui-btn-primary" id="sub" >创建会议室</button>

        </div>
        <div id="showAddress">
            <input type="radio" id="addressid" name="addressid" value="" hidden>
        </div>


    </form>
</section>
<footer class="full-footer full-footer-placeholder">

</footer>

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
                    nowDom += '<div class="ui-form-item ui-form-item-radio ui-border-b" id="address2">' + data[i].addressname +
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
