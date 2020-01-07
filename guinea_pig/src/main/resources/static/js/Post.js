
//有关post 和 response的操作

function newPost(postContent,postTitle,tag,gameId,call){
    let resultCode ;
    //time
    let time= Date.parse( new Date());
    let post = {
        time:time,
        postContent:postContent,
        postTitle:postTitle,
        tag:tag,
        gameId:gameId
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/post/new_post",
        contentType:"application/json",
        data:JSON.stringify(post),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}


function newResponse(responseContent,postId,call){
    let resultCode ;
    //time
    let time= Date.parse( new Date());
    let post = {
        time:time,
        responseContent:responseContent,
        postId:postId
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/post/new_response",
        contentType:"application/json",
        data:JSON.stringify(post),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}

// function getPostByUserIdGameId(gameId,call) {
//     let allData={};
//     let post = $.ajax(({
//         url:"post/usergame/"+gameId,
//         type:"GET",
//         success:function (result) {
//             allData.post = result;
//         },
//         error:function (result) {
//             allData.post = result;
//         },
//         complete:function (result) {
//             // completeFunction(result);
//         }
//     }));
//
//     $.when(post).done(() =>{call(allData)});
//
// }
//删除帖子 postId 需要是一个int
function deletePost(postId,call){
    let resultCode ;
    let post = {
        postId:postId
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/post/new_response",
        contentType:"application/json",
        data:JSON.stringify(post),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}