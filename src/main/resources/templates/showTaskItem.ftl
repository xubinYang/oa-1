<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="author" content="ISUX"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="viewport" content="width=device-width, user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <title>FrozenUI组件库</title>
    <!--统计代码 -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=37342703" charset="UTF-8"></script>


    <!-- <link rel="stylesheet" type="text/css" href="http://i.gtimg.cn/vipstyle/frozenui/1.2.0/css/frozen.css?_bid=306"/> -->
    <link rel="stylesheet" type="text/css" href="css/frozen.css">
    <style>
        .title { padding-left: 15px; line-height: 48px; font-size: 20px; color:
                #00A5E3; }
        .title2{
            padding: 0 15px;
            line-height: 66px;
            font-size: 23px;
        }
        body>a{display: none;}
        .ui-list{margin-bottom: 20px;}
        .border-list{
            background-color: #fff;
        }
        .border-list li{width: 100px;margin: 10px auto;-webkit-box-pack: center;text-align: center;}


        body > a {
            display: none;
        }
    </style>
</head>

<body ontouchstart="">
<h2 class="title ui-border-b"><a href="index.html">Frozen UI</a> &gt; 表单项</h2>
<div class="ui-arrowlink title2">填写以下表格</div>
<div class="ui-form">
    <form action="#">
        <ul class="ui-list ui-border-tb" >
<div id="parameterFather"></div>
        </ul>

        <div class="ui-btn-group ui-btn-group-bottom">
            <button type="button">
                上一步
            </button>
            <button type="button">
                预览
            </button>
            <button type="button" onclick="sub()">
                提交
            </button>
        </div>
    </form>
</div>

<script type="text/javascript">
    var blockNum = 10;//参数内容最多添加10个
    var count =0;
    $(document).ready(function () {

        var parentDom = $("#parameterFather"), oriDom = parentDom.children(":first");
        // Number count = 0;
        var clLength = parentDom.children().length;

        var nowDom;
        var questionName = null;
        var questionDescribe = null;

            <#list questions as question>
                if(count == 0){
                    count++;
                    nowDom = '<li>\n' +
                            '    <div class="ui-list-info ui-border-t">\n' +
                            '        <h4>'+ '${question.questionName}' +' </h4>\n' +
                            '        <div class="ui-form-item-taskitem ui-border-b">\n' +
                            '            <input type="text" placeholder="'+ '${question.questionName}' +'" id="value'+ count +'"/>\n' +
                            '            <a href="#" class="ui-icon-close">\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </div>\n' +
                            '</li>';
                }
                else {
                    count++;
                    nowDom += '<li>\n' +
                            '    <div class="ui-list-info ui-border-t">\n' +
                            '        <h4>'+ "${question.questionName}" +' </h4>\n' +
                            '        <div class="ui-form-item-taskitem ui-border-b">\n' +
                            '            <input type="text" placeholder="'+ '${question.questionName}' +'" id="value'+ count +'"/>\n' +
                            '            <a href="#" class="ui-icon-close">\n' +
                            '            </a>\n' +
                            '        </div>\n' +
                            '    </div>\n' +
                            '</li>';
                }

            </#list>
        parentDom.append(nowDom);
        alert("ok");
    });


    var value;
    function sub() {
        for (var i=1; i<=count; i++){
            if (i===1){
                value = $("#value"+i).val();
            }
            else {
                value += "&"+$("#value"+i).val();
            }
        }
        $.post("/addTaskItem",{"taskEntity.taskid":${task.taskid},"value":value},function () {
            window.location.href='success.ftl';
        });
    }

</script>
</body>
</html>