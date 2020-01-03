function changePasword(password,call){
    let resultCode ;
    //time
    let user = {
        new_password:password,
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/password",
        contentType:"application/json",
        data:JSON.stringify(user),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}

/**
 *
 * @param ajaxNum 需要请求的数量
 * @param data    传入的data的个数，是一个数组
 * @param call    回掉
 */
function sendData(ajaxNum,data,call){

    for(let i = 0 ;i < ajaxNum;i++){

    }
}