<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="author" content="ISUX" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <title>FrozenUI组件库</title>
    <!--统计代码 -->

    <!--<link rel="stylesheet" href="css/frozenui.css"/>-->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/form-list.css" />
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=37342703" charset="UTF-8"></script>
    <script type="text/javascript">
        var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F3c5e509616a8353ee908823c8b29510c' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <!-- <link rel="stylesheet" type="text/css" href="http://i.gtimg.cn/vipstyle/frozenui/1.2.0/css/frozen.css?_bid=306"/> -->
    <link rel="stylesheet" type="text/css" href="css/frozen.css">
    <style>
        .title { padding-left: 15px; line-height: 48px; font-size: 20px; color:
                #00A5E3; }
        body>a{display: none;}
    </style>
</head>

<body ontouchstart="">
<h2 class="title ui-border-b"><a href="../static/index.html">Frozen UI</a> &gt; 表单项</h2>
<ul class="ui-list ui-border-tb">
    <li>
        <div class="ui-list-info-my ui-border-t">
            <h4>100*100头像</h4>
            <p>内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
        </div>
    </li>
    <li>
        <div class="ui-list-info-my ui-border-t">
            <h4>100*100头像</h4>
            <p>内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
        </div>
    </li>
    <li>
        <div class="ui-list-info-my ui-border-t">
            <h4>100*100头像</h4>
            <p>内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
        </div>
    </li>
</ul>

<div class="ui-btn-group ui-btn-group-bottom">
    <button type="button">
        回到主页
    </button>

</div>
<script type="text/javascript">
    var blockNum = 10;//参数内容最多添加10个

    $(function(){
        //实现动态添加参数div
        $('#userAdd').click(function () {
            var username = $("#userInput").val();
            var userid = null;
            var parentDom = $("#parameterFather"), oriDom = parentDom.children(":first");
            // Number count = 0;
            var clLength = parentDom.children().length;
            var nowDom;
            var validatename = false;
        <#list users as user>
            if(username === "${user.username}"){
                validatename = true;

            <#--userid = "${user.userid}";-->
            }

        </#list>


            // if($("#userInput").val().indexOf('h') > 0) {
            //     alert("有 'h'");
            // }else {
            //     alert("没有 'h'");
            // }
            if (validatename === true && clLength == 0) {
                //第一次添加 参数内容
                nowDom = '<div class="ui-row-flex ui-whitespace">\n' +
                    '       <div class="ui-col ui-col-2">\n'+
                    '           <ul class="ui-list ui-list-function ui-border-tb">\n'+
                    '               <li>'+
                    '                   <div class="ui-list-info ui-border-t" id=">\n'+username+'"'+
                    '                       <h4 class="ui-nowrap">'+ username +'</h4>\n'+
                    '                       <p>userid</p>\n'+
                    '                   </div>\n'+
                    '                   <div class="ui-btn">PK</div>\n'+
                    '               </li>\n'+
                    '           <n></n></ul>\n'+
                    '       </div>\n'+
                    '</div>\n';
                parentDom.append(nowDom);
                $("#users").val($("#userInput").val());
                $("#userInput").attr("value","");
            }
            else if (validatename === true && clLength != 0) {

                nowDom = '<div class="ui-row-flex ui-whitespace" >\n' +
                    '       <div class="ui-col ui-col-2">\n'+
                    '           <ul class="ui-list ui-list-function ui-border-tb">\n'+
                    '               <li>'+
                    '                   <div class="ui-list-info ui-border-t" id=">\n'+username+'"'+
                    '                       <h4 class="ui-nowrap">\n'+ username +'</h4>\n'+
                    '                       <p>双行头像</p>\n'+
                    '                   </div>\n'+
                    '                   <div class="ui-btn">PK</div>\n'+
                    '               </li>\n'+
                    '           <n></n></ul>\n'+
                    '       </div>\n'+
                    '</div>\n';
                parentDom.append(nowDom);

                $("#users").val($("#users").val() + "," + $("#userInput").val());
                $("#userInput").attr("value","");
            }
            else
                alert("没有这个名字");
            return false;
        });



    });

</script>
</body>

</html>