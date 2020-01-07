//package com.guineap_pig_329.guinea_pig.dao;
//
//import com.guineap_pig_329.guinea_pig.error.UserNotFoundException;
//import com.guineap_pig_329.guinea_pig.repo.UserRepo;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class LoginRealm  extends AuthorizingRealm {
//
//
//    @Autowired
//    private UserRepo userRepo;
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//
//    //进行用户真正的登陆流程
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String userEmail= (String) authenticationToken.getPrincipal();
//        User user = userRepo.findByUserEmail(userEmail);
//        if(user == null){
//            throw new UnknownAccountException("账户不存在");
//        }
//        return new SimpleAuthenticationInfo();
//
//    }
//}
//
