
function deletePost(postId, call){

    let resultCode;
    let delPost={
        postId:postId,
    };
    let deletePost=$.ajax(({
        type:"POST",
        url:"/post/delete_post",
        contentType:"application/json",
        data:JSON.stringify(delPost),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(deletePost).done(()=>call(resultCode));
}