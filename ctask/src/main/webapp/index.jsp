<html>
<head>
    <style type="text/css">
        body, ul, li, h1, h2, h3, h4, h5, h6, p, form, dl, dt, dd { margin: 0px; padding: 0px;
            font-size: 12px; font-weight: normal; }
        ul { list-style: none; }
        img { border-style: none; }
        .main {
            margin-left: auto;
            margin-right: auto;
            position:relative ;
            width:1000px ;
            top:160px;
            height:279px ;
            overflow:hidden ;
        }
        .main .poster_pre_btn{
            position:absolute;
            top:0px;
            left: 0;
            height: 100%;
            width:200px;
            background:url(/common/images/left.png) no-repeat  center center;
            cursor:pointer;
        }
        .main .poster_next_btn{
            position:absolute;
            top:0px;
            right: 0px;
            height: 100%;
            width:200px;
            background:url(/common/images/right.png) no-repeat  center center;
            cursor:pointer;
        }

        ul li{
            position:absolute;
            list-style:none;
        }
        img {
            width:100%;
            height:100% ;
        }
        .btn{
            font-size: 20px;
            font-color:black;
            line-height: 279px;
            align-content: center;
        }
    </style>
</head>
<body>
<div class="jPoster main" date-setting='{   "width":1000,
                                    "height":279,
                                    "posterWidth":460,
                                    "posterHeight":279,
                                    "scale":0.8,
                                    "speed":500,
                                    "verticalAlign":"middle",
                                    "autoPlay":true,
                                    "delay":1500
                                    }'>
    <div class="poster_pre_btn btn"></div>
    <ul class="poster_list">
        <li class="poster_item"><a href="#"><img src="/common/images/1.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/2.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/3.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/4.jpeg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/5.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/6.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/7.jpeg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/8.jpg"></a></li>
        <li class="poster_item"><a href="#"><img src="/common/images/9.jpg"></a></li>
    </ul>
    <div class="poster_next_btn btn"></div>
</div>


</body>
<script type="text/javascript" src="./common/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="./common/js/carousel.js?1=4"></script>
<script type="text/javascript">
    $(function(){
        var carousel = Carousel.init($(".jPoster"));
    })

</script>
</html>
