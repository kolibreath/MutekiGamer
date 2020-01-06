function news(gameId,call) {
    let allData={};
    let newsRequest=$.ajax(
        (
            {
                url:"/battle/news" +gameId,
                type:"GET",
                success:function(result){
                    allData.banner = result;
                },
                error:function (result) {
                    allData.banner = result;
                },
                complete:function (result) {
                }
            }
        )
    )

    $.when(newsRequest).done(() =>{call(allData)});
}