function test(call){
    let allData={};
    let request = $.ajax(({
        type:"GET",
        url:"/user/info",
        success:function (result) {
            allData.info = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));
     $.when(request).done( () => { call(allData) } );
}