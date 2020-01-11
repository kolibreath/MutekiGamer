
//有关post 和 response的操作


function createParse(){
    Date.prototype.format = function(fmt) {
        let o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }
}
function newPost(postContent,postTitle,tag,call){
    let resultCode ;
    //time
    createParse();
    let time= (new Date()).format("yyyy-MM-dd");
    let post = {
        time:time,
        postContent:postContent,
        postTitle:postTitle,
        tag:tag
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

function getMyPost(call) {
        let allData = {};
        let post =$.ajax(({
            url:"post/mypost",
            type:"GET",
            success:function(result){
                allData.post = result;
            },
            //todo 错误处理
            error:function (result) {

            },
            complete:function (result) {

            }
        }));

        $.when(post).done(() =>{call(allData)});

}

