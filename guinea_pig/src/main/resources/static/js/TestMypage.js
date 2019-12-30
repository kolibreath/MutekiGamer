function mypage(call) {
    let allData={};

    let mynameRequest=$.ajax({
        url:"/user/myname",
    type:"GET",
        success:function(result){
        allData.name= result;
    },
    error:function (result) {
        allData.name = result;
    },
    complete:function (result) {
        // completeFunction(result)
    }
    });
    $.when(mynameRequest).done(() =>{call(allData)});
}