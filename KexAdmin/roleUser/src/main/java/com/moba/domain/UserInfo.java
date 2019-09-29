package com.moba.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author liu, jia
 * @version 2019-02-20 09:13
 */
@ApiModel(value = "UserInfo")
@Data
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "")
    private String userName;

    @ApiModelProperty(value = "")
    private String realName;

    @ApiModelProperty(value = "用户类型：取base_data 类型为10数据")
    private Integer userType;

    @ApiModelProperty(value = "会员等级base_data:200")
    private Integer userLevel;

    @ApiModelProperty(value = "使用MD5算法加密")
    private String password;

    @ApiModelProperty(value = "")
    private String mobile;

    @ApiModelProperty(value = "所属企业")
    private Integer ownEntpId;

    @ApiModelProperty(value = "")
    private String ownEntpName;

    @ApiModelProperty(value = "")
    private Integer sex;

    @ApiModelProperty(value = "")
    private Date birthday;

    @ApiModelProperty(value = "")
    private String positions;

    @ApiModelProperty(value = "")
    private String officeTel;

    @ApiModelProperty(value = "")
    private String email;

    @ApiModelProperty(value = "")
    private Integer loginCount;

    @ApiModelProperty(value = "")
    private String lastLoginIp;

    @ApiModelProperty(value = "")
    private Date lastLoginTime;

    @ApiModelProperty(value = "")
    private Integer orderValue;

    @ApiModelProperty()
    private Integer isLock;

    @ApiModelProperty(value = "")
    private Integer isDel;

    @ApiModelProperty(value = "")
    private Date addDate;

    @ApiModelProperty(value = "")
    private Integer addUserId;

    @ApiModelProperty(value = "")
    private Date updateDate;

    @ApiModelProperty(value = "")
    private Integer updateUserId;

    @ApiModelProperty(value = "")
    private Date delDate;

    @ApiModelProperty(value = "")
    private Integer delUserId;

    @ApiModelProperty(value = "")
    private Integer isAudit;

    @ApiModelProperty()
    private Integer isActive;

    @ApiModelProperty(value = "app扫描登陆用，点击确认后修改为1，自动登陆后修改为0")
    private Integer isActivate;

    @ApiModelProperty(value = "验证的数字签名")
    private String sign;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "")
    private Integer keyIsActive;

    @ApiModelProperty(value = "当前积分")
    private BigDecimal curScore;

    @ApiModelProperty(value = "app设备")
    private String device;

    @ApiModelProperty(value = "app设备tokens")
    private String deviceTokens;

    @ApiModelProperty(value = "银行区域")
    private Integer bankPindex;

    @ApiModelProperty(value = "银行区域名称")
    private String bankPname;

    @ApiModelProperty(value = "支行名称")
    private String bankBranchName;

    @ApiModelProperty(value = "")
    private String bankName;

    @ApiModelProperty(value = "")
    private String bankAccount;

    @ApiModelProperty(value = "")
    private String bankAccountName;

    @ApiModelProperty(value = "")
    private String userLogo;

    @ApiModelProperty(value = "")
    private String appidWeixin;

    @ApiModelProperty(value = "")
    private String appidWeixinUnionid;

    @ApiModelProperty(value = "")
    private Integer appidWeixinIsFollow;

    @ApiModelProperty(value = "qq_open_id")
    private String appidQq;

    @ApiModelProperty(value = "weibo_open_id")
    private String appidWeibo;

    @ApiModelProperty(value = "所属地区")
    private Integer pIndex;

    @ApiModelProperty(value = "会员卡号")
    private String userNo;

    @ApiModelProperty(value = "是否商家")
    private Integer isEntp;

    @ApiModelProperty(value = "是否服务中心")
    private Integer isFuwu;

    @ApiModelProperty(value = "是否联盟主")
    private Integer isLianmeng;

    @ApiModelProperty(value = "")
    private Date isLianmengDate;

    @ApiModelProperty(value = "")
    private Date isLianmengCanceledDate;

    @ApiModelProperty(value = "")
    private Integer isDaqu;

    @ApiModelProperty(value = "余额")
    private BigDecimal biDianzi;

    @ApiModelProperty(value = "已返积分")
    private BigDecimal biXiaofei;

    @ApiModelProperty(value = "待返余额")
    private BigDecimal biDianziLock;

    @ApiModelProperty(value = "")
    private BigDecimal biDianziLost;

    @ApiModelProperty(value = "货款币")
    private BigDecimal biHuokuan;

    @ApiModelProperty(value = "待返货款币")
    private BigDecimal biHuokuanLock;

    @ApiModelProperty(value = "扶贫金")
    private BigDecimal biAid;

    @ApiModelProperty(value = "待返扶贫金")
    private BigDecimal biAidLock;

    @ApiModelProperty(value = "用户消费累计金额：为了系统自动赠送返现卡 用，赠送后，自动减去返现卡金额100元")
    private BigDecimal leijiMoneyUser;

    @ApiModelProperty(value = "商家累计金额：为了系统自动赠送返现卡 用，赠送后，自动减去返现卡金额100元")
    private BigDecimal leijiMoneyEntp;

    @ApiModelProperty(value = "累计推广金额")
    private BigDecimal leijiMoneyTuiguang;

    @ApiModelProperty(value = "推荐人（预留，旧的系统导入用）")
    private String ymid;

    @ApiModelProperty(value = "")
    private BigDecimal userScoreUnion;

    @ApiModelProperty(value = "")
    private String passwordPay;

    @ApiModelProperty(value = "是否设置安全问题")
    private Integer isSetSecurity;

    @ApiModelProperty(value = "推广锁定的电子币")
    private BigDecimal biDianziLockTuiguan;

    @ApiModelProperty(value = "")
    private Date scoreUpdateDate;

    @ApiModelProperty(value = "是否非法 0正常 1非法")
    private Integer isClosed;

    @ApiModelProperty(value = "")
    private String deviceToken;

    @ApiModelProperty(value = "")
    private String deviceTokenApp;

    @ApiModelProperty(value = "是否已实名认证")
    private Integer isRenzheng;

    @ApiModelProperty(value = "是否是城市经理 0 不是 1是")
    private Integer isCityManager;

    @ApiModelProperty(value = "")
    private Integer isStart;

    @ApiModelProperty(value = "是否已经修改密码 0 没有 1修改过了")
    private Integer isHasUpdatePass;

    @ApiModelProperty(value = "")
    private BigDecimal biFuxiao;

    @ApiModelProperty(value = "关联entpId 现在只给配送员用")
    private Integer linkEntpId;

    @ApiModelProperty(value = "身份证正面")
    private String imgIdCardZm;

    @ApiModelProperty(value = "身份证反面")
    private String imgIdCardFm;

    @ApiModelProperty(value = "所属村id")
    private Integer ownVillageId;

    @ApiModelProperty(value = "")
    private String ownVillageName;

    @ApiModelProperty(value = "是否是村合伙人 0：不是 1：是")
    private Integer isVillage;

    @ApiModelProperty(value = "是否是贫困户 0：不是 1：是")
    private Integer isPoor;

    @ApiModelProperty(value = "关联poor_id")
    private Integer poorId;

    @ApiModelProperty(value = "会员卡到期时间")
    private Date cardEndDate;

    @ApiModelProperty(value = "签名")
    private String autograph;

    @ApiModelProperty(value = "用户注册来源APP")
    private String appKey;

    @ApiModelProperty(value = "是否刷单用户 0：否，1：是")
    private Integer isShuadan;

    @ApiModelProperty(value = "设为主页")
    private String linkArea;

    @ApiModelProperty(value = "设置市的二级域名")
    private String domainSite;

    @ApiModelProperty(value = "超市用户id")
    private Integer markUserId;

    @ApiModelProperty(value = "所属法人ID")
    private Integer serviceCenterId;

    @ApiModelProperty(value = "所属法人名称")
    private String serviceCenterName;

    @ApiModelProperty(value = "是否收银员 0 不是 1是")
    private Integer isCashier;

    @ApiModelProperty(value = "个人最大领取券额度")
    private BigDecimal receiveAmountMax;

    @ApiModelProperty(value = "个人领取基础额度")
    private BigDecimal receiveAmountBase;

    @ApiModelProperty(value = "个人领取扩展额度")
    private BigDecimal receiveAmountExtend;

    @ApiModelProperty(value = "注册来源：1微信，2API，3APP，4地推二维码 9其他")
    private Integer fromType;

    @ApiModelProperty(value = "注册来源关联ID")
    private Integer fromId;

    @ApiModelProperty(value = "实名认证时间")
    private Date renZhengDate;

    @ApiModelProperty(value = "资源权限")
    private List<String> authorities;

}