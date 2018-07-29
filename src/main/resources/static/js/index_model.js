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
            $("#model_place").append("<option value='"+result.data[i].name +"'>"+result.data[i].name+"</option>");
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
// function printOrder() {
//     var startStr = "<!--startprint-->";
//     var endStr = "<!--endprint-->";
//     var printHtml = document.getElementById("myModal").innerHTML;
//     window.document.body.innerHTML = printHtml;
//     window.print();
// }

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
    var endTable=$("#Etable").val();


    if( !$("table tr:visible").length){
        $("#showError").empty();
        $("#showError").append("<span>您没有选择菜品！</span>");
    }else if(!date){
        $("#showError").empty();
        $("#showError").append("<span>您没选择时间！</span>");
    }else if(!price){
        $("#showError").empty();
        $("#showError").append("<span>您没填写菜品价格！</span>");
    }else if(!(startTable&&endTable)){
        $("#showError").empty();
        $("#showError").append("<span>您没选择桌位！</span>");
    }else if(storage(date,bookDate,place,model_dineType,price,startTable,endTable)){ //判断完毕后再进行打印
        var startStr = "<!--startprint-->";
        var endStr = "<!--endprint-->";
        var printHtml = document.getElementById("myModal").innerHTML;
        window.document.body.innerHTML = printHtml;
        window.print();
    }else{
        $("#showError").empty();
        $("#showError").append("<span>数据保存不成功！</span>");
    }
});
//根据ajax请求判断是否成功插入数据库
function storage(date,book,place,model_dineType,price,startTable,endTable){
        var bookDate=date+" "+book;
        var num=startTable+"-"+endTable;
       // 获取表格内所有的内容
       var arr = new Array();
        $("table#model_table").find("td").each(function () {
            //保存内容
            arr.push({"foodName":$(this).text()});
        });
        var con={};
        con["price"]=price;
        con["bookDate"]=bookDate;
        con["place"]=place;
        con["dineType"]=model_dineType;
        con["num"]=num;
        var JsonObj=JSON.stringify(con);
        var jsobj=[];
        jsobj.push(con);
        for(var i in arr){
            var it={
                item:arr;
            }
            jsobj.push(it);
        }
        var flag=0;
        console.log(JSON.stringify(jsobj));


       $.ajax({
            type: "post",
            url: "/setmeal/create",
            data:  JSON.stringify(con),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function (result) {
                flag=1;
             }
       });
       return flag;
}