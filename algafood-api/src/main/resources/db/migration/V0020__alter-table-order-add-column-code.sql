ALTER TABLE orders ADD COLUMN code VARCHAR(36) NOT NULL;
ALTER TABLE orders ADD CONSTRAINT uk_orders_code UNIQUE (code);

--alter table tablename rename to oldtable;
--create table tablename (column defs go here);
--insert into tablename (col1, col2, col3) select col2, col1, col3 from oldtable;