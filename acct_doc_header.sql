CREATE TABLE acct_doc_header(
id INT(11) PRIMARY KEY AUTO_INCREMENT ,
account_id INT(11),
document_number_norm VARCHAR(100),
company_code VARCHAR(50),
fiscal_year DATE,
branch VARCHAR(100),
customer_number_norm VARCHAR(50),
fk_customer_map_id INT(11),
document_date_norm DATE,
baseline_date_norm DATE,
due_date_norm DATE,
invoice_number_norm VARCHAR(50),
open_amount_norm DECIMAL(15,2),
payment_terms VARCHAR(100),
clearing_date_norm DATE,
isOpen TINYINT(1),
order_type VARCHAR(100),
order_date DATE,
business_area VARCHAR(100),
ship_date DATE,
job_id INT(11),
tax_amt DECIMAL(15,2),
current_dispute_amount DECIMAL(15,2),
ship_to VARCHAR(100),
document_id INT(11),
document_date DATE,
actual_open_amount DECIMAL(15,2),
due_date DATE,
invoice_age INT(11),
isValid_dispute TINYINT(1),
posting_key VARCHAR(100),
strategy_id INT(11),
currency CHAR(3),
debit_credit_indicator CHAR(1),
valid_open_amount DECIMAL(15,2),
customer_name VARCHAR(100),
retain_age_amount DECIMAL(15,2)
)


DESC acct_doc_header

SELECT* FROM acct_doc_header

ALTER TABLE acct_doc_header MODIFY fiscal_year INT(4)
ALTER TABLE acct_doc_header MODIFY debit_credit_indicator VARCHAR(3)

DELETE FROM acct_doc_header