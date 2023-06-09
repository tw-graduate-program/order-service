DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `contact_id`    VARCHAR(36)  NOT NULL,
    `order_type`    INT(11),
    `order_status`  INT(4),
    `order_fee`     DECIMAL(18,2),
    `delivery_fee`  DECIMAL(18,2),
    `created_at`    DATETIME     NULL,
    `updated_at`    DATETIME     NULL,
    `deleted`       BOOLEAN      DEFAULT FALSE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

