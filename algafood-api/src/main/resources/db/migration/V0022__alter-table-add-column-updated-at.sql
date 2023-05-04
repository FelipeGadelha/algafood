ALTER TABLE payment_method ADD COLUMN updated_at TIMESTAMP NULL;
UPDATE payment_method SET updated_at = CURRENT_TIMESTAMP;
ALTER TABLE payment_method ALTER COLUMN updated_at SET DATA TYPE TIMESTAMP;
ALTER TABLE payment_method ALTER COLUMN updated_at SET NOT NULL;

