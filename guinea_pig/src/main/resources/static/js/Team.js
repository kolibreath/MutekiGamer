function search(content,call) {
    let allData={};
    let searchInfo = $.ajax(({
            url:"/battle/search?="+content,
            type:"GET",
            contentType:"application/json",
            success:function (result) {
                allData.info = result;
            },
            error:function () {
                //todo 错误处理
            }
        })
    );
    $.when(searchInfo).done( () => { call(allData) } );
}