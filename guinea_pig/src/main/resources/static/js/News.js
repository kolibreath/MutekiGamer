function getHotPost(call) {
    let allData={};
    let request = $.ajax({
        type:"GET",
        url:"/post/hot",
        success:function (result) {
            allData = result;
        },
        error:function () {
            //todo 错误处理
        }
    });

    $.when(request).done( () => { call(allData) } );
}

function getBanners(call) {
    let allData={};
    let request = $.ajax({
        type:"GET",
        url:"/banner/all",
        success:function (result) {
            allData = result;
        },
        error:function () {
        }
    });

    $.when(request).done( () => { call(allData) } );
}