package com.moba.auth;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moba.annotation.ParamValid;
import com.moba.constant.ConstantKey;
import com.moba.controller.BaseController;
import com.moba.domain.RoleUser;
import com.moba.domain.UserInfo;
import com.moba.emums.CodeType;
import com.moba.emums.IsDel;
import com.moba.security.JwtAuthenticationRequest;
import com.moba.security.JwtTokenUtil;
import com.moba.service.RoleUserService;
import com.moba.service.UserInfoService;
import com.moba.utils.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthController extends BaseController {
    private String tokenHeader = ConstantKey.JWT_HEADER;

    @Autowired
    private AuthService authService;

    @Autowired
    UserInfoService userInfoService;

    @Resource
    RoleUserService roleUserService;

    @ParamValid
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public Object createAuthenticationToken(
            @RequestBody @Valid JwtAuthenticationRequest authenticationRequest, BindingResult result) throws AuthenticationException {
        final String token =
                authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        return ResultUtils.success(jsonObject);
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public Object refreshAndGetAuthenticationToken(HttpServletRequest request)
            throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResultUtils.error(CodeType.CODE_TYPE_100.getIndex(), "刷新token失败");
        } else {
            return ResultUtils.success(null);
        }
    }

    @RequestMapping(value = "/auth/remove", method = RequestMethod.GET)
    public Object removeAndGetAuthenticationToken(HttpServletRequest request)
            throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        Boolean flag = authService.remove(token);
        if (flag) {
            return ResultUtils.success(null);
        } else {
            return ResultUtils.error(CodeType.CODE_TYPE_100.getIndex(), "销毁token失败");
        }
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public UserInfo register(@RequestBody UserInfo addedUser) throws AuthenticationException {
        return authService.register(addedUser);
    }

    @GetMapping(value = "/auth/token")
    public Object token(HttpServletRequest request, String username, String password)
            throws AuthenticationException {

        final String token = authService.login(username, password);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);

        return ResultUtils.success(jsonObject);
    }

    @GetMapping(value = "/auth/getUserByToken")
    public Object getUserByToken(HttpServletRequest request) {
        String token = request.getHeader(ConstantKey.JWT_HEADER);
        if (token == null || token.isEmpty()) {
            return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), "Token为空，或参数名错误");
        }
        token = token.substring(ConstantKey.JWT_TOKEN_HEADER.length());
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String username = jwtTokenUtil.getUsernameFromToken(token);
        if (StringUtils.isBlank(username)) {
            return ResultUtils.error(CodeType.CODE_TYPE_102.getIndex(), CodeType.CODE_TYPE_102.getName());
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(username);
        userInfo.setIsDel(IsDel.IS_DEL_0.getIndex());
        userInfo = userInfoService.getEntity(userInfo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", userInfo);

        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userInfo.getId());
        List<RoleUser> roleList = roleUserService.getEntityList(roleUser);
        JSONArray jsonArray = new JSONArray();
        if(null != roleList && roleList.size() > 0){
            for (RoleUser temp : roleList){
                jsonArray.add(temp.getRoleId());
            }
        }
        jsonObject.put("access", jsonArray);

        return ResultUtils.success(jsonObject);
    }
}
