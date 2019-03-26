<!DOCTYPE HTML>
<html>
<head>
    <meta content="text/html, charset=utf-8" http-equiv="Content-Type"/>
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" name="viewport"/>
    <meta content="telephone=no" name="format-detection"/>
    <meta charset="utf-8"/>
    <meta content="view" name="decorator">
    <title>表单</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/frozenui.css"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/form-list.css"/>
</head>
<body>
<header class="header bg-col-1">
    <article>新建任务</article>
    <a href="index.html">
        <i class="iconright iconfont-p iconfont"></i>
    </a>
</header>
<div class="demo-block">
    <!-- 带标题文字消息 -->
    <div class="ui-dialog" id="dialog1">
        <div class="ui-dialog-cnt">
            <div class="ui-dialog-bd">
                <h3>拒绝参加会议</h3>
                <p></p>
                <p style="padding-top: 8px;">确认拒绝该会议吗？如确认请填写拒绝原因并提交</p>
                <p></p>
                <div class="ui-form-item ui-border-b">
                    <input type="text" id="reason" placeholder="请填写拒绝原因" style="padding-left: 68px;">
                </div>
            </div>
            <div class="ui-dialog-ft">
                <button type="button" data-role="button">退出</button>
                <button type="button" data-role="button" class="btn-recommand" id="refuse" onclick="refuse()">提交</button>
            </div>
        </div>
    </div>

</div>
<ul class="ui-list ui-list-nospace ui-border-tb">
    <h1 class="myfront title">${meeting.user.username}邀请你参加会议</h1>
    <li class="ui-border-t">
        <div class="ui-list-info">
            <h4 class="ui-nowrap">会议主题</h4>
            <div class="ui-h4-my ui-nowrap">${meeting.topic}</div>
        </div>
        <div class="ui-list-img-square">
            <span style="background-image:url(http://placeholder.qiniudn.com/188x188)"></span>
        </div>
    </li>
    <li class="ui-border-t">
        <div class="ui-list-info">
            <h4 class="ui-nowrap">会议地点</h4>
            <div class="ui-h4-my ui-nowrap">${meeting.meetingAddressEntity.addressEntity.addressname}</div>
        </div>
        <div class="ui-list-img-square">
            <span style="background-image:url(http://placeholder.qiniudn.com/188x188)"></span>
        </div>
    </li>
    <li class="ui-border-t">
        <div class="ui-list-info">
            <h4 class="ui-nowrap">会议时间</h4>
            <div class="ui-h4-my ui-nowrap">${meeting.meetingAddressEntity.meetingDate?string('yyyy-MM-dd')}
                从<font color=#ff8444>${meeting.meetingAddressEntity.beginTime?string('HH:mm')}</font>
                到<font color=#ff8444>${meeting.meetingAddressEntity.endTime?string('HH:mm')}</font>
            </div>
        </div>
        <div class="ui-list-img-square">
            <span style="background-image:url(http://placeholder.qiniudn.com/188x188)"></span>
        </div>
    </li>
    <li class="ui-border-t">
        <div class="ui-list-info">
            <h4 class="ui-nowrap">邀请人员</h4>
            <div class="ui-h4-my ui-nowrap">${userString}</div>
        </div>
        <div class="ui-list-img-square">
            <span style="background-image:url(http://placeholder.qiniudn.com/188x188)"></span>
        </div>
    </li>
    <li class="ui-border-t">
        <div class="ui-list-info">
            <h4 class="ui-nowrap">会议内容</h4>
            <div class="ui-h4-my ui-nowrap" onclick="showAll()" id="comment">${meeting.comment}</div>
        </div>
        <div class="ui-list-img-square">
            <span onclick=""></span>
        </div>
    </li>

    <li class="ui-border-t">
        <div class="ui-row-flex ui-whitespace">
            <div class="ui-col ui-col">
                <div class="ui-btn-wrap" id="acceptdiv">
                    <button class="ui-btn-lg ui-btn-primary-bottom" onclick="accept()" id="accept">
                        接受会议
                    </button>
                </div>
            </div>
            <div class="ui-col ui-col">
                <div class="ui-btn-wrap" id="refusediv">
                    <button class="ui-btn-lg" id="getRefuse">拒绝会议</button>
                </div>
            </div>
        </div>
        </div>
    </li>

    <div class="ui-footer ui-footer-stable">
        <p class="ui-txt-muted" style="text-align: center">以上为全部显示</p>
    </div>


</ul>


<script type="text/javascript">
    function showAll() {
        $('#comment').removeClass("ui-nowrap");
        $('#comment').addClass("ui-br");
    }


    (function () {
        var mta = document.createElement("script");
        mta.src = "http://pingjs.qq.com/h5/stats.js?v2.0.2";
        mta.setAttribute("name", "MTAH5");
        mta.setAttribute("sid", "500421336");
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(mta, s);
    })();
    $("#refusediv").click(function () {
        $("#dialog1").addClass("show");
    });

    function accept() {
        $.post("/accepting", {"accepting":"1","meeting.meetingid":${meeting.meetingid}},function (data) {
            window.location.href='success.ftl';
        });
    }
    function refuse() {
        $.post("/accepting", {"accepting":"2","meeting.meetingid":${meeting.meetingid},"reason":$('#reason').val()},function (data) {
            window.location.href='success.ftl';
        });
    }
</script>
<script src="http://open.mobile.qq.com/sdk/qqapi.js?_bid=152"></script>
<script src="js/zepto.min.js"></script>
<script src="js/index.js"></script>
</body>
</html>
