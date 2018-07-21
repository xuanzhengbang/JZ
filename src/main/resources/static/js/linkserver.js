$(function(){
    $.ajax({
        type: 'get',
        url:'http://jz.opopto.com/food/list',
        async: true,
        jsonp: 'jsoncallback',
        success:function (data) {
            //获取数据
            var foodname=$.parseJSON(data);

        }
    });

});