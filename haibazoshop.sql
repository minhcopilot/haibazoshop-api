-- haibazoshop.categories definition

CREATE TABLE "categories" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "name" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id")
);


-- haibazoshop.colors definition

CREATE TABLE "colors" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "hex_code" varchar(255) DEFAULT NULL,
  "name" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id")
);


-- haibazoshop.sizes definition

CREATE TABLE "sizes" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "name" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id")
);


-- haibazoshop.styles definition

CREATE TABLE "styles" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "name" varchar(255) DEFAULT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY "UKt66pj9594i558j902po967bfq" ("name")
);


-- haibazoshop.products definition

CREATE TABLE "products" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "description" varchar(255) DEFAULT NULL,
  "name" varchar(255) DEFAULT NULL,
  "price" decimal(38,2) DEFAULT NULL,
  "views" int NOT NULL,
  "category_id" bigint DEFAULT NULL,
  "is_deleted" bit(1) NOT NULL,
  "style_id" bigint DEFAULT NULL,
  "original_price" decimal(38,2) DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY "FKog2rp4qthbtt2lfyhfo32lsw9" ("category_id"),
  KEY "FK3bdhf42l8os4j6p1jid39fvk9" ("style_id"),
  CONSTRAINT "FK3bdhf42l8os4j6p1jid39fvk9" FOREIGN KEY ("style_id") REFERENCES "styles" ("id"),
  CONSTRAINT "FKog2rp4qthbtt2lfyhfo32lsw9" FOREIGN KEY ("category_id") REFERENCES "categories" ("id")
);


-- haibazoshop.reviews definition

CREATE TABLE "reviews" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "comment" varchar(255) DEFAULT NULL,
  "date_posted" datetime(6) DEFAULT NULL,
  "rating" int NOT NULL,
  "product_id" bigint DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY "FKpl51cejpw4gy5swfar8br9ngi" ("product_id"),
  CONSTRAINT "FKpl51cejpw4gy5swfar8br9ngi" FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);


-- haibazoshop.images definition

CREATE TABLE "images" (
  "id" bigint NOT NULL AUTO_INCREMENT,
  "is_primary" bit(1) NOT NULL,
  "url" varchar(255) DEFAULT NULL,
  "product_id" bigint DEFAULT NULL,
  PRIMARY KEY ("id"),
  KEY "FKghwsjbjo7mg3iufxruvq6iu3q" ("product_id"),
  CONSTRAINT "FKghwsjbjo7mg3iufxruvq6iu3q" FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);


-- haibazoshop.product_colors definition

CREATE TABLE "product_colors" (
  "product_id" bigint NOT NULL,
  "color_id" bigint NOT NULL,
  PRIMARY KEY ("product_id","color_id"),
  KEY "FKb9e4okm5xhksf4up2ltc8gxv0" ("color_id"),
  CONSTRAINT "FKb9e4okm5xhksf4up2ltc8gxv0" FOREIGN KEY ("color_id") REFERENCES "colors" ("id"),
  CONSTRAINT "FKqhu7cqni31911lmvx4fqmiw65" FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);


-- haibazoshop.product_sizes definition

CREATE TABLE "product_sizes" (
  "product_id" bigint NOT NULL,
  "size_id" bigint NOT NULL,
  PRIMARY KEY ("product_id","size_id"),
  KEY "FK3bqabm2nc8yyl9to7fo8trak4" ("size_id"),
  CONSTRAINT "FK3bqabm2nc8yyl9to7fo8trak4" FOREIGN KEY ("size_id") REFERENCES "sizes" ("id"),
  CONSTRAINT "FK4isa0j51hpdn7cx04m831jic4" FOREIGN KEY ("product_id") REFERENCES "products" ("id")
);