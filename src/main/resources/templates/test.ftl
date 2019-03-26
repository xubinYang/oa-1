<HTML>
<HEAD>
    <TITLE>测试页面---HelloWorld!</TITLE>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <!-- 企业微信的JS-SDK -->
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>

</HEAD>
<BODY>
    <h1>登录</h1>



    <button id="a1">aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</button>

    <script type="text/javascript">

        //初始化方法
        function init() {
            getToken();
            bindClick();
        }

        //从后台获取wx.config中所需要的参数
        function getToken() {
            // $.ajax({
            //     url : "/name",
            //     type : "get",
            //     dataType : "json",
            //     success : function(data) {
            //         console.log(data);
            //         var json = data;
            //         wx.config({
            //             beta : true,// 必须这么写，否则wx.invoke调用形式的jsapi会有问题
            //             debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            //             appId : 'ww8c2f2da594c7306c', // 必填，企业微信的corpID
            //             timestamp : json.timestamp, // 必填，生成签名的时间戳
            //             nonceStr : json.noncestr, // 必填，生成签名的随机串
            //             signature : json.signature,// 必填，签名，见附录1
            //             jsApiList : [ 'checkJsApi', 'selectEnterpriseContact' ]
            //             // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            //         });
            //         wx.ready(function() {
            //             wx.checkJsApi({
            //                 jsApiList : [ 'selectEnterpriseContact' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            //                 success : function(ress) {
            //                     alert(2)
            //                     // 以键值对的形式返回，可用的api值true，不可用为false
            //                     // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            //                 }
            //             });
            //         });
            //     }
            // });

            $.get("/name",function(data) {
                console.log(data);
                var json = data;
                wx.config({
                    beta : true,// 必须这么写，否则wx.invoke调用形式的jsapi会有问题
                    debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId : 'ww8c2f2da594c7306c', // 必填，企业微信的corpID
                    timestamp : json.timestamp, // 必填，生成签名的时间戳
                    nonceStr : json.noncestr, // 必填，生成签名的随机串
                    signature : json.signature,// 必填，签名，见附录1
                    jsApiList : [ 'checkJsApi', 'selectEnterpriseContact' ]
                    // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });
                wx.ready(function() {
                    wx.checkJsApi({
                        jsApiList : [ 'selectEnterpriseContact' ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                        success : function(ress) {
                            alert(2)
                            // 以键值对的形式返回，可用的api值true，不可用为false
                            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                        }
                    });
                });
            },"json");
        }

        //绑定两个按钮的点击事件
        function bindClick() {
            //这个接口是打开userIds对话框，运行则微信端直接跳到聊天框
            $("#a1").click(function() {
                wx.invoke("selectEnterpriseContact", {
                            "fromDepartmentId": -1,// 必填，表示打开的通讯录从指定的部门开始展示，-1表示自己所在部门开始, 0表示从最上层开始
                            "mode": "multi",// 必填，选择模式，single表示单选，multi表示多选
                            "type": ["department", "user"]// 必填，选择限制类型，指定department、user中的一个或者多个
                        },function(res){
                            if (res.err_msg == "selectEnterpriseContact:ok")
                            {
                                if(typeof res.result == 'string')
                                {
                                    res.result = JSON.parse(res.result) //由于目前各个终端尚未完全兼容，需要开发者额外判断result类型以保证在各个终端的兼容性
                                }
                                var selectedDepartmentList = res.result.departmentList;// 已选的部门列表
                                for (var i = 0; i < selectedDepartmentList.length; i++)
                                {
                                    var department = selectedDepartmentList[i];
                                    var departmentId = department.id;// 已选的单个部门ID
                                    var departemntName = department.name;// 已选的单个部门名称
                                }
                                var selectedUserList = res.result.userList; // 已选的成员列表
                                for (var i = 0; i < selectedUserList.length; i++)
                                {
                                    var user = selectedUserList[i];
                                    var userId = user.id; // 已选的单个成员ID
                                    var userName = user.name;// 已选的单个成员名称
                                    var userAvatar= user.avatar;// 已选的单个成员头像
                                }
                            }
                        }
                );
            });
        }
        init();
    </script>

</BODY>
</HTML>