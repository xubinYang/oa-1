<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <title>FrozenUI Demo</title>
    <#--<!-- 引入 FrozenUI &ndash;&gt;-->
    <!--<link rel="stylesheet" href="../static/css/oa.css"/>-->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <#--<script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>-->
    <link rel="stylesheet" href="css/frozenui.css"/>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>



    <div class="ui-col ui-col-4" >
        <h1 class="title">创建会议</h1>
        <hr>
        <form action="/chooseTime">
            <div class="ui-form-item ui-border-b">
                <label>
                    列表标题
                </label>
                <input type="text" id="exampleFirstName" name="topic"
                       placeholder="First Name">
                <a href="#" class="ui-icon-close">
                </a>
            </div>


            <div class="ui-form-item ui-border-b">
                <label>
                    列表标题
                </label>
                <input type="text" placeholder="18位身份证号码" id="userInput" />
                <a class="ui-btn ui-icon-close-my ui-btn-margin" type="button" id="userAdd"  >添加人员</a>
            </div>
            <input type="text" id="users" value="" name="users" >
            <div  id="parameterFather">

            </div>

            <div class="ui-form-item ui-border-b ui-form-item-textarea-comment">
                <label>
                    列表标题
                </label>
                <textarea rows="5" cols="35" id="textarea" id="comment" name="comment"></textarea>

            </div>

            <div class="ui-btn-wrap">
                <button class="ui-btn-lg ui-btn-primary">
                    创建会议
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
