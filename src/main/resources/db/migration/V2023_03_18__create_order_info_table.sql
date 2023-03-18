DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
    `id`            VARCHAR(36)  PRIMARY KEY NOT NULL,
    `contact_id`    VARCHAR(36)  NOT NULL,
    `order_type`    INT(11),
    `order_status`  INT(4),
    `order_fee`     DECIMAL(18,2),
    `delivery_fee`  DECIMAL(18,2),
    `created_at`    DATETIME     NOT NULL,
    `updated_at`    DATETIME     NOT NULL,
    `deleted`       BOOLEAN      NOT NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

