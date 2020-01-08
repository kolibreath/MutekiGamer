function news(gameId,call) {
    let allData={};
    let newsRequest=$.ajax(
        (
            {
                url:"/battle/news" +gameId,
                type:"GET",
                success:function(result){
                    allData.post = result;
                },
                error:function (result) {
                    allData.post = result;
                },
                complete:function (result) {
                }
            }
        )
    );

    $.when(newsRequest).done(() =>{call(allData)});
}
function search(content, call){
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


