CREATE TABLE categories (
   id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE subcategories (
   id INT AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NULL,
   category_id INT NULL,
   CONSTRAINT pk_subcategories PRIMARY KEY (id)
);

ALTER TABLE subcategories ADD CONSTRAINT FK_SUBCATEGORIES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);