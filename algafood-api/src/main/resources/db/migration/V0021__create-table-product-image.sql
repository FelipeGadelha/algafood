create table product_image (
    product_id INT8 NOT NULL,
    file_name varchar(150) NOT NULL,
    description varchar(150) NOT NULL,
    content_type varchar(80) NOT NULL,
    size INT NOT NULL,

    primary key (product_id),
    CONSTRAINT FK_product_image_product FOREIGN KEY (product_id) REFERENCES product (id)
);