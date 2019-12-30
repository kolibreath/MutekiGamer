function follow(otherUserId,call){
    let resultCode ;
    let follow = {
        otherUserId:otherUserId,
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/user/follow",
        contentType:"application/json",
        data:JSON.stringify(follow),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}


function unfollow(otherUserId,call){
    let resultCode ;
    let follow = {
        otherUserId:otherUserId,
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/user/unfollow",
        contentType:"application/json",
        data:JSON.stringify(follow),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))
}


function getUserInfo(call) {
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