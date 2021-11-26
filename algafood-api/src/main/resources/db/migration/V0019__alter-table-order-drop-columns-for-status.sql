ALTER TABLE orders DROP COLUMN status;
ALTER TABLE orders DROP COLUMN creation_date;
ALTER TABLE orders DROP COLUMN confirmation_date;
ALTER TABLE orders DROP COLUMN cancel_date;
ALTER TABLE orders DROP COLUMN delivery_date;
--ALTER TABLE orders RENAME COLUMN delivery_date TO example;