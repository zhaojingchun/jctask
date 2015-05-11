;(function($){
    var Carousel = function(poster){
        var self = this;
        //保存单个广告图片
        this.poster = poster;
        //alert($(this.poster).attr("date-setting"));
        this.setting ={
            "width":1000,
            "height":279,
            "posterWidth":460,
            "posterHeight":279,
            "scale":0.9,
            "speed":500,
            "verticalAlign":"middle",
            "autoPlay":true,
            "delay":500
        }
        //console.log(this.getSetting());
        $.extend(this.setting,this.getSetting());

        this.posterMain = this.poster.find("ul.poster_list");
        this.preBtn = this.poster.find("div.poster_pre_btn");
        this.nextBtn = this.poster.find("div.poster_next_btn");
        this.btnW = (this.setting.width-this.setting.posterWidth)/2;
        this.posterItems = this.poster.find("ul.poster_list li");
        this.posterItemFirst = this.posterItems.first();
        this.posterItemLast = this.posterItems.last();
        this.posterNo = this.posterItems.size();
        this.setSetingval();
        this.setPosterPos();
        this.rotateFlag =true;
        this.nextBtn.bind("click",function(){
            if(self.rotateFlag){
                self.rotateFlag=false;
                self.courseRotate("right");
            }

        })
        this.preBtn.bind("click",function(){
            if(self.rotateFlag){
                self.rotateFlag=false;
                self.courseRotate("left");

            }

        })
        if(this.setting.autoPlay){
            this.autoPlay();
        }
        this.posterMain.hover(function(){
            window.clearInterval(self.timer)
        },function(){
            self.autoPlay()
        })
    }

    Carousel.prototype ={
        autoPlay:function(){
            var self = this;
            this.timer = window.setInterval(function(){
                self.nextBtn.trigger("click")
            },this.setting.delay)
        },
        courseRotate:function(dir){
            var _this_ = this;
            if(dir=="left"){
                var zIndexArr = new Array();
                this.posterItems.each(function(){
                    var self = $(this);
                    var pre = self.prev().get(0)?self.prev():_this_.posterItemLast;
                    var width = pre.width();
                    var height = pre.height()
                    var zIndex = pre.css("zIndex");
                    var left = pre.css("left");
                    var opacity = pre.css("opacity");
                    var top = pre.css("top");
                    zIndexArr.push(zIndex)
                    self.animate({
                        width:width,
                        height:height,
                        zIndex:zIndex,
                        left:left,
                        //zIndex:zIndex,
                        opacity:opacity,
                        top:top
                    },_this_.speed,function(){
                        _this_.rotateFlag=true;
                    })
                })
                this.posterItems.each(function(i){
                    $(this).css({"zIndex":zIndexArr[i]})
                })
            }else{
                var zIndexArr = new Array();
                this.posterItems.each(function(){
                    var self = $(this);
                    var next = self.next().get(0)?self.next():_this_.posterItemFirst;
                    var width = next.width();
                    var height = next.height()
                    var zIndex = next.css("zIndex");
                    var left = next.css("left");
                    var opacity = next.css("opacity");
                    var top = next.css("top");
                    zIndexArr.push(zIndexArr)
                    self.animate({
                        width:width,
                        height:height,
                        zIndex:zIndex,
                        left:left,
                        //zIndex:zIndexArr,
                        opacity:opacity,
                        top:top
                    },_this_.speed,function(){
                        _this_.rotateFlag=true;
                    })
                })
                this.posterItems.each(function(i){
                    $(this).css({"zIndex":zIndexArr[i]})
                })
            }
        },
        setVerticalAlign:function(rh){
            var align = this.setting.verticalAlign;
            if(align==="middle"){
                return (this.setting.posterHeight-rh)/2
            }else if(align==="bottom"){
                return (this.setting.posterHeight-rh) ;
            }else{
                return 0;
            }
        },
        setPosterPos:function(){
            var _self = this;
            var sliceItems = this.posterItems.slice(1);
            var sliceSize = sliceItems.size()/2;
            var rightSlice = sliceItems.slice(0,sliceSize);
            var leftSlice = sliceItems.slice(sliceSize);
            var leftSliceSize = leftSlice.size();
            var gap = this.btnW/sliceSize;
            var rw = this.setting.posterWidth;
            var rh = this.setting.posterHeight;
            rightSlice.each(function(i,item){
                rw = rw*_self.setting.scale;
                rh = rh*_self.setting.scale;
                ++i;
                $(this).css({
                    position:"absolute",
                    zIndex:sliceSize--,
                    width:rw,
                    height:rh,
                    opacity:1/i,
                    left:(_self.btnW+_self.setting.posterWidth-rw+gap*i),
                    top:_self.setVerticalAlign(rh)
                });
            });
            leftSlice.each(function(i,item){
                $(this).css({
                    position:"absolute",
                    zIndex:i+1,
                    width:rw,
                    height:rh,
                    opacity:1/leftSliceSize,
                    left:(gap*i),
                    top:_self.setVerticalAlign(rh)
                });
                --leftSliceSize;
                rw = rw/_self.setting.scale;
                rh = rh/_self.setting.scale;
            });

        },
        setSetingval : function(){
            this.poster.css({
                width:this.setting.width,
                height:this.setting.height
            });
            this.posterMain.css({
                width:this.setting.width,
                height:this.setting.height
            });
            var firstIndex =  Math.ceil(this.posterItems.length/2);
            this.preBtn.css({
                width:this.btnW,
                height:this.setting.height,
                "z-index":firstIndex
            });

            this.nextBtn.css({
                width:this.btnW,
                height:this.setting.height,
                "z-index":firstIndex
            });
            this.posterItemFirst.css({
                left:this.btnW,
                height:this.setting.posterHeight,
                width:this.setting.posterWidth,
                position:"absolute",
                "z-index":firstIndex
            })

        },
        getSetting :function(){
            var settingStr =  this.poster.attr("date-setting");
            if(settingStr!=undefined&&settingStr!=""){
                return $.parseJSON(settingStr);
            }else{
                return {};
            }
        }
    }
    Carousel.init=function(posters){
        var _this_ = this;
        posters.each(function(){
            new _this_($(this));
        })

    }
    window["Carousel"] = Carousel;

})(jQuery);(function($){
    var Carousel = function(poster){
        var self = this;
        //保存单个广告图片
        this.poster = poster;
        //alert($(this.poster).attr("date-setting"));
        this.setting ={
            "width":1000,
            "height":279,
            "posterWidth":460,
            "posterHeight":279,
            "scale":0.9,
            "speed":500,
            "verticalAlign":"middle",
            "autoPlay":true,
            "delay":500
        }
        //console.log(this.getSetting());
        $.extend(this.setting,this.getSetting());

        this.posterMain = this.poster.find("ul.poster_list");
        this.preBtn = this.poster.find("div.poster_pre_btn");
        this.nextBtn = this.poster.find("div.poster_next_btn");
        this.btnW = (this.setting.width-this.setting.posterWidth)/2;
        this.posterItems = this.poster.find("ul.poster_list li");
        this.posterItemFirst = this.posterItems.first();
        this.posterItemLast = this.posterItems.last();
        this.posterNo = this.posterItems.size();
        this.setSetingval();
        this.setPosterPos();
        this.rotateFlag =true;
        this.nextBtn.bind("click",function(){
            if(self.rotateFlag){
                self.rotateFlag=false;
                self.courseRotate("right");
            }

        })
        this.preBtn.bind("click",function(){
            if(self.rotateFlag){
                self.rotateFlag=false;
                self.courseRotate("left");

            }

        })
        if(this.setting.autoPlay){
            this.autoPlay();
        }
        this.posterMain.hover(function(){
            window.clearInterval(self.timer)
        },function(){
            self.autoPlay()
        })
    }

    Carousel.prototype ={
        autoPlay:function(){
            var self = this;
            this.timer = window.setInterval(function(){
                self.nextBtn.trigger("click")
            },this.setting.delay)
        },
        courseRotate:function(dir){
            var _this_ = this;
            if(dir=="left"){
                var zIndexArr = new Array();
                this.posterItems.each(function(){
                    var self = $(this);
                    var pre = self.prev().get(0)?self.prev():_this_.posterItemLast;
                    var width = pre.width();
                    var height = pre.height()
                    var zIndex = pre.css("zIndex");
                    var left = pre.css("left");
                    var opacity = pre.css("opacity");
                    var top = pre.css("top");
                    zIndexArr.push(zIndex)
                    self.animate({
                        width:width,
                        height:height,
                        zIndex:zIndex,
                        left:left,
                        //zIndex:zIndex,
                        opacity:opacity,
                        top:top
                    },_this_.speed,function(){
                        _this_.rotateFlag=true;
                    })
                })
                this.posterItems.each(function(i){
                    $(this).css({"zIndex":zIndexArr[i]})
                })
            }else{
                var zIndexArr = new Array();
                this.posterItems.each(function(){
                    var self = $(this);
                    var next = self.next().get(0)?self.next():_this_.posterItemFirst;
                    var width = next.width();
                    var height = next.height()
                    var zIndex = next.css("zIndex");
                    var left = next.css("left");
                    var opacity = next.css("opacity");
                    var top = next.css("top");
                    zIndexArr.push(zIndexArr)
                    self.animate({
                        width:width,
                        height:height,
                        zIndex:zIndex,
                        left:left,
                        //zIndex:zIndexArr,
                        opacity:opacity,
                        top:top
                    },_this_.speed,function(){
                        _this_.rotateFlag=true;
                    })
                })
                this.posterItems.each(function(i){
                    $(this).css({"zIndex":zIndexArr[i]})
                })
            }
        },
        setVerticalAlign:function(rh){
            var align = this.setting.verticalAlign;
            if(align==="middle"){
                return (this.setting.posterHeight-rh)/2
            }else if(align==="bottom"){
                return (this.setting.posterHeight-rh) ;
            }else{
                return 0;
            }
        },
        setPosterPos:function(){
            var _self = this;
            var sliceItems = this.posterItems.slice(1);
            var sliceSize = sliceItems.size()/2;
            var rightSlice = sliceItems.slice(0,sliceSize);
            var leftSlice = sliceItems.slice(sliceSize);
            var leftSliceSize = leftSlice.size();
            var gap = this.btnW/sliceSize;
            var rw = this.setting.posterWidth;
            var rh = this.setting.posterHeight;
            rightSlice.each(function(i,item){
                rw = rw*_self.setting.scale;
                rh = rh*_self.setting.scale;
                ++i;
                $(this).css({
                    position:"absolute",
                    zIndex:sliceSize--,
                    width:rw,
                    height:rh,
                    opacity:1/i,
                    left:(_self.btnW+_self.setting.posterWidth-rw+gap*i),
                    top:_self.setVerticalAlign(rh)
                });
            });
            leftSlice.each(function(i,item){
                $(this).css({
                    position:"absolute",
                    zIndex:i+1,
                    width:rw,
                    height:rh,
                    opacity:1/leftSliceSize,
                    left:(gap*i),
                    top:_self.setVerticalAlign(rh)
                });
                --leftSliceSize;
                rw = rw/_self.setting.scale;
                rh = rh/_self.setting.scale;
            });

        },
        setSetingval : function(){
            this.poster.css({
                width:this.setting.width,
                height:this.setting.height
            });
            this.posterMain.css({
                width:this.setting.width,
                height:this.setting.height
            });
            var firstIndex =  Math.ceil(this.posterItems.length/2);
            this.preBtn.css({
                width:this.btnW,
                height:this.setting.height,
                "z-index":firstIndex
            });

            this.nextBtn.css({
                width:this.btnW,
                height:this.setting.height,
                "z-index":firstIndex
            });
            this.posterItemFirst.css({
                left:this.btnW,
                height:this.setting.posterHeight,
                width:this.setting.posterWidth,
                position:"absolute",
                "z-index":firstIndex
            })

        },
        getSetting :function(){
            var settingStr =  this.poster.attr("date-setting");
            if(settingStr!=undefined&&settingStr!=""){
                return $.parseJSON(settingStr);
            }else{
                return {};
            }
        }
    }
    Carousel.init=function(posters){
        var _this_ = this;
        posters.each(function(){
            new _this_($(this));
        })

    }
    window["Carousel"] = Carousel;

})(jQuery)