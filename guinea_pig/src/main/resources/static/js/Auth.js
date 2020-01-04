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
