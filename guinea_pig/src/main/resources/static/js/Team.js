function getTeamInfo(gameId, call){
    let allData = {};
    let request = $.ajax(({
        url:"/battle/team/"+gameId,
        method:"GET",
        success:function (result) {
            allData.result = result;
        },
        error:function (result) {
            allData.result = result;
        },complete:function (result) {
            alert("result");
        }
    }));

    $.when(request).done(() => {call(allData)});
}