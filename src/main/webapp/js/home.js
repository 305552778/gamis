var ins_id=0;
$(function () {

    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        var index=$(this).index();
        $(".weui-tab__bd-item").eq(index).show().siblings().hide();
        if(index==0)
        {
            $(".weui-tab__bd-item").eq(index).children().attr("src","search.html");
        }
        if(index==1)
        {
            $(".weui-tab__bd-item").eq(index).children().attr("src","approve.html");
        }
    });

    $('#ok').on('click', function(){
        //var ins_id=$('input:radio[name="company"]:checked').val();
        if(ins_id!==0)
        {
            $.post("account/setins",
                {
                    "insID": ins_id
                },
                function(data){
                    console.log(data);
                    if(data.code==1)
                    {
                        $('#chooseCompany').fadeOut(200);
                        $('#companyItems').removeClass('weui-half-screen-dialog_show');
                    }
                    else
                    {
                        alert(data.msg);
                    }
                });
        }
        else
        {
            alert("请选择登陆单位");
        }
    });


});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ddcode="jfaskfnktrilsfee";
dd.ready(function () {
    dd.runtime.permission.requestAuthCode({
        corpId: "ding93a8f2c2230a245dee0f45d8e4f7c288", // 企业id
        onSuccess: function (info) {
            ddcode = info.code // 通过该免登授权码可以获取用户身份
            var x=GetQueryString("n");
            if(x.length>0)
            {
                var ins=JSON.parse(Decrypt2(x));
                console.log(ins);
                if(ins.length>0)
                {
                    $('#chooseCompany').fadeIn(200);
                    $('#companyItems').addClass('weui-half-screen-dialog_show');
                    $("#ins_bd").html( buildInsView(ins));
                }
                else
                {
                    window.location.href="auth.html";
                }
            }
            else
            {
                $.post("account/auth",
                    {
                        "ddcode":ddcode
                    },
                    function(data){
                        if(data.code===1)
                        {
                            var ins=data.data;
                            if(ins.length>0) {
                                $('#chooseCompany').fadeIn(200);
                                $('#companyItems').addClass('weui-half-screen-dialog_show');
                                $("#ins_bd").html( buildInsView(ins));
                            }
                        }
                        else if(data.code===0)
                        {
                            window.location.href="auth.html";
                        }
                        else
                        {
                            //alert(data.Msg);
                            $("#ins_bd").html("<h3>请联系管理员开通权限</h3>");
                        }
                    });
            }
        },
        onFail: function (err) {
            alert(err);
        }
    });
});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function setIns() {
    var radios = document.getElementsByName("company");
    for(var i=0;i<radios.length;i++){
        if(radios[i].checked == true){
            ins_id = radios[i].value;
        }
    }
}
function buildInsView(ins) {
    var ins_html="";
    for (var i = 0; i < ins.length; i++) {
        var item = ins[i];
        ins_html += "<label class='weui-cell weui-cell_active weui-check__label' for='x"+item.instituteID+"'><div class='weui-cell__bd'>";
        ins_html += "<p id='t"+item.instituteID+"'>" + item.instituteName + "</p>";
        ins_html += "</div>";
        ins_html += "<div class='weui-cell__ft'>";
        ins_html += " <input type='radio' class='weui-check' name='company' value='" + item.instituteID + "' id='x"+item.instituteID+"' onclick='setIns()'/>";
        ins_html += "<span class='weui-icon-checked'></span>";
        ins_html += "</div>";
        ins_html += "</label>";
    }
    return ins_html;
}