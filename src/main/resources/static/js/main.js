(function(jQuery) {
    var $ = jQuery;
    if(cache != null){
    var cache = window.localStorage.getItem("bts");
        var arr = cache.split(",");
        for(var i in arr) {
            if(arr[i] != "" && arr[i] != "null" && arr[i] != null)
                $(".addFood").append("<button class='button button5 button7' id='button7'>" + arr[i] + "</button>")
        }
    }
    var textArr =new Array();
    /////////////////
    //ajax 需要添加新的方法
    function caipin(){
        $(".button4").on("click",function() {
            if($(this).is(".button6")) {
                $(this).removeClass("button6");
                var foodnumber=textArr.indexOf($(this).text());
                if(foodnumber!=-1){
                    textArr.splice(foodnumber,1);
                }
            } else {
                $(this).addClass("button6");
               var foodnum= textArr.indexOf($(this).text());
               if(foodnum==-1)
                    textArr.push($(this).text());
            }
        });
    }
    //获取模态框数据
    $(document).on("click",".nextli a",function () {
        var name = $(this).text();
        // alert(name);
        $.ajax({
            type: "get",
            url: "/food/list?type="+name,
            dataType: 'html',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                $("#bts").empty();
                result=JSON.parse(result);
                // alert(result.data[0].name);
                for (var i in result.data){
                    $("#bts").append("<span><button type='button' class='button button4' id='button4' value='"+result.data[i].name+"'>"+result.data[i].name+"</button></span>");
                }
                caipin();
            }
        });
    });

    //搜索查询的餐品
    $("#inputButton").on("click",function(){
        //缓存的添加
        //    获取输入框中的菜品名称
        var foodname= $("#input_search").val();
        if(foodname != null){
            $.ajax({
                type: "get",
                url:  "/food/query.json?foodName="+foodname,
                dataType: 'html',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {
                    //清空原本的菜品信息
                    $("#bts").empty();
                    result=JSON.parse(result);
                    for (var i in result.data){
                        $("#bts").append("<span><button type='button' class='button button4' id='button4' value='"+result.data[i].name+"'>"+result.data[i].name+"</button></span>");
                    }
                    caipin();
                }
            });
        }
    });
    //重置按钮的设置
    $("#resertButton").on("click",function () {
        // var name=$(".nextlili").find("#nexthref").text();
        // alert(name);
        var flag = false;
        $.ajax({
            type: "get",
            url: "/food/list",
            dataType: 'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                $("#bts").empty();
                result=JSON.parse(result);
                // alert(result.data[0].name);
                for (var i in result.data){
                    $("#bts").append("<span><button type='button' class='button button4' id='button4' value='"+result.data[i].name+"'>"+result.data[i].name+"</button></span>");
                }
                caipin();
            }
        });
    });
    //////////////////

    $.fn.ripple = function() {
        $(this).click(function(e) {
            var rippler = $(this),
                ink = rippler.find(".ink");
            if(rippler.find(".ink").length === 0) {
                rippler.append("<span class='ink'></span>");
            }
            ink.removeClass("animate");
            if(!ink.height() && !ink.width()) {
                var d = Math.max(rippler.outerWidth(), rippler.outerHeight());
                ink.css({
                    height: d,
                    width: d
                });
            }
            var x = e.pageX - rippler.offset().left - ink.width() / 2;
            var y = e.pageY - rippler.offset().top - ink.height() / 2;
            ink.css({
                top: y + 'px',
                left: x + 'px'
            }).addClass("animate");
        });
    };


    //菜品自动添加的js
    $("#ripper_active").on("click",function() {
        $(".tree").empty();

        $.ajax({
            type :"GET",
            url : "/foodType/list",
            dataType: 'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success : function (result) {
                result=JSON.parse(result);
                for (var i in result.data){
                    $(".tree").append(" <li class='nextli'>" +
                        " <a href='javascript:void(0);'id='nexthref' >"+result.data[i].name+"</a>" +
                        " </li>")
                }
            }
        });
    });
    /*sadasda*/
    $(".ripple").on("click",function() {
        var index = $(this).index();
        $(this).addClass("active").siblings().removeClass("active");
    });
    $(document).on("click",".nextli",function() {
        $(this).addClass("nextlili").siblings().removeClass("nextlili");
    });



    /*这是button按钮点击事件*/
    $(".button4").on("click",function() {

        if($(this).is(".button6")) {
            $(this).removeClass("button6");
            var foodnumber=textArr.indexOf($(this).text());
            if(foodnumber!=-1){
                textArr.splice(foodnumber,1);
            }

        } else {
            $(this).addClass("button6");
            var foodnum= textArr.indexOf($(this).text());
            if(foodnum==-1)
                textArr.push($(this).text());
        }
    });
    /*这是提交订单事件*/
    $(".busubmit").on("click",function() {
        var arr = new Array();
        var bts = window.localStorage.getItem("bts");
        var res = "";

        //通过获取点击添加的css查找选中的按钮
        $("button.button6").each(function () {
            var a = $(this).text();
            if (bts.indexOf(a) != -1) {
                if (res == "")
                    res = a;
                else
                    res += "，" + a;
            }
            $(this).removeClass("button6");
            arr.push(a);
        });
        if (bts != null && bts != "null") {
            // console.log(window.localStorage);
            var bts_array = bts.split(',');
            if (bts_array != null && bts_array.length > 0) {
                //循环数组，发现不一样的餐品添加进入，找到一样的不添加。
                for (var i in textArr) {
                    // var flag = 0;
                    if(bts_array.indexOf(textArr[i])==-1)
                    {
                        bts += "," + textArr[i];
                        $(".addFood").append("<button class='button button5 button7' id='button7'>" + textArr[i] + "</button>")
                    }
                }
                window.localStorage.setItem("bts",bts);
            }
        } else {
            for (var i in textArr) {
                bts += ","+textArr[i];
                $(".addFood").append("<button class='button button5 button7' id='button7'>" + textArr[i] + "</button>")
            }
            window.localStorage.setItem("bts", bts);
        }
    });

    //删除菜品,先添加属性再删除

    $(".button7").on("click","#button7",function() {

        if($(this).is(".buttonDel")) {
            $(this).removeClass("buttonDel");
        } else {
            $(this).addClass("buttonDel");
        }
    });
    //这是新添加的菜品的为删除菜单增加的jquery
    $(".addFood").on("click","#button7",function() {

        if($(this).is(".buttonDel")) {
            $(this).removeClass("buttonDel");
        } else {
            $(this).addClass("buttonDel");
        }

    });

    //没有菜品的时候提示没有删除
    $(".buttonDe").on("click",function () {
        var index=0;
        $("button.button7").each(function () {
            if($(this).is(".buttonDel")){
                index=1;
                var btName = $(this).text();
                console.log("要删除的菜谱：" + btName);
                var bts = window.localStorage.getItem("bts");
                window.localStorage.setItem("bts", bts.replace(btName,""));
            }
            if(index==1){
                $(".buttonDel").remove();
            }
        });
    });


    $.fn.carouselAnimate = function() {
        function doAnimations(elems) {
            var animEndEv = 'webkitAnimationEnd animationend';

            elems.each(function() {
                var $this = $(this),
                    $animationType = $this.data('animation');
                $this.addClass($animationType).one(animEndEv, function() {
                    $this.removeClass($animationType);
                });
            });
        }

        var $myCarousel = this;
        var $firstAnimatingElems = $myCarousel.find('.item:first')
            .find('[data-animation ^= "animated"]');

        doAnimations($firstAnimatingElems);
        $myCarousel.carousel('pause');
        $myCarousel.on('slide.bs.carousel', function(e) {
            var $animatingElems = $(e.relatedTarget)
                .find("[data-animation ^= 'animated']");
            doAnimations($animatingElems);
        });
    };

    this.hide = function() {
        $(".tree").hide();
        $(".sub-tree").hide();
    };

    this.treeMenu = function() {

        $('.tree-toggle').click(function(e) {
            e.preventDefault();
            var $this = $(this).parent().children('ul.tree');
            $(".tree").not($this).slideUp(1200);
            $this.toggle(1300);

            $(".tree").not($this).parent("li").find(".tree-toggle .right-arrow").removeClass("fa-angle-down").addClass("fa-angle-right");
            $this.parent("li").find(".tree-toggle .right-arrow").toggleClass("fa-angle-right fa-angle-down");
        });

        $('.sub-tree-toggle').click(function(e) {
            var $this = $(this).parent().children('ul.sub-tree');
            $(".sub-tree").not($this).slideUp(1200);
            $this.toggle(1300);

            $(".sub-tree").not($this).parent("li").find(".sub-tree-toggle .right-arrow").removeClass("fa-angle-down").addClass("fa-angle-right");
            $this.parent("li").find(".sub-tree-toggle .right-arrow").toggleClass("fa-angle-right fa-angle-down");
        });
    };

    this.leftMenu = function() {

        $('.opener-left-menu').on('click', function() {
            $(".line-chart").width("100%");
            $(".mejs-video").height("auto").width("100%");
            if($('#right-menu').is(":visible")) {
                $('#right-menu').animate({
                    'width': '0px'
                }, 'slow', function() {
                    $('#right-menu').hide();
                });
            }
            if($('#left-menu .sub-left-menu').is(':visible')) {
                $('#content').animate({
                    'padding-left': '0px'
                }, 'slow');
                $('#left-menu .sub-left-menu').animate({
                    'width': '0px'
                }, 'slow', function() {
                    $('.overlay').show();
                    $('.opener-left-menu').removeClass('is-open');
                    $('.opener-left-menu').addClass('is-closed');
                    $('#left-menu .sub-left-menu').hide();
                });
            } else {
                $('#left-menu .sub-left-menu').show();
                $('#left-menu .sub-left-menu').animate({
                    'width': '230px'
                }, 'slow');
                $('#content').animate({
                    'padding-left': '230px',
                    'padding-right': '0px'
                }, 'slow');
                $('.overlay').hide();
                $('.opener-left-menu').removeClass('is-closed');
                $('.opener-left-menu').addClass('is-open');
            }
        });
    };

    this.userList = function() {

        $(".user-list ul").niceScroll({
            touchbehavior: true,
            cursorcolor: "#FF00FF",
            cursoropacitymax: 0.6,
            cursorwidth: 24,
            usetransition: true,
            hwacceleration: true,
            autohidemode: "hidden"
        });

    };

    this.rightMenu = function() {
        $('.opener-right-menu').on('click', function() {
            userList();
            $(".user").niceScroll();
            $(".user ul li").on('click', function() {
                $(".user-list ul").getNiceScroll().remove();
                $(".user").hide();
                $(".chatbox").show(1000);
                userList();
            });

            $(".close-chat").on("click", function() {
                $(".user").show();
                $(".chatbox").hide(1000);
            });

            $(".line-chart").width("100%");

            if($('#left-menu .sub-left-menu').is(':visible')) {
                $('#left-menu .sub-left-menu').animate({
                    'width': '0px'
                }, 'slow', function() {
                    $('#left-menu .sub-left-menu').hide();
                    $('.overlay').show();
                    $('.opener-left-menu').removeClass('is-open');
                    $('.opener-left-menu').addClass('is-closed');
                });

                $('#content').animate({
                    'padding-left': '0px'
                }, 'slow');
            }

            if($('#right-menu').is(':visible')) {
                $('#right-menu').animate({
                    'width': '0px'
                }, 'slow', function() {
                    $('#right-menu').hide();
                });
                $('#content').animate({
                    'padding-right': '0px'
                }, 'slow');
            } else {
                $('#right-menu').show();
                $('#right-menu').animate({
                    'width': '230px'
                }, 'slow');
                $('#content').animate({
                    'padding-right': '230px'
                }, 'slow');
            }
        });
    };

    $(".box-v6-content-bg").each(function() {
        $(this).attr("style", "width:" + $(this).attr("data-progress") + ";");
    });

    $('.carousel-thumb').on('slid.bs.carousel', function() {
        if($(this).find($(".item")).is(".active")) {
            var Current = $(this).find($(".item.active")).attr("data-slide");
            $(".carousel-thumb-img li img").removeClass("animated rubberBand");
            $(".carousel-thumb-img li").removeClass("active");

            $($(".carousel-thumb-img").children()[Current]).addClass("active");
            $($(".carousel-thumb-img li").children()[Current]).addClass("animated rubberBand");
        }
    });

    $(".carousel-thumb-img li").on("click", function() {
        $(".carousel-thumb-img li img").removeClass("animated rubberBand");
        $(".carousel-thumb-img li").removeClass("active");
        $(this).addClass("active");
    });

    $("#mimin-mobile-menu-opener").on("click", function(e) {
        $("#mimin-mobile").toggleClass("reverse");
        var rippler = $("#mimin-mobile");
        if(!rippler.hasClass("reverse")) {
            if(rippler.find(".ink").length == 0) {
                rippler.append("<div class='ink'></div>");
            }
            var ink = rippler.find(".ink");
            ink.removeClass("animate");
            if(!ink.height() && !ink.width()) {
                var d = Math.max(rippler.outerWidth(), rippler.outerHeight());
                ink.css({
                    height: d,
                    width: d
                });

            }
            var x = e.pageX - rippler.offset().left - ink.width() / 2;
            var y = e.pageY - rippler.offset().top - ink.height() / 2;
            ink.css({
                top: y + 'px',
                left: x + 'px',
            }).addClass("animate");

            rippler.css({
                'z-index': 9999
            });
            rippler.animate({
                backgroundColor: "#FF6656",
                width: '100%'
            }, 750);
            rippler.animate({
                backgroundColor: "#FF6656",
                width: '100%'
            });

            $("#mimin-mobile .ink").on("animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
                function(e) {
                    $(".sub-mimin-mobile-menu-list").show();
                    $("#mimin-mobile-menu-opener span").removeClass("fa-bars").addClass("fa-close").css({
                        "font-size": "2em"
                    });
                });
        } else {

            if(rippler.find(".ink").length == 0) {
                rippler.append("<div class='ink'></div>");
            }
            var ink = rippler.find(".ink");
            ink.removeClass("animate");
            if(!ink.height() && !ink.width()) {
                var d = Math.max(rippler.outerWidth(), rippler.outerHeight());
                ink.css({
                    height: d,
                    width: d
                });

            }
            var x = e.pageX - rippler.offset().left - ink.width() / 2;
            var y = e.pageY - rippler.offset().top - ink.height() / 2;
            ink.css({
                top: y + 'px',
                left: x + 'px',
            }).addClass("animate");
            rippler.animate({
                backgroundColor: "transparent",
                'z-index': '-1'
            }, 750);

            $("#mimin-mobile .ink").on("animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
                function(e) {
                    $("#mimin-mobile-menu-opener span").removeClass("fa-close").addClass("fa-bars").css({
                        "font-size": "1em"
                    });
                    $(".sub-mimin-mobile-menu-list").hide();
                });
        }
    });

    $(".form-text").on("click", function() {
        $(this).before("<div class='ripple-form'></div>");
        $(".ripple-form").on("animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
            function(e) {
                // do something here
                $(this).remove();
            });
    });

    $('.mail-wrapper').find('.mail-left').css('height', $('.mail-wrapper').innerHeight());

    $("#left-menu ul li a").ripple();
    $(".ripple div").ripple();

    $("#carousel-example3").carouselAnimate();
    $("#left-menu .sub-left-menu").niceScroll();
    $(".sub-mimin-mobile-menu-list").niceScroll({
        touchbehavior: true,
        cursorcolor: "#FF00FF",
        cursoropacitymax: 0.6,
        cursorwidth: 24,
        usetransition: true,
        hwacceleration: true,
        autohidemode: "hidden"
    });

    $(".fileupload-v1-btn").on("click", function() {
        var wrapper = $(this).parent("span").parent("div");
        var path = wrapper.find($(".fileupload-v1-path"));
        $(".fileupload-v1-file").click();
        $(".fileupload-v1-file").on("change", function() {
            path.attr("placeholder", $(this).val());
            console.log(wrapper);
            console.log(path);
        });
    });

    var datetime = null,
        date = null;

    $("body").tooltip({
        selector: '[data-toggle=tooltip]'
    });
    leftMenu();
    rightMenu();
    treeMenu();
    hide();
})(jQuery);

function StringFormat() {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
}