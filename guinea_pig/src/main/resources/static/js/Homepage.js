
function homepage(requestNum) {
    let allData = {};
    const allRequestComplete = "allRequestComplete";

    const completeFunction = function(result){
        requestNum --;
        if(requestNum == 0){
            $(document).trigger((allRequestComplete));
        }
    };


    //完成完了所有的业务调用
    $(document).bind(allRequestComplete, function () {
        //调用具体的函数
        console.log(allData);
        return allData;
    });

    //请求banner 数据
    $.ajax(({
        url:"homepage/banners",
        type:"GET",
        success:function(result){
            allData.banner = result;
        },
        error:function (result) {
            allData.banner = result;
        },
        complete:function (result) {
            completeFunction(result)
        }
    }));

    //请求用户游戏数据
    $.ajax(({
        url:"homepage/games",
        type: "GET",
        success:function (result) {
            allData.game  = result;
        },
        error: function (result) {
            allData.game  = result;
        },
        complete:function (result) {
            completeFunction(result);
        }
    }));

    //请求帖子数据
    $.ajax(({
        url:"homepage/posts",
        type:"GET",
        success:function (result) {
            allData.post = result;
        },
        error:function (result) {
            allData.post = result;
        },
        complete:function (result) {
            completeFunction(result);
        }
    }))
}

