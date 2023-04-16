SELECT `account`.`Name`,
    `account`.`Address`,
    `account`.`Email`,
    `account`.`Dob`,
    `account`.`type`,
    `account`.`Credit`,
    `account`.`CVV`,
    `account`.`accountNum`,
    `account`.`balance`
FROM `project`.`account`;

SELECT `transaction_history`.`accountNum`,
    `transaction_history`.`Date`,
    `transaction_history`.`Debit`,
    `transaction_history`.`Credit`,
    `transaction_history`.`balance`,
    `transaction_history`.`id`
FROM `project`.`transaction_history`;
