function defultDisplay(call) {
    let allData = {};
    let post =$.ajax(({
        url:"post/defult",
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

function officalDefault(call) {
    let allData = {};
    let post =$.ajax(({
        url:"battle/newsDefault",
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