CREATE DATABASE IF NOT EXISTS bigcam DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use bigcam;

create table IF NOT EXISTS qh_user(
 user_id int(10) not null primary key auto_increment,
 email varchar(256),
 nickname varchar(256),
 username varchar(256),
 password varchar(32),
 profile_photo varchar(64),
 phone_num varchar(16),
 age int(3),
 gender varchar(8),
 birth_date datetime,
 pace_num int(10),
 journey int(10),
 register_date datetime,
 signature_text varchar(256),
 country_id int(4),
 province_id int(4),
 city_id int(4),
 certificated boolean,
 access_token varchar(256),
 open_id varchar(256),
 auth_type varchar(64)
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_signature(
 signature_id int(10) not null primary key auto_increment,
 signature_text varchar(256),
 user_id int(10),
 sign_date datetime
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_video(
 video_id int(10) not null primary key auto_increment,
 video_title varchar(512),
 user_id int(10),
 channel_id int(10),
 uuid VARCHAR(64),
 video_cover varchar(64),
 video_url varchar(512),
 video_desc varchar(512),
 video_duration int(6),
 like_times int(6),
 play_times int(6),
 comment_times int(6),
 share_times int(6),
 store_times int(6),
 upload_time datetime

--  video_content varchar(64),
--  take_time datetime,
--  location_id int(10),
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_channel(
 channel_id int(10) not null primary key auto_increment,
 channel_text varchar(64)
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_user_like_video(
 user_id int(10),
 video_id int(10),
 like_time datetime
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_user_play_video(
 user_id int(10),
 video_id int(10),
 play_time datetime
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_user_suggestion(
 suggestion_id int(10) not null primary key auto_increment,
 user_id int(10),
 user_email varchar(512),
 user_suggestion text,
 suggest_time datetime
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_comment(
 comment_id int(10) not null primary key auto_increment,
 comment_text varchar(1024),
 user_id int(10),
 video_id int(10),
 video_user_id int(10),
 replied_user_id int(10),
 praise_times int(10),
 comment_time datetime
)DEFAULT CHARACTER SET = utf8;

create table IF NOT EXISTS qh_user_praise_comment(
 user_id int(10),
 comment_id int(10),
 praise_time datetime
)DEFAULT CHARACTER SET = utf8;
