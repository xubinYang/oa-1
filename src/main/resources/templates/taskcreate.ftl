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
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/form-list.css" />
    <link rel="stylesheet" type="text/css" href="css/frozen.css">
    <style>
        .title { padding-left: 15px; line-height: 48px; font-size: 20px; color:
                #00A5E3; }
        body>a{display: none;}
    </style>
</head>

<body>
<h2 class="title ui-border-b"><a href="../static/index.html">Frozen UI</a> &gt; 表单项</h2>
<div class="ui-form">
    <form action="#">
        <div class="ui-form-item ui-border-b">
            <label for="#">
                任务名
            </label>
            <input type="text" placeholder="输入任务名" name="taskname" id="taskname"/>
            <a href="#" class="ui-icon-close">
            </a>
        </div>
        <div class="ui-form-item ui-form-item-textarea ui-border-b">
            <label for="#">
                任务描述
            </label>
            <textarea placeholder="街道等详细地址" name="taskDescribe" id="taskDescribe">
            </textarea>
        </div>
        <article class="art_wrap">
            <div class="art_title">截止时间：</div>
            <div class="art_con">
                <input type="datetime-local" id="deadline" name="deadline" class="input1 timeinput">
            </div>
        </article>
        <div class="ui-form-item ui-border-b">
            <label>任务类型</label>
            <div class="ui-select">
                <select name="type" id="type">
                    <option>2014</option>
                    <option selected="">2015</option>
                    <option>2016</option>
                </select>
            </div>
        </div>
        <div class="ui-form-item ui-border-b">
            <label>
                完成人员
            </label>
            <input type="text" placeholder="需要完成的人员" id="userInput" name="userInput"/>
            <a class="ui-btn ui-icon-close-my ui-btn-margin" type="button" id="userAdd"  >添加人员</a>
        </div>
        <input name="userString" id="userString" hidden>
        <div  id="parameterFather">

        </div>
        <div class="ui-btn-group ui-btn-group-bottom">
            <button type="button" onclick="saveTask()">
                保存
            </button>
            <button type="button" onclick="sub()">
                下一步
            </button>
        </div>

    </form>
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
            if(username === "${user.username}") {
                validatename = true;
            }
        </#list>

            if (validatename === true && clLength == 0) {
                //第一次添加 参数内容
                nowDom = '<div class="ui-row-flex ui-whitespace">\n' +
                        '       <div class="ui-col ui-col-2">\n'+
                        '           <ul class="ui-list ui-list-function ui-border-tb">\n'+
                        '               <li>'+
                        '                   <div class="ui-list-info ui-border-t">\n'+
                        '                       <h4 class="ui-nowrap">'+ username +'</h4>\n'+
                        '                       <p>userid</p>\n'+
                        '                   </div>\n'+
                        '                   <div class="ui-btn">PK</div>\n'+
                        '               </li>\n'+
                        '           <n></n></ul>\n'+
                        '       </div>\n'+
                        '</div>\n';
                parentDom.append(nowDom);
                $("#userString").val($("#userInput").val());
                $("#userInput").attr("value","");
            }
            else if (validatename === true && clLength != 0) {

                nowDom = '<div class="ui-row-flex ui-whitespace" >\n' +
                        '       <div class="ui-col ui-col-2">\n'+
                        '           <ul class="ui-list ui-list-function ui-border-tb">\n'+
                        '               <li>'+
                        '                   <div class="ui-list-info ui-border-t">\n'+
                        '                       <h4 class="ui-nowrap">\n'+ username +'</h4>\n'+
                        '                       <p>双行头像</p>\n'+
                        '                   </div>\n'+
                        '                   <div class="ui-btn">PK</div>\n'+
                        '               </li>\n'+
                        '           <n></n></ul>\n'+
                        '       </div>\n'+
                        '</div>\n';
                parentDom.append(nowDom);

                $("#userString").val($("#userString").val() + "," + $("#userInput").val());
                $("#userInput").attr("value","");
            }
            else
                alert("没有这个名字");
            return false;
        });
    });



    function saveTask() {
        var data = {
            taskname : $("#taskname").val(),
            taskDescribe : $("#taskDescribe").val(),
            deadlineString : $("#deadline").val(),
            type : $("#type").val(),
            userString : $("#userString").val(),
            saveOrSub : 0
        };
        $.post("/saveTask", data, function () {
            window.location.href='index.ftl';
        });
    }

    function sub() {
        var data = {
            taskname : $("#taskname").val(),
            taskDescribe : $("#taskDescribe").val(),
            deadlineString : $("#deadline").val(),
            type : $("#type").val(),
            userString : $("#userString").val(),
            saveOrSub : 1
        };
        $.post("/saveTask", data, function () {
            window.location.href='/taskform';
        });
    }


</script>
</body>

</html>