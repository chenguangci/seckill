CREATE DATABASE seckill;
use seckill;
CREATE TABLE seckill
(
  seckill_id INT AUTO_INCREMENT COMMENT '商品id',
  seckill_name VARCHAR(50) NOT NULL ,
  number INT NOT NULL ,
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  begin_time TIMESTAMP NOT NULL COMMENT '开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '结束时间',
  PRIMARY KEY (seckill_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT '秒杀库存表';
-- 添加索引
ALTER TABLE seckill ADD KEY idx_create_time(create_time),ADD KEY idx_begin_time(begin_time);
-- 添加数据
INSERT INTO seckill
  (seckill_name,number,begin_time,end_time)
VALUES
  ('9999陈会长签名版坚果pro细红线特别版',1,'2018-01-09 00:00:00','2018-01-10 00:00:00'),
  ('4000秒杀iPhoneX',5,'2018-01-09 00:00:00','2018-01-15 00:00:00'),
  ('3000秒杀iPhone7',10,'2018-01-10 00:00:00','2018-01-14 00:00:00'),
  ('1899秒杀坚果pro2高配版',10,'2018-01-09 00:00:00','2018-01-15 00:00:00'),
  ('1299秒杀坚果pro',10,'2018-01-08 00:00:00','2018-01-12 00:00:00'),
  ('3199秒杀小米MIX',10,'2018-01-08 00:00:00','2018-01-12 00:00:00'),
  ('2000秒杀魅族pro7',10,'2018-01-08 00:00:00','2018-01-12 00:00:00'),
  ('699秒杀魅蓝6s',10,'2018-01-09 00:00:00','2018-01-15 00:00:00'),
  ('1499秒杀iPhone6',10,'2018-01-09 00:00:00','2018-01-15 00:00:00'),
  ('3199秒杀华为Mate10',10,'2018-01-10 00:00:00','2018-01-14 00:00:00'),
  ('699秒杀魅族6',10,'2018-01-09 00:00:00','2018-01-15 00:00:00'),
  ('1299秒杀小米5X',10,'2018-01-08 00:00:00','2018-01-12 00:00:00'),
  ('1999秒杀oppoR11',10,'2018-01-08 00:00:00','2018-01-12 00:00:00'),
  ('1999秒杀vivoX20',10,'2018-01-08 00:00:00','2018-01-12 00:00:00')
;


-- 秒杀成功明细表
USE seckill;
CREATE TABLE success_killed
(
  seckill_id INT NOT NULL COMMENT '商品id',
  user_phone BIGINT NOT NULL COMMENT '用户手机号',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '状态 -1：无效，0：成功，1：已付款，2：已发货',
  create_time TIMESTAMP NOT NULL,
  PRIMARY KEY(seckill_id,user_phone),
  KEY (create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '秒杀库存表';