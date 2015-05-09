<html>
<head>
    <style type="text/css">
        body, ul, li, h1, h2, h3, h4, h5, h6, p, form, dl, dt, dd { margin: 0px; padding: 0px;
            font-size: 12px; font-weight: normal; }
        ul { list-style: none; }
        img { border-style: none; }
        .main {
            position:relative ;
            width:1000px ;
            height:279px ;
            overflow:hidden ;
        }
        .main .pre_btn{
            position:absolute;
            left: 0;
            height: 100%;
            width:200px;
            background-color: red;
        }
        .main .nex_btn{
            position:absolute;
            right: 0;
            height: 100%;
            width:200px;
            background-color: red;
        }

        ul li{
            position:absolute;
            list-style:none;
        }
    </style>
</head>
<body>
<div class="main" date-setting='{   "width":1000,
                                    "height":279,
                                    "liItemWidth":460,
                                    "liItemHeight":279,
                                    "scate":0.9
                                    }'>
    <div class="pre_btn">></div>
    <div>
        <ul>
            <li><img src="./images/11.jpg"></li>
            <li><img src="./images/11.jpg"></li>
            <li><img src="./images/11.jpg"></li>
            <li><img src="./images/11.jpg"></li>
            <li><img src="./images/11.jpg"></li>
        </ul>
    </div>
    <div class="nex_btn"><</div>

</div>
</body>
<script type="text/javascript" src="./common/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
    (function($){
        this.setting ={
            "width":1000,
            "height":279,
            "liItemWidth":460,
            "liItemHeight":279,
            "scate":0.9
        }
        var aa = $.extend({},this.setting)
        console.log(aa)
    })(jQuery)
</script>
</html>
