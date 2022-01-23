DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS accounts_roles;
DROP TABLE IF EXISTS roles_privileges;
DROP TABLE IF EXISTS expense_categories;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS privileges;
DROP TABLE IF EXISTS roles;


CREATE TABLE expense_categories
(
	expense_category_id BIGINT AUTO_INCREMENT,
	name                VARCHAR2(255) NOT NULL,
	CONSTRAINT pk_expense_categories PRIMARY KEY (expense_category_id),
	CONSTRAINT unique_expense_category_name UNIQUE (name)
);

CREATE TABLE accounts
(
	account_id BIGINT AUTO_INCREMENT,
	email      VARCHAR(255) NOT NULL,
	password   VARCHAR(255) NOT NULL,
	CONSTRAINT pk_accounts PRIMARY KEY (account_id),
	CONSTRAINT unique_account_email UNIQUE (email)
);

CREATE TABLE expenses
(
	expense_id          BIGINT AUTO_INCREMENT,
	date                TIMESTAMP,
	price               DECIMAL(19, 2),
	expense_category_id BIGINT NOT NULL,
	account_id          BIGINT NOT NULL,
	CONSTRAINT pk_expenses PRIMARY KEY (expense_id),
	CONSTRAINT fk_expenses_expense_category_category_id FOREIGN KEY (expense_category_id) REFERENCES expense_categories (expense_category_id),
	CONSTRAINT fk_expenses_account_account_id FOREIGN KEY (account_id) REFERENCES accounts (account_id)
);

CREATE TABLE privileges
(
	privilege_id BIGINT AUTO_INCREMENT,
	name         VARCHAR(255) NOT NULL,
	CONSTRAINT pk_privileges PRIMARY KEY (privilege_id),
	CONSTRAINT unique_privilege_name UNIQUE (name)
);

CREATE TABLE roles
(
	role_id BIGINT AUTO_INCREMENT,
	name    VARCHAR(255) NOT NULL,
	CONSTRAINT pk_roles PRIMARY KEY (role_id),
	CONSTRAINT unique_role_name UNIQUE (name)
);

CREATE TABLE roles_privileges
(
	role_id      BIGINT NOT NULL,
	privilege_id BIGINT NOT NULL,
	CONSTRAINT pk_roles_privileges PRIMARY KEY (role_id, privilege_id),
	CONSTRAINT fk_roles_privileges_privilege FOREIGN KEY (privilege_id) REFERENCES privileges (privilege_id),
	CONSTRAINT fk_roles_privileges_role FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE TABLE accounts_roles
(
	role_id    BIGINT NOT NULL,
	account_id BIGINT NOT NULL,
	CONSTRAINT pk_accounts_roles PRIMARY KEY (role_id, account_id),
	CONSTRAINT fk_accounts_roles_account FOREIGN KEY (account_id) REFERENCES accounts (account_id),
	CONSTRAINT fk_accounts_roles_role FOREIGN KEY (role_id) REFERENCES roles (role_id)
);