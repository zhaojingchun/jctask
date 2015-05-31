
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<>
    <title></title>
    <script type="text/css">
        .txtarea {
            border: 1px solid #C5E2F2;
            width: 300px;
            height: 145px;
            padding: 5px;
            font-size: 12px;
        }
    </script>


</head>
<body>
     生成二维码
    <textarea name="textarea" id="textarea" class="txtarea" cols="20" rows="10"></textarea>
    <button id="createQC" > 生成二维码</button>
    <img src="" id="qcImg"/>
     <input type="file" name="upload" id="upload"/>
     <input type="button" value="上传" onclick="uploadNormalImage();"/>
<div id="decodeStr"></div>
</body>
<script type="text/javascript" src="/common/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/common/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    function uploadNormalImage(){
        uploadImage('/qc/decodeQC.action');
    }
    function  uploadImage(url){
        jQuery.ajaxFileUpload(
                {
                    url:url , // 需要链接到服务器地址
                    secureuri: false,
                    fileElementId: 'upload', // 文件选择框的id属性
                    dataType: 'json', // 服务器返回的格式，可以是json
                    success: function (data)
                    {
                        $("#decodeStr").text(data);
                    },
                    error: function (data)
                    {
                        alert(data)
                    }
                });
    }
    $(function(){
        $("#createQC").bind("click",function(){
            var data = {"content":$("#textarea").val()}
            $.post("/qc/createQC.action",data,
                    function(result){
                        $("#qcImg").attr("src","/html/"+result);
                    });
        });
    });
</script>
</html>
