function getInfo() {
    $("#foodName").val("");
    $("#price").val("");
    $.get("/dineType/list", function(result){
        if($("#showError").text())
        {
            $("#showError").empty();
        }
        $("#model_dineType").empty();
        for (var i in result.data){
            if (result.data[i].name!=null){
                $("#model_dineType").append("<option value='"+result.data[i].name +"'>"+result.data[i].name+"</option>");
            }
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
                console.log(bts_array[k]);
                console.log("长度"+bts_array[k].length);
                if(bts_array[k]!="null"&&bts_array[k].length>0){
                    var newRow = table.insertRow();
                    var cell = newRow.insertCell(0);
                    cell.innerHTML=bts_array[k];
                }
            }
        }
    }
    
}

$(".btn-primary").on("click",function () {
    var date=$("#date_input").val();
    var bookDate=$("#foodType").find("option:selected").text();

    var place=$("#model_place").find("option:selected").text();

    var model_dineType=$("#model_dineType").find("option:selected").text();
    var price=$("#price").val();
    var startTable=$("#Stable").val();
    var standardName=$("#foodName").val();

    $("#printTable").empty();
    $(".time").empty();
    $(".foodType").empty();
    $(".place").empty();
    $(".type").empty();
    $(".price").empty();
    $(".num").empty();
    if( !$("table tr:visible").length){
        $("#showError").empty();
        $("#showError").append("<span>您没有选择菜品！</span>");
    }else if(!date){
        $("#showError").empty();
        $("#showError").append("<span>您没选择时间！</span>");
    }else if(!price){
        $("#showError").empty();
        $("#showError").append("<span>您没填写菜品价格！</span>");

    }else if(!startTable){
        $("#showError").empty();
        $("#showError").append("<span>您需要多少桌！</span>");
    }else if(!standardName){
        $("#showError").empty();
        $("#showError").append("<span>套餐名称不对！</span>");

    }else {
        storage(date,bookDate,place,model_dineType,price,startTable,standardName);
    }


});
var LODOP;

//根据ajax请求判断是否成功插入数据库
function storage(date,book,place,model_dineType,price,startTable,standardName){
        var bookDate=date+" "+book;
        var num=startTable;
       // 获取表格内所有的内容
       var foods = [];
        $("table#model_table").find("td").each(function () {
            foods.push($(this).text());
        });

       $.ajax({
            type: "post",
            url: "/setmeal/create",
            data:  "bookDate="+bookDate+"&place="+place+"&dineType="+model_dineType+"&standardName="+standardName+"&num="+num+"&price="+price+"&foods="+foods,
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
    $(".time").append("时间："+date);
    $(".foodType").append("餐型："+book);
    $(".place").append("地点："+place);
    $(".type").append("类别："+model_dineType);
    $(".price").append("标准："+price+"元/桌");
    $(".num").append("桌数："+num+"桌");

    var table = document.getElementById("printTable");
    var bts = window.localStorage.getItem("bts");
    if(bts != null&& bts !="null"){
        var bts_array = bts.split(',');
        if(bts_array!=null && bts_array.length>0){
            for(var k in bts_array){
                if(bts_array[k]!="null"&&bts_array[k].length>0){
                    var newRow = table.insertRow();
                    var cell = newRow.insertCell(0);
                    cell.innerHTML=bts_array[k];
                }
            }
        }
    }

    CreateTwoFormPage();
    LODOP.PREVIEW();

}

//在菜单保存页面打印頁面同時并保存数据
$("#printBtn").on("click",function () {
    //将需要提交的数据获取
    $("#showError").empty();
    $("#printTable").empty();
    $(".time").empty();
    $(".foodType").empty();
    $(".place").empty();
    $(".type").empty();
    $(".price").empty();
    $(".num").empty();

    //获取当前所有的选中选项
    var date=$("#date_input").val();
    var dateFood=$("#foodType").find("option:selected").text();
    var place=$("#model_chageplace").find("option:selected").text();
    var model_dineType=$("#model_chagedineType").find("option:selected").text();
    var price=$("#modal_price").text();
    var num=$("#chageStable").val();


    if (!date){
        $("#showError").empty();
        $("#showError").append("<span>未选择时间！</span>");
    }else if(!dateFood) {
        $("#showError").empty();
        $("#showError").append("<span>您没选择餐型！</span>");
    } else if(!place){
        $("#showError").empty();
        $("#showError").append("<span>您没填写就餐地点！</span>");
    } else if(!model_dineType){
        $("#showError").empty();
        $("#showError").append("<span>未选择餐厅类别！</span>");
    } else if(!num){
        $("#showError").empty();
        $("#showError").append("<span>未添加桌数！</span>");
    }else{
        // $("#printTable").append("<tr><td style='padding: 0px'>时间："+date+"</td><td style='padding: 0px'>餐型："+dateFood+"</td></tr>");
        $(".time").append("时间："+date);
        $(".foodType").append("餐型："+dateFood);
        $(".place").append("地点："+place);
        $(".type").append("类别："+model_dineType);
        $(".price").append("标准："+price+"元/桌");
        $(".num").append("桌数："+num+"桌");

        $("table#model_table").find("td").each(function () {
            $("#printTable").append("<tr><td>"+$(this).text()+"</td></tr>");
        });
        CreateTwoFormPage();
        LODOP.PREVIEW();
    }
});

function CreateTwoFormPage() {
    LODOP=getLodop();
    // LODOP.ADD_PRINT_TEXT(21,300,151,30,"jz.banana96.com");
    // ADD_PRINT_TEXT(Top,Left,Width,Height,strContent)
    // ADD_PRINT_LINE(Top1,Left1, Top2, Left2,intLineStyle, intLineWidth)
    LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
    LODOP.SET_PRINT_STYLEA(0,"Bold",1);
    LODOP.SET_PRINT_STYLEA(0,"ItemType",1);
    LODOP.SET_PRINT_STYLEA(0,"Horient",2);
    LODOP.SET_PRINT_STYLEA(0,"Vorient",3);
    // ADD_PRINT_HTML(Top,Left,Width,Height, strHtmlContent)
    LODOP.ADD_PRINT_HTM(20,0,"80mm","100%",document.getElementById("print").innerHTML);

}

