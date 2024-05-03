CREATE TABLE banners (
   id INT AUTO_INCREMENT NOT NULL,
   image VARCHAR(255) NULL,
   route VARCHAR(255) NULL,
   deeplink VARCHAR(255) NULL,
   CONSTRAINT pk_banners PRIMARY KEY (id)
);

CREATE TABLE offers (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   type VARCHAR(255) NULL,
   ios_redirect VARCHAR(255) NULL,
   android_redirect VARCHAR(255) NULL,
   CONSTRAINT pk_offers PRIMARY KEY (id)
);

CREATE TABLE offers_product (
  id INT AUTO_INCREMENT NOT NULL,
   product_id INT NULL,
   price_in_total DOUBLE NULL,
   price_in_cash DOUBLE NULL,
   price_on_time VARCHAR(255) NULL,
   discount DOUBLE NULL,
   offers_product_id INT NULL,
   CONSTRAINT pk_offers_product PRIMARY KEY (id)
);

ALTER TABLE offers_product ADD CONSTRAINT FK_OFFERS_PRODUCT_ON_OFFERS_PRODUCT FOREIGN KEY (offers_product_id) REFERENCES offers (id);
ALTER TABLE offers_product ADD CONSTRAINT FK_OFFERS_PRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);


CREATE TABLE news (
  id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   image VARCHAR(255) NULL,
   offer_id INT NULL,
   CONSTRAINT pk_news PRIMARY KEY (id)
);

ALTER TABLE news ADD CONSTRAINT FK_NEWS_ON_OFFER FOREIGN KEY (offer_id) REFERENCES offers (id);