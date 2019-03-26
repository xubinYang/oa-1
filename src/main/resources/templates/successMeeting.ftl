
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
<ul class="ui-list ui-list-nospace ui-border-tb">
    <h1 class="myfront title">已创建会议</h1>
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

    <div class="ui-btn-wrap-my">
        <button class="ui-btn-lg ui-btn-primary-bottom" onclick="invitate()">
            继续邀请人员
        </button>
    </div>


</ul>
<footer class="ui-footer ui-footer-stable">
    <p class="ui-txt-muted" style="text-align: center">以上为全部显示</p>
</footer>
<script type="text/javascript">
    function showAll() {
        $('#comment').removeClass("ui-nowrap");
        $('#comment').addClass("ui-br");
    }
    function invitate() {
        $.post("/forward",{meetingid:${meeting.meetingid});
    }
</script>
</body>
</html>
