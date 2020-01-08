function PostDetail(call) {
    let allData = {};
    let post =$.ajax(({
        url:""
        type:"GET",
        success:function(result){
            allData.post = result;
        },
        //todo 错误处理
        error:function (result) {

        },
        complete:function (result) {

        }
    }));

    $.when(post).done(() =>{call(allData)});

}