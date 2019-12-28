
function homepage(call) {
    let allData = {};

    //请求banner 数据
    let bannerRequest= $.ajax(({
        url:"homepage/banners",
        type:"GET",
        success:function(result){
            allData.banner = result;
        },
        error:function (result) {
            allData.banner = result;
        },
        complete:function (result) {
            // completeFunction(result)
        }
    }));

    //请求用户游戏数据
    let gamesRequest = $.ajax(({
        url:"homepage/games",
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
        url:"homepage/posts",
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

    $.when(gamesRequest,bannerRequest,postRequest).done(() => {call(allData)});
}

