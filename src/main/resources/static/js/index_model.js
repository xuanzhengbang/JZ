function getInfo() {
    $.get("/dineType/list", function(result){
        if($("#showError").text())
        {
            $("#showError").empty();
        }
        $("#model_dineType").empty();
        for (var i in result.data){
            $("#model_dineType").append("<option value='"+result.data[i].name +"'>"+result.data[i].name+"</option>");
        }
    });

    $.get("/place/list", function(result){
        $("#model_place").empty();
        for (var i in result.data){
            if(result.data[i].items.length>0){
                var htm = "<optgroup label=" + result.data[i].name + ">";
                for(var k in result.data[i].items){
                    htm += "<option value='" + result.data[i].items[k].name + "' >"+ result.data[i].items[k].name +"</option>";
                }
                htm += "</optgroup>";
                $("#model_place").append(htm);
            } else {
                $("#model_place").append("<option value='"+result.data[i].name +"'>"+result.data[i].name+"</option>");
            }

        }
    });

    var table = document.getElementById("model_table");
    $("#model_table  tr").empty("");
    var bts = window.localStorage.getItem("bts");
    if(bts != null&& bts !="null"){
        var bts_array = bts.split(',');
        if(bts_array!=null && bts_array.length>0){
            for(var k in bts_array){
                if(bts_array[k].length>0){
                    var newRow = table.insertRow();
                    var cell = newRow.insertCell(0);
                    cell.innerHTML=bts_array[k];
                }
            }
        }
    }
    
}

//提交菜单
// :visible 选择器表示获取可见的元素
//
// $("table#test tr:visible") 表示获取id为test的table下的可见行对象
//
// length属性表示对象的长度，如果没有可见的tr，那么获取长度为0

$(".btn-primary").on("click",function () {
    var date=$("#date_input").val();
    var bookDate=$("#foodType").find("option:selected").text();

    var place=$("#model_place").find("option:selected").text();

    var model_dineType=$("#model_dineType").find("option:selected").text();
    var price=$("#price").val();
    var startTable=$("#Stable").val();

    if( !$("table tr:visible").length){
        $("#showError").empty();
        $("#showError").append("<span>您没有选择菜品！</span>");
    }else if(!date){
        $("#showError").empty();
        $("#showError").append("<span>您没选择时间！</span>");
    }else if(!price){
        $("#showError").empty();
        $("#showError").append("<span>您没填写菜品价格！</span>");

    }else if(!(startTable)){
        $("#showError").empty();
        $("#showError").append("<span>您没选择桌位！</span>");
    }else{
        storage(date,bookDate,place,model_dineType,price,startTable);
    }
        //判断完毕后再进行打印

    // post_create_setmeal(date,bookDate,place,model_dineType,price,startTable,["清汤甲鱼","年糕炒蟹块","蛋黄焗蟹"])

});
//根据ajax请求判断是否成功插入数据库
function storage(date,book,place,model_dineType,price,startTable){
        var bookDate=date+" "+book;
        var num=startTable;
       // 获取表格内所有的内容
       var foods = [];
        $("table#model_table").find("td").each(function () {
            foods.push($(this).text());
        });
        console.log(foods);
       $.ajax({
            type: "post",
            url: "/setmeal/create",
            data:  "bookDate="+bookDate+"&place="+place+"&dineType="+model_dineType+"&num="+num+"&price="+price+"&foods="+foods,
            dataType: "text",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (result) {
                //
                printFood(date,book,place,model_dineType,num,price);
            },
           error:function(result){
               $("#showError").empty();
               $("#showError").append("<span>数据保存不成功！</span>");

           }
       });
}
//da打印页面设置
function printFood(date,book,place,model_dineType,num,price) {
    //清空当前元素所属的页面，再添加上相应的属性
    //跳转页面进行打印，跳转只是增加页面内容
    $("#printTable").append("<tr><td>军转大酒店菜单</td></tr>");
    $("#printTable").append("<tr><td>时间："+date+"</td></tr>");
    $("#printTable").append("<tr><td>餐型："+book+"</td></tr>");
    $("#printTable").append("<tr><td>地点："+place+"</td></tr>");
    $("#printTable").append("<tr><td>类别："+model_dineType+"</td></tr>");
    $("#printTable").append("<tr><td>标准："+price+"元/桌</td></tr>");
    $("#printTable").append("<tr><td>桌数："+num+"桌</td></tr>");
    $("#printTable").append("<tr><td>----------------------------------------------------------</td></tr>");

    var table = document.getElementById("printTable");
    var bts = window.localStorage.getItem("bts");
    if(bts != null&& bts !="null"){
        var bts_array = bts.split(',');
        if(bts_array!=null && bts_array.length>0){
            for(var k in bts_array){
                if(bts_array[k].length>0){
                    var newRow = table.insertRow();
                    var cell = newRow.insertCell(0);
                    cell.innerHTML=bts_array[k];
                }
            }
        }
    }

    CreateOneFormPage();
    LODOP.PREVIEW();

}
function CreateOneFormPage(){
    LODOP=getLodop();
    LODOP.PRINT_INIT("军转大酒店菜单");
 
    LODOP.ADD_PRINT_TEXT(10,10,100,300,"jz.opopto.com");
    LODOP.SET_PRINT_STYLEA(2,"FontSize",12);
    LODOP.SET_PRINT_STYLEA(2,"Bold",1);
    LODOP.ADD_PRINT_HTM(40,40,"100%","10%",document.getElementById("print").innerHTML);
};