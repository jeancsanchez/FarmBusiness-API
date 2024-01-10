CREATE TABLE products (
   id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   presentation VARCHAR(255) NULL,
   subcategory_id INT NULL,
   code VARCHAR(255) NULL,
   created_at datetime NULL,
   shelf_life datetime NULL,
   batch VARCHAR(255) NULL,
   unit_price DOUBLE NULL,
   total_items INT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_SUBCATEGORY FOREIGN KEY (subcategory_id) REFERENCES subcategories (id);

CREATE TABLE product_images (
   id INT AUTO_INCREMENT NOT NULL,
   image_url VARCHAR(255) NULL,
   product_id INT NULL,
   CONSTRAINT pk_product_images PRIMARY KEY (id)
);

ALTER TABLE product_images ADD CONSTRAINT FK_PRODUCT_IMAGES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);