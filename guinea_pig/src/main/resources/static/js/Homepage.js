
function homepage(call) {
    let allData = {};

    let userInfoRequest = $.ajax({
        url:"/user/simple_info",
        type:"GET",
        success:function (result) {
            allData.info = result;
        },
        error:function (result) {
            allData.info = result;
        }
    });

    //请求banner 数据
    let bannerRequest= $.ajax(({
        url:"/banner/all",
        type:"GET",
        success:function(result){
            allData.banner = result;
           // console.log(result);
        },
        error:function (result) {
            allData.banner = result;
        },
        complete:function (result) {
        }
    }));

    //请求用户游戏数据
    let gamesRequest = $.ajax(({
        url:"/game/user",
        type: "GET",
        success:function (result) {
            allData.game  = result;
        },
        error: function (result) {
            allData.game  = result;
        },
        complete:function (result) {
            // completeFunction(result);
        }
    }));

    //请求帖子数据
    let postRequest = $.ajax(({
        url:"/post/selected",
        type:"GET",
        success:function (result) {
            allData.post = result;
        },
        error:function (result) {
            allData.post = result;
        },
        complete:function (result) {
            // completeFunction(result);
        }
    }));

    // let array = {};
    // array[0] = bannerRequest;
    // array[1] = postRequest;
    // array[2] = gamesRequest;
    //todo 前端ajax filter
    $.when(userInfoRequest,gamesRequest, postRequest, bannerRequest).done(() => {call(allData)});
}

function getPostByGameId(gameId,call){
    let allData = {};
    let post =$.ajax(({
        url:"post/user/"+gameId,
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

function homepage1(call) {
    let allData = {};
    //请求用户游戏数据
    let gamesRequest = $.ajax(({
        url:"/game/allgame",
        type: "GET",
        success:function (result) {
            allData.game  = result;
        },
        error: function (result) {
            allData.game  = result;
        },
        complete:function (result) {
            // completeFunction(result);
        }
    }));

    //请求帖子数据
    let postRequest = $.ajax(({
        url:"/post/selected",
        type:"GET",
        success:function (result) {
            allData.post = result;
        },
        error:function (result) {
            allData.post = result;
        },
        complete:function (result) {
            // completeFunction(result);
        }
    }));

    //todo 前端ajax filter
    $.when(  gamesRequest,postRequest).done(() => {call(allData)});
}


function getRecommendGames(call){
    let allData = {};
    let post =$.ajax(({
        url:"game/hot",
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

function search4content(content,call){
    let allData = {};
    let postbody = {"search":content};
    let post =$.ajax(({
        async:false,
        url:"battle/game",
        type:"GET",
        contentType:'application/x-www-form-urlencoded',
        dataType:"json",
        data:postbody,
        success:function(result){
            allData = result;
        },
        //todo 错误处理
        error:function (result) {

        },
        complete:function (result) {

        }
    }));

    $.when(post).done(() =>{call(allData)});
}

