function news(gameId,call) {
    let allData={};
    console.log(gameId);
    let newsRequest=$.ajax(
        (
            {
                url:"/battle/news/" +"" +gameId,
                method:"GET",
                success:function(result){
                    allData.post = result;
                    console.log(result);
                    console.log(allData.post.data);
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
        url:"/battle/search/"+ content,
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


