create database animal_nest;
use animal_nest;


#动物信息表
drop table if exists animal_info;

create table animal_info(
	id int primary key auto_increment,
	name varchar(20),
	color varchar(20),
	gender char(5),
	health varchar(10),
	habitat varchar(10),
	temperament varchar(100),
	habit varchar(100),
	picture nvarchar(100)
);


update animal_info set gender = '男',picture='https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png' where id=1;

insert into animal_info values (null, '小胡子', '奶牛', '女', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/7f94aacbe5484f0f.png');
insert into animal_info values (null, '小狸', '白色加狸花', '女', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/37ec132ef9505ae2.png');
insert into animal_info values (null, '壮壮', '黄白色', '未知', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/b67cfe90cf443e6e.png');
insert into animal_info values (null, '胖狸', '狸花', '男', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png');
insert into animal_info values (null, '奶牛', '奶牛', '女', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/7eec952bf41ac74d.png');
insert into animal_info values (null, '墨镜', '奶牛', '男', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/ac088df1c029aec0.png');
insert into animal_info values (null, '敦敦', '橘猫', '男', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/e19fe01cd0b64692.png');
insert into animal_info values (null, '大眼睛', '橘猫', '未知', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/97bd91fe17cdcaf9.png');
insert into animal_info values (null, '栗子', '橘猫', '男', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/6745afa61fec6fa1.png');
insert into animal_info values (null, '鸡腿', '橘猫', '未知', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/2a87efed3ed36c47.png');
insert into animal_info values (null, '假面', '三花', '女', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/61d015184579bb8e.png');
insert into animal_info values (null, '大白', '纯白', '未知', '健康', '未知', 'TA的性格', 'TA的爱好', 'https://s3.bmp.ovh/imgs/2023/02/25/41e66fae4d886a16.png');


select * from animal_info;


#后台操作用户表
drop table if exists admin;

create table admin(
	id int primary key auto_increment,
	phone varchar(20),
	name varchar(10),
	password varchar(20)
);

insert into admin values (null, 15269553372, '咕叽', '123');
insert into admin values (null, 123, '张三', '123456');

#用户表
drop table if exists user;

create table user(
	id int primary key auto_increment,
	phone varchar(20),
	name varchar(20),
	profile nvarchar(100)
);

alter table user add open_id varchar(255) unique;

select * from user limit 1, 5;


update user set name='史杰佳', profile='https://s3.bmp.ovh/imgs/2023/02/28/f927e64600fc6f5a.jpg' where id = 1;
update user set name='史杰佳', profile=
'https://s3.bmp.ovh/imgs/2023/02/28/f927e64600fc6f5a.jpg' where id = 2;
update user set open_id="ofscN5T8qWKTW7EGDM6Bn4NM1J6Y" where id = 2;


insert into user values (null, 123, '张三', '123');
insert into user values (null, 12345, '李四', '123');
delete from user where id = 2;

select * from user;
update user set profile = 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png' where id = 1;

 
#动态表
drop table if exists posting;

create table posting(
	id int primary key auto_increment,
	picture nvarchar(100),
	words varchar(500),
	animal_id int,
	user_id int,
	likes_number int,
	comment_number int
);

#添加索引
show indexes from posting;
drop index index_animal_id on posting;
drop index index_user_id on posting;
ALTER TABLE posting ADD INDEX index_animal_id (animal_id);
ALTER TABLE posting ADD INDEX index_user_id (user_id);

select * from posting;

insert into posting values (null, '123', 'heihei', 1, 1, 10, 12);
insert into posting values (null, '123', 'wuwuwu', 1, 1, 10, 12);



update posting set picture='https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png' , words='动物小窝，愿不再流浪' where id=1;

insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/37ec132ef9505ae2.png', '它们说，不要问我们为什么而活，你因活着才能思考为何活。你的生命自出现便是被选择。 但经历整个人世冷暖，彼此命运交叠成无数曲折，它们说，或许生的序幕由他人打开，但舞台上的热泪盈眶却必须由自己奏响。要永远炙烈燃放如同烟火，即便幻灭而逝，也不要放弃能璀璨永存的那一刻。', 6, 1, 10, 12);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png', '冬日寒冷，天寒地冻的感觉，关爱流浪猫，给它们一个温暖的家，给他们送去温暖的呵护，让他们感受到我们人类所有的温柔！', 8, 1, 1, 0);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/ac088df1c029aec0.png', '阳光午后，做一只慵懒的猫吧。让时间悠悠逝去，享受猫咪一样的慢生活。', 10, 1, 8, 2);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/e19fe01cd0b64692.png', '亲爱的朋友们，让我们行动起来，谁说人世无情，你的仁爱之心，你的点滴之恩，就有可能拯救一个小小的生命，谁说命运不公，你的无名关怀，你的善意之举，就是它最大的幸运。众人拾柴火焰高，能力不分大小。捐款不分多少，善举不分先后，贵在有份爱心。滴水汇成大江，碎石堆成海岛。哪怕就是一分钱，只要人人献出一份爱，它就多了一份生存的希望。', 11, 1, 22, 3);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/97bd91fe17cdcaf9.png', '对于整个社会而言，他们和它们都是弱者。尤其在以人为本的社会中，流浪猫狗一直都很卑微很无助很胆怯的挣扎着、生存着，它们去垃圾中找吃的，去下水道喝脏水，在乱草丛中睡觉，穿俊在冰冷坚硬的马路上，还随时害怕着被打杀。它们生命的消逝，对于社会而言简直微不足道，但是，朋友，我相信您也是爱狗一员，在碰到它们无助地在路边找吃的时候，希望您能伸出爱心之手，请您给它们喂点吃的，让它们可以有幸吃到一顿饱饭。我们希望不再有狗狗的高低贵贱之分，不再有中华田园犬被投毒被杀的事情发生，希望能有更多的人去关注流浪猫狗，希望每一个生命都能被得到尊重和爱护。', 12, 1, 32, 5);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png', '随着城市流浪猫数量的增加，大众越发意识到救助流浪动物也是一种社会责任。在近期发起的流浪猫救助行动中，通过捐赠猫粮给流浪猫救助站、为个人提供公益猫粮、举办快闪展览等，鼓励更多年轻人深入了解科学救助，使城市居民与流浪动物和谐共生的理念深入人心。', 8, 1, 1, 2);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png', '作为城市中最常见的流浪动物之一，流浪猫的困境受到了众多爱心人士的救助和关爱，但其卫生、发情、伤人等情况也带来了相当多的争议，个别极端支持或极端反对流浪猫的言行也引发了社会各界人士的激烈讨论。', 8, 1, 9, 8);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png', 'TNR的全称：Trap Neuter Return，即抓捕、绝育、放归。在流浪猫泛滥的地方，TNR可以有效救助、控制流浪猫咪的繁殖数量，同时亦可对这些流浪猫进行身体检查。操作过程也比较简单便捷。', 8, 1, 10, 9);
insert into posting values (null, 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png', '重复抓捕，绝育会对猫咪本身造成了不小的伤害，也加大了TNR的成本。流浪猫被绝育后放归，恢复后，公猫用手摸可以识别出来，母猫却是摸不出来的，即使宠物医师也无法肉眼识别这只猫是否被绝育过。所以为了区分流浪猫是否被绝育过，综合考量下，会在猫咪的耳朵上剪掉一小部分作为标记。“公左母右”，便于识别以及二次抓捕。', 8, 1, 2, 1);

update posting set picture = 'https://s3.bmp.ovh/imgs/2023/02/25/7f4958446e5a661d.png'where id = 16;



#审核记录表
drop table if exists audit_record;

create table audit_record(
	id int primary key auto_increment,
	status int,
	posting_id int,
	words varchar(500)
);

insert into audit_record values (null, 1, 1, '还可以，审核通过');

select * from audit_record;


#评论表
drop table if exists comment;

create table comment(
	id int primary key auto_increment,
	user_id int,
	posting_id int,
	words varchar(500),
	likes_number int
);

#添加索引
drop index index_posting_id on comment;
show indexes from comment;
ALTER TABLE comment ADD INDEX index_posting_id(posting_id);

insert into comment values (null, 1, 1, '好有爱呀', 10);
insert into comment values (null, 1, 1, 'cute', 20);
select * from comment where posting_id = 1;




#用户关注动物中间表

drop table if exists favorite;

create table favorite(
	id int primary key auto_increment,
	user_id int,
	animal_id int
);

insert into favorite values(null, 1, 1);
insert into favorite values(null, 1, 5);

delete from favorite where animal_id = 10;


#帖子点赞记录表

drop table if exists posting_like;

create table posting_like(
	id int primary key auto_increment,
	user_id int,
	posting_id int
);

insert into posting_like values(null, 1, 6);
insert into posting_like values(null, 1, 7);
insert into posting_like values(null, 1, 8);
insert into posting_like values(null, 1, 10);
insert into posting_like values(null, 1, 11);


#反馈信息表

drop table if exists feedback;

create table feedback(
	id int primary key auto_increment,
	user_id int,
	msg varchar(500)
);

insert into feedback values(null, 1, "反馈内容1");
select * from feedback;







