function news(call) {
    let allData={};
    let newsRequest=$.ajax(
        (
            {
                url:"/battle/news",
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