drop table t_aws_asin_rank;
drop table t_aws_asin;

CREATE TABLE `t_aws_asin_rank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `asin` varchar(10) COMMENT '商品码ASIN',
  `rank_name` varchar(64)  COMMENT '类别名称',
  `rank_score` varchar(20)  COMMENT '类别排名',
  `type` tinyint(1) default 0 COMMENT '是否大类排名：0:false,1:true',
  `grab_day`  date COMMENT '爬取日期',
  `grab_hour`  tinyint(2) COMMENT '爬取小时',
  PRIMARY KEY (`id`),
  key `k_grap` (`grab_day`,`grab_hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='亚马逊商品排名表';

CREATE TABLE `t_aws_asin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `asin` varchar(10) COMMENT '商品码ASIN',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='待处理的商品配置表';

INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B075GKB2V1');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B00Y8AHZYC');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B00W3I82L6');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B00YMI0KEM');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B074BSWHX8');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B00LN0RPA4');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B00LN0RRZW');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B06XW84ZFT');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B0078ZTWP4');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B0196IJF70');
INSERT INTO `hu`.`t_aws_asin`(`asin`) VALUES ('B07P6V3VPK');
