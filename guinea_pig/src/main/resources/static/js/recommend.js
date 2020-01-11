function recommend(call) {
    let allData={};
    let request = $.ajax({
        type:"GET",
        url:"/user/test_al",
        success:function (result) {
            allData = result;
        },
        error:function () {
        }
    });
    $.when(request).done( () => { call(allData) } );
}

function followrecommend(gameId,call) {
    let resultCode ;

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/user/followRecommend/"+gameId,
        success:function () {
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))
}