package com.moba.security;

import com.moba.domain.SysModule;
import com.moba.domain.UserInfo;
import com.moba.emums.IsDel;
import com.moba.emums.roleUser.UserType;
import com.moba.service.SysModuleService;
import com.moba.service.UserInfoService;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SysModuleService sysModuleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo ui = new UserInfo();
        ui.setUserName(username);
        ui.setIsDel(0);
        // ui.setId(Integer.valueOf(username));
        UserInfo sysUser = userInfoService.getEntity(ui);
        if (null == sysUser) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        List<String> authorities = new ArrayList<>();

        // ==========获取用户资源权限====================
        SysModule sysAll = new SysModule();
        sysAll.setIsDel(IsDel.IS_DEL_0.getIndex());
        sysAll.getMap().put("user_id", sysUser.getId());
        sysAll.getMap().put("is_public", "true");

        if (sysUser.getUserType().intValue() == UserType.USER_TYPE_1.getIndex()) {
            sysAll.getMap().put("is_admin", "true");
        }

        List<SysModule> sysAllList = sysModuleService.getEntityList(sysAll);

        for (SysModule sys : sysAllList) {
            if (StringUtils.isNotBlank(sys.getAuthorityCode())) {
                authorities.add(sys.getAuthorityCode());
            }
        }
        sysUser.setAuthorities(authorities);
        return JwtUserFactory.create(sysUser);
    }
}
