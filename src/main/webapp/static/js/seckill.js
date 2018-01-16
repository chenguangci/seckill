//主要交互的js代码
//JavaScript模块化
var seckill = {
    //封装ajax秒杀相关的url
    URL: {
        now:function () {
            return '/seckill/time/now';
        }
    },
    //验证手机号
    validatePhone:function (phone) {
      return !!(phone && phone.length === 11 && !isNaN(phone));
    },

    handleSecKill : function () {
        //执行秒杀逻辑
    },

    countdown: function (seckillId, time, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        //事件判断
        if (time > endTime) {
            //秒杀结束
            seckillBox.html('秒杀结束！');
        } else if (time < startTime) {
            //秒杀未开始，计时
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //执行秒杀，获取秒杀地址
                seckill.handleSecKill();
            });
        } else {
            seckill.handleSecKill();
        }
    },

    detail: {
        init:function (param) {
            //取值
            var phone = $.cookie('killPhone');
            //判断手机号是否合法
            if (!seckill.validatePhone(phone)) {
                //显示弹出层
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//禁止关闭
                    keyboard:false //禁止键盘事件
                });
                //点击事件
                $('#killPhoneBtn').click(function () {
                    //获取输入框中的值
                    var killPhone = $('#killPhoneKey').val();
                    if (seckill.validatePhone(killPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', killPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        //手机号错误，显示错误信息
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300)
                    }
                })
            }
            //已经登陆，计时
            var seckillId = param['seckillId'];
            var startTime = param['startTime'];
            var endTime = param['endTime'];
            $.get(seckill.URL.now(), {} ,function (result) {
               if(result && result['success']) {
                   var time = result['data'];
                   seckill.countdown(seckillId, time, startTime, endTime);
               } else {
                   console.log("result:"+result);
               }
            });
        }
    }
}