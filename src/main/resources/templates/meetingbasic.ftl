
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
    <form action="/chooseTime">
        <div class="ui-form-item ui-border-b">
            <label>
                会议主题
            </label>
            <input type="text" placeholder="主题" id="topic" name="topic" />
            </a>
        </div>


        <div class="ui-form-item ui-border-b">
            <label>
                参会人员
            </label>
            <input type="text" placeholder="邀请人姓名" id="userInput" />
            <a class="ui-btn ui-icon-close-my" type="button" id="userAdd"  >添加人员</a>
        </div>

        <input type="text" id="users" value="" name="users" hidden>
        <div  id="parameterFather">

        </div>

        <div class="ui-form-item ui-form-item-textarea ui-border-b">
            <label for="#">
                会议内容
            </label>
            <textarea placeholder="" rows="5" cols="35" id="textarea" id="comment" name="comment">

            </textarea>
        </div>

        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary">
                创建会议
            </button>
        </div>
    </form>
</section>
<footer class="ui-footer ui-footer-stable">
    <p class="ui-txt-muted" style="text-align: center">以上为全部显示</p>
</footer>

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
