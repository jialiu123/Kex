let formatDate = function (date, fmt){
  let o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'h+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'S': date.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
      if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
      }
  }
  return fmt
}
function padLeftZero(str) {
  return ('00' + str).substr(str.length);
}
 
//用于处理浮点数小数位数
let toFixed = function(input,num){
  return Number(input).toFixed(num);
};
 
//加密电弧号码  185****2563
let privateMobile = function(mobile){
  if (mobile) {
    return mobile.replace(/(\d{3})\d{3,4}(\d{3,4})/, '$1****$2');
  }
};
 
export default {
  formatDate,
  toFixed,
  privateMobile
}