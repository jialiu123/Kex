<template>
  <div
    style="background-image:url('https://oss.stmbuy.com/static/upload/bannerpic/5b3c7dddb6278.jpg');background-repeat: no-repeat;"
  >
    <header-bar></header-bar>
    <div class="container w-response">
      <div class="nav">
        <div class="main-nav">
          <div class="appid dropmenu hover">
            <dl class="drop-menu">
              <dd data-gamename="dota2" data-appid="570" class>
                <a href="/dota2">
                  <i class="icon icon-dota2-sm"></i>
                  <span>DOTA2</span>
                </a>
              </dd>
              <dd data-gamename="csgo" data-appid="730" class="selected">
                <a href="/csgo">
                  <i class="icon icon-csgo-sm"></i>
                  <span>CS:GO</span>
                </a>
              </dd>
              <dd data-gamename="h1z1" data-appid="433850" class>
                <a href="/h1z1">
                  <i class="icon icon-h1z1-sm"></i>
                  <span>H1Z1</span>
                </a>
              </dd>
              <dd data-gamename="pubg" data-appid="578080" class>
                <a href="/pubg">
                  <i class="icon icon-pubg-sm"></i>
                  <span>绝地求生</span>
                </a>
              </dd>
            </dl>
            <div class="drop-title">
              <i class="icon icon-csgo-sm"></i>
              <input
                type="text"
                placeholder="请选游戏"
                value="CS:GO"
                readonly="readonly"
                unselectable="on"
              >
              <i class="edge"></i>
            </div>
          </div>
          <a href="/csgo">全部饰品</a>
          
          <a href="/csgo/buqiang">步枪</a>
          <a href="/csgo/ak-47">AK-47</a>
          
          <a href="/csgo/item-9896126477cce64a">AK-47 | 血腥运动 (略有磨损)</a>
          
          <span class="filter-tags"></span>
        </div>
      </div>
      <div class="main-content">
        <!--商品内容-->
        <div class="goods-detail">
          <div class="goods-img" data-3dlink>
            <img
              src="https://oss.stmbuy.com/static/image/game/730/9896126477cce64a.png"
              alt="AK-47 | 血腥运动 (略有磨损)AK-47 | Bloodsport (Minimal Wear)"
            >
            <button class="relation goods-hover"></button>
          </div>
          <div class="goods-info">
            <h1 class="goods-name">AK-47 | 血腥运动 (略有磨损)</h1>
            <p class="special-tag">
              <span style="background: #eb4b4b">隐秘</span>
              <span class="WearCategory1" style="font-size: .15rem;">( 略有磨损 )</span>
            </p>
            <p class="goods-price">
              最近成交价：
              <strong>¥
                <span>288</span>.88
              </strong>
              参考价：
              <strong class="market-price">¥
                <span>320</span>.23
              </strong>
            </p>
            <div class="goods-count">
              <p class="now-count">
                <span>当前在售</span>
                <span>
                  <strong>3</strong> 件
                </span>
              </p>
              <p class="total-sell">累计出售：
                <strong>5</strong> 件
              </p>
            </div>
            <div class="controls">
              <button class="buy-bulk">批量购买</button>
              <button class="sell-now">我要出售</button>
              <button class="buy-seek">我要求购</button>
            </div>
            <button class="collect">
              <span>收藏</span>
              <i class="icon icon-collect"></i>
            </button>
          </div>
        </div>
        <!-- tab -->
        <ul class="switch-head">
          <li class="on">
            <p>
              <a href="/csgo/item-9896126477cce64a" target="_self">当前在售(3)</a>
            </p>
          </li>
          <li class>
            <p>
              <a href="/csgo/item-9896126477cce64a/askbuy" target="_self">求购饰品(1)</a>
            </p>
          </li>
          <li class>
            <p>
              <a href="/csgo/item-9896126477cce64a/deal" target="_self">历史成交</a>
            </p>
          </li>
          <li class>
            <p>
              <a href="/csgo/item-9896126477cce64a/price" target="_self">价格趋势</a>
            </p>
          </li>
          <li class="controls fr">
            <button class="sell-now">我要出售</button>
            <button class="buy-seek">我要求购</button>
          </li>
        </ul>
        <div class="tab-wrap">
          <div class="tab-wrap">
            <table class="tab-item" cellpadding="0" cellspacing="0">
              <thead>
                <tr class="filter-nav">
                  <th class="goods">饰品</th>
                  <th class="seller">卖家</th>
                  <th class="price">单价
                    <i class="icon icon-increase"></i>
                  </th>
                  <th class="amount">数量</th>
                  <th class="mode">
                    <div class="dropmenu filter-kv">
                      <div class="drop-title">
                        <i class="icon icon-sale-mode"></i>
                        <div class="mode-pop hover-pop top">
                          <div>
                            <p>
                              <strong>寄售模式</strong>
                            </p>
                            <p>饰品已寄存在STMBUY平台，下单后系统自动发货</p>
                            <br>
                            <p>
                              <strong>自售模式</strong>
                            </p>
                            <p>饰品在卖家仓库中，下单由卖家直接发货到买家库存中</p>
                          </div>
                        </div>
                        <input type="text" placeholder="is_self_trade" value="发货" readonly>
                        <i class="icon icon-sale-mode-select"></i>
                      </div>
                      <dl class="drop-menu">
                        <dd>
                          <span>全部</span>
                        </dd>
                        <dd data-value="0">
                          <span>寄售</span>
                        </dd>
                        <dd data-value="1">
                          <span>自售</span>
                        </dd>
                      </dl>
                    </div>
                  </th>
                  <th class="putout-state">取回时间
                    <i class="icon icon-increase"></i>
                  </th>
                  <th class="controls">操作</th>
                </tr>
              </thead>
              <tbody class="js-maincontent">
                <tr>
                  <td class="goods">
                    <div class="goods-img goods-hover">
                      <img
                        src="https://oss.stmbuy.com//static/image/game/730/9896126477cce64a.png"
                        onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                      >
                      <div class="hover-pop right">
                        <div>
                          <p class="tit">AK-47 | 血腥运动 (略有磨损)</p>
                          <p class="sec-tit">
                            <i class="icon icon-csgo-sm"></i>
                            <span>Counter-Strike: Global Offensive</span>
                          </p>
                          <p class="price">参考价：¥
                            <span>320.23</span>
                          </p>

                          <p class="describe" style="color: #undefined">外观： 略有磨损</p>

                          <p class="describe" style="color: #undefined"></p>

                          <p class="describe" style="color: #undefined">
                            强大又可靠，AK-47 是世上最流行的突击步枪之—。近距离内控制好的短点射极为致命。
                            &nbsp;这把武器在红黑两色的水文图案上覆盖了白色徽标贴花。
                            招募副驾驶
                          </p>

                          <p class="describe" style="color: #undefined"></p>

                          <p class="describe" style="color: #9da1a9">光谱收藏品</p>
                          <p class="describe" style="color: #undefined">&nbsp;</p>
                          <p class="describe" style="color: #undefined"></p>
                        </div>
                      </div>
                    </div>
                    <div class="goods-info">
                      <p>AK-47 | 血腥运动 (略有磨损)</p>
                      <p>
                        <a
                          class="watch-ingame"
                          href="steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S76561198411408311A15635438748D63690740685778749"
                        >游戏中检测</a>
                      </p>
                    </div>
                  </td>
                  <td class="seller">
                    <div>
                      <a href="/user/1dvy777fcrid" target="_blank" title="MIRACLE KILL">
                        <img
                          src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/8d/8de43849e8145af130e7d4ab91df9f09ab2791fd_full.jpg"
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        <div class="seller-info">
                          <span>MIRACLE KILL</span>
                          <div class="seller-pop hover-pop right">
                            <div>发货成功率：
                              <span>
                                <strong>100</strong>%
                              </span>
                            </div>
                            <div>平均发货时间：
                              <span>
                                <strong>30</strong>分钟
                              </span>
                            </div>
                          </div>
                        </div>
                      </a>
                    </div>
                  </td>
                  <td class="price">
                    <div>￥
                      <span>269</span>.90
                    </div>
                  </td>
                  <td class="amount">
                    <div>1</div>
                  </td>
                  <td class="mode">寄售</td>
                  <td class="putout-state">可立即取回</td>
                  <td class="controls">
                    <button class="buy-now">立即购买</button>
                  </td>
                </tr>
                <tr>
                  <td class="goods">
                    <div class="goods-img goods-hover">
                      <img
                        src="https://oss.stmbuy.com//static/image/game/730/9896126477cce64a.png"
                        onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                      >
                      <div class="hover-pop right">
                        <div>
                          <p class="tit">AK-47 | 血腥运动 (略有磨损)</p>
                          <p class="sec-tit">
                            <i class="icon icon-csgo-sm"></i>
                            <span>Counter-Strike: Global Offensive</span>
                          </p>
                          <p class="price">参考价：¥
                            <span>320.23</span>
                          </p>
                          <p class="describe" style="color: #undefined">外观： 略有磨损</p>
                          <p class="describe" style="color: #undefined"></p>
                          <p
                            class="describe"
                            style="color: #undefined"
                          >强大又可靠，AK-47 是世上最流行的突击步枪之—。近距离内控制好的短点射极为致命。 &nbsp;</p>
                          <p class="describe" style="color: #undefined"></p>
                          <p class="describe" style="color: #9da1a9">光谱收藏品</p>
                          <p class="describe" style="color: #undefined">&nbsp;</p>
                          <p class="describe" style="color: #undefined"></p>
                          <ul class="specials">
                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/kawaiikiller.35ec3d31236c60cd7f644165ff4ab0247ff63443.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>印花: 卡哇伊杀手反恐精英</p>
                                <p></p>
                              </div>
                            </li>
                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/pinups_capsule/merietta.ad86245da06bf53567e59dd2d5e7e73121c6f27d.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>玛丽埃塔</p>
                                <p></p>
                              </div>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                    <div class="goods-info">
                      <p>AK-47 | 血腥运动 (略有磨损)</p>
                      <p>
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/kawaiikiller.35ec3d31236c60cd7f644165ff4ab0247ff63443.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/pinups_capsule/merietta.ad86245da06bf53567e59dd2d5e7e73121c6f27d.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <a
                          class="watch-ingame"
                          href="steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S76561198801651209A15174107716D336265980510741851"
                        >游戏中检测</a>
                      </p>
                    </div>
                  </td>
                  <td class="seller">
                    <div>
                      <a href="/user/1lq32z9mkaoa" target="_blank" title="Beijinger in KY">
                        <img
                          src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/3b/3b1b0a9a409552205907a7942fd007f302a1a1a8_full.jpg"
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        <div class="seller-info">
                          <span>Beijinger in KY</span>
                          <div class="seller-pop hover-pop right">
                            <div>发货成功率：
                              <span>
                                <strong>0</strong>%
                              </span>
                            </div>
                            <div>平均发货时间：
                              <span>
                                <strong>0</strong>分钟
                              </span>
                            </div>
                          </div>
                        </div>
                      </a>
                    </div>
                  </td>
                  <td class="price">
                    <div>￥
                      <span>270</span>.00
                    </div>
                  </td>
                  <td class="amount">
                    <div>1</div>
                  </td>

                  <td class="mode" style="color: #549ecb;">自售</td>

                  <td class="putout-state">可立即取回</td>
                  <td class="controls">
                    <button class="buy-now">立即购买</button>
                  </td>
                </tr>

                <tr>
                  <td class="goods">
                    <div class="goods-img goods-hover">
                      <img
                        src="https://oss.stmbuy.com//static/image/game/730/9896126477cce64a.png"
                        onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                      >

                      <div class="hover-pop right">
                        <div>
                          <p class="tit">AK-47 | 血腥运动 (略有磨损)</p>
                          <p class="sec-tit">
                            <i class="icon icon-csgo-sm"></i>
                            <span>Counter-Strike: Global Offensive</span>
                          </p>
                          <p class="price">参考价：¥
                            <span>320.23</span>
                          </p>

                          <p class="describe" style="color: #undefined">外观： 略有磨损</p>

                          <p class="describe" style="color: #undefined"></p>

                          <p class="describe" style="color: #undefined">
                            强大又可靠，AK-47 是世上最流行的突击步枪之—。近距离内控制好的短点射极为致命。
                            &nbsp;这把武器在红黑两色的水文图案上覆盖了白色徽标贴花。
                            招募副驾驶
                          </p>

                          <p class="describe" style="color: #undefined"></p>

                          <p class="describe" style="color: #9da1a9">光谱收藏品</p>

                          <p class="describe" style="color: #undefined">&nbsp;</p>

                          <p class="describe" style="color: #undefined"></p>

                          <ul class="specials">
                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>印花: 招财猫（闪亮）</p>
                                <p></p>
                              </div>
                            </li>

                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>招财猫（闪亮）</p>
                                <p></p>
                              </div>
                            </li>

                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>招财猫（闪亮）</p>
                                <p></p>
                              </div>
                            </li>

                            <li>
                              <img
                                src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                                title
                              >
                              <div style="vertical-align: middle;">
                                <p>招财猫（闪亮）</p>
                                <p></p>
                              </div>
                            </li>
                          </ul>
                        </div>
                      </div>
                    </div>
                    <div class="goods-info">
                      <p>AK-47 | 血腥运动 (略有磨损)</p>
                      <p>
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <img
                          src="https://steamcdn-a.akamaihd.net/apps/730/icons/econ/stickers/community02/lucky_cat_foil.87d67d57309652b0e238dbaf53af5c764445fe44.png"
                          width="30px"
                          title
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        
                        <a
                          class="watch-ingame"
                          href="steam://rungame/730/76561202255233023/+csgo_econ_action_preview%20S76561198107761077A15404902424D433135334669438865"
                        >游戏中检测</a>
                      </p>
                    </div>
                  </td>
                  <td class="seller">
                    <div>
                      <a href="/user/20000002x" target="_blank" title="♚Doom">
                        <img
                          src="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/b5/b555995fcbc77b18569962df0c86a55f0a662cb1_full.jpg"
                          onerror="this.src='https://web.stmbuy.com/static/img/imgload-error.png'"
                        >
                        <div class="seller-info">
                          <span>♚Doom</span>
                          <div class="seller-pop hover-pop right">
                            <div>发货成功率：
                              <span>
                                <strong>82</strong>%
                              </span>
                            </div>
                            <div>平均发货时间：
                              <span>
                                <strong>3</strong>分钟
                              </span>
                            </div>
                          </div>

                          <div class="tags">
                            <button class="explain">
                              <i class="icon icon-store-quick"></i>
                              <div class="hover-pop top">
                                <div class="display-line">闪电发货</div>
                              </div>
                            </button>
                          </div>
                        </div>
                      </a>
                    </div>
                  </td>
                  <td class="price">
                    <div>￥
                      <span>399</span>.99
                    </div>
                  </td>
                  <td class="amount">
                    <div>1</div>
                  </td>

                  <td class="mode" style="color: #549ecb;">自售</td>

                  <td class="putout-state">可立即取回</td>
                  <td class="controls">
                    <button class="buy-now">立即购买</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <aside-comm></aside-comm>
    </div>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import HeaderBar from "../components/header-bar";
import FooterBar from "../components/footer-bar";
import AsideComm from "./components/aside-comm.vue";
export default {
  components: {
    HeaderBar,
    FooterBar,
    AsideComm
  },
  data() {
    return {
      dataTwoList: ["1", "2"],
      dataThreeList: ["1", "2"]
    };
  },
  mounted: function() {},
  methods: {
    getData() {}
  }
};
</script>
<style>
@import "../../../assets/css/common.css";
@import "../../../assets/css/comm.css";
</style>
