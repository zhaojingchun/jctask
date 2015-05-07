<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
    <head>
    </head>
<body>
<a href="/task/task.action" >task</a>
<input type="text" value="请输入需要抓取的url" id="catchUrl" /><input type="button" id="cmtButton" value="抓取"/>
<br><hr>
<input type="text" value="请输入需要抓取文件url" id="catchFileUrl" /><input type="button" id="cmtFileButton" value="抓取"/>
<iframe id="iframe" style="width:100%;height:600px;" >
</iframe>
<div id = "catchPage">

</div>
<form method="post" action="/upload/uploadImage.action" enctype="multipart/form-data">
    <%--上传文件1<input type="file" name="upload" id="upload"/><input type="button" value="上传" onclick="uploadImage();"/>--%>

上传文件1<input type="file" name="upload" id="upload"/><input type="button" value="上传" onclick="uploadZipImage();"/>
    <%--<input type="submit" value="提交"/>--%>


</form>
<script type="text/javascript" src="/common/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    function uploadNormalImage(){
        uploadImage('/upload/uploadImage.action');
    }
    function uploadZipImage(){
        uploadImage('/upload/uploadZipImage.action');
    }
    function  uploadImage(url){
        jQuery.ajaxFileUpload(
                {
                    url:url , // 需要链接到服务器地址
                    secureuri: false,
                    fileElementId: 'upload', // 文件选择框的id属性
                    dataType: 'json', // 服务器返回的格式，可以是json
                    success: function (data, status)
                    {
                        alert(data.status)
                    },
                    error: function (data, status, e)
                    {
                       alert(data.status)
                    }
                });
    }
    $(function(){
        $("#cmtButton").bind("click",function(){
            var data = {"catchUrl":$("#catchUrl").val()} ;
            alert(data.catchUrl);
            $.post("/catch/catchPage.action",
                    data,
                    function(result){
                        $("#iframe").attr("src","/html/"+result);
                    });
        });
        $("#cmtFileButton").bind("click",function(){
            var data = {"catchUrl":$("#catchFileUrl").val()} ;
            alert(data.catchUrl);
            $.post("/catch/catchFile.action",
                    data,
                    function(result){
                        $("#iframe").attr("src","/html/"+result);
                    });
        });
    });
</script>

</body>
</html>
