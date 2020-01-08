function postDetail(call) {
    let allData = {};
    let request = $.ajax(
        (
            {
                url:"/post/postDetail",
                method:"GET",
                success:function (result) {
                    allData.result = result;
                },
                error:function (result) {
                    allData.result = result;
                },
                complete:function (result) {
                }
            }));

    $.when(request).done(() => {call(allData)});
}

function postUserInfo(userid,call) {
    let allData = {};
    let request = $.ajax(
        (
            {
                url:"/user/postUserInfo/"+userid,
                method:"GET",
                success:function (result) {
                    allData = result;
                },
                error:function (result) {
                    allData = result;
                },
                complete:function (result) {
                }
            }));

    $.when(request).done(() => {call(allData)});
}

function getResponse(postId,call) {
    let allData = {};
    let request = $.ajax(
        (
            {
                url:"/post/getResponse/"+postId,
                method:"GET",
                success:function (result) {
                    allData = result;
                },
                error:function (result) {
                    allData = result;
                },
                complete:function (result) {
                }
            }));

    $.when(request).done(() => {call(allData)});
}