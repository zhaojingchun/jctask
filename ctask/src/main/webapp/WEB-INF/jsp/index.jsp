<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
    <head>
    </head>
<body>
<input type="text" value="请输入需要抓取的url" id="catchUrl" /><input type="button" id="cmtButton" value="抓取"/>
<br><hr>
<input type="text" value="请输入需要抓取文件url" id="catchFileUrl" /><input type="button" id="cmtFileButton" value="抓取"/>
<iframe id="iframe" style="width:100%;height:600px;" >
</iframe>
<div id = "catchPage">

</div>
<form method="post" action="/upload/uploadOneFile.action" enctype="multipart/form-data">
    上传文件1<input type="file" name="upload"/><br>
    <input type="submit" value="提交"/>


</form>
<script type="text/javascript" src="/common/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
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
