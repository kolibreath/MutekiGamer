function search(content, call){
    alert("fuck tunshu ");
    let allData = {};
    let request = $.ajax(({
        url:"/battle/search/"+content,
        method:"GET",
        success:function (result) {
            console.log(result);
            allData.result = result;
        },
        error:function (result) {
            console.log(result);
            allData.result = result;
        },complete:function (result) {
            alert("result");
        }
    }));

    $.when(request).done(() => {call(allData)});
}