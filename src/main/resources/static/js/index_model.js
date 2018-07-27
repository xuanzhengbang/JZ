function getInfo() {
    $.get("/dineType/list", function(result){
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
function print() {

}