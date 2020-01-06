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
    )

    $.when(newsRequest).done(() =>{call(allData)});
}