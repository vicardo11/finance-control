DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS expense_categories;

CREATE TABLE expense_categories(
    expense_category_id   BIGINT AUTO_INCREMENT,
    name VARCHAR2(255) NOT NULL,
    CONSTRAINT pk_expense_categories PRIMARY KEY (expense_category_id),
    CONSTRAINT unique_expense_category_name UNIQUE (name)
);

CREATE TABLE expenses(
    expense_id          BIGINT AUTO_INCREMENT,
    date                TIMESTAMP,
    price               DECIMAL(19, 2),
    expense_category_id BIGINT NOT NULL,
    CONSTRAINT pk_expenses PRIMARY KEY (expense_id),
    CONSTRAINT fk_expenses_expense_category_category_id FOREIGN KEY (expense_category_id) REFERENCES expense_categories (expense_category_id)
);