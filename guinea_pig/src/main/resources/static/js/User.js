function follow(otherUserId,call){
    let resultCode ;
    let follow = {
        otherUserId:otherUserId,
    };

    let createNewPost = $.ajax(({
        type:"POST",
        url:"/user/follow",
        contentType:"application/json",
        data:JSON.stringify(follow),
        success:function (result) {
            resultCode = result;
        },
        error:function () {
            //todo 错误处理
        }
    }));

    $.when(createNewPost).done(()=>call(resultCode))

}


function unfollow(otherUserId,call){
    let resultCode ;
    let follow = {
        otherUserId:otherUserId,
    };
    let createNewPost = $.ajax(({
        type:"POST",
        url:"/user/unfollow",
        contentType:"application/json",
        data:JSON.stringify(follow),
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
 * 获取用户个人页面信息
 * @param call
 */
function getUserInfo(call) {
    let allData={};

    let request = $.ajax({
        type:"GET",
        url:"/user/info",
        success:function (result) {
            allData = result;
        },
        error:function () {
            //todo 错误处理
        }
    });

    $.when(request).done( () => { call(allData) } );
}


function sendEmail(call){
    let allData={};

    let request = $.ajax(({
        type:"GET",
        url:"/mail/send",
        success:function (result) {
            alert("fuck 豚鼠大邮箱");
            allData.info = result;
        },
        error:function (result) {
            alert("fuck 豚鼠大邮箱" + result);
            //todo 错误处理
        }
    }));

    $.when(request).done( () => { call(allData) } );
}


function alterUserInfo (avatar,nickname,sex, age, city,occupation,intro,call ) {
    let postBody = {
        nickname:nickname,
        avatar:avatar,
        sex:sex,
        age:age,
        city:city,
        occupation:occupation,
        intro:intro
    };

    let allData = {};
    let alterUserInfo = $.ajax({
        url:"/user/alter_user_info",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(postBody),
        success:function (result) {
            allData = result;
        },
        error:function (result) {
            allData = result;
        }
    });

    $.when(alterUserInfo).done( ()=> {call(allData)});
}

//直接推荐用户数据库中的其他用户
function getRecommendUser(call) {
    let allData={};

    let request = $.ajax({
       url:"/user/hot",
       type:"GET",
        success:function (result) {
            allData = result
        },
        error:function (result) {
           allData = result;
        }
    });

    $.when(request).done( () => { call(allData) } );
}
