create table if not exists products(
    id bigint unsigned not null primary key auto_increment comment "商品ID",
    title varchar(100) not null unique comment "商品名",
    description varchar(500) not null comment "商品説明",
    price int unsigned not null comment "商品価格",
    image_path text comment "商品画像",
    created_at datetime not null default current_timestamp comment "作成日時",
    updated_at datetime not null default current_timestamp on update current_timestamp comment "更新日時",
    version int not null comment "バージョン"
)
default charset = utf8mb4,
    engine innodb,
    comment "商品テーブル";