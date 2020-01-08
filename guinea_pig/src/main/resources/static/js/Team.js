function getTeamInfo(gameId, call){
    let allData = {};
    let request = $.ajax(
        (
            {
        url:"/battle/team/"+gameId,
        method:"GET",
        success:function (result) {
            allData.result = result;
        },
        error:function (result) {
            allData.result = result;
        },complete:function (result) {
        }
    }));

    $.when(request).done(() => {call(allData)});
}

function match(gameId,call) {
    let allData = {};
    let request = $.ajax(
        (
            {
                url:"/battle/match/"+gameId,
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