create database if not exists projectDB;

use projectDB;

drop table if exists employeeTB;

create table if not exists `employeeTB` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(64) ,
    `last_name` varchar(64) ,
    `start_date` varchar(64) default null ,
    `start_salary` int(11) default null ,
    `employee_contract_signed` varchar(64) default null ,
    `social_security_number` varchar(64) default null ,
    `birth_date` varchar(64) ,
    `phone_number` varchar(64) ,
    `emergency_contact` varchar(64) ,
    `emergency_number` varchar(64) ,
    primary key (`id`)
)engine=InnoDB auto_increment=1 default charset=latin1;

insert into `employeeTB`(`first_name`, `last_name`, `start_date`, `start_salary`, `employee_contract_signed`, 
`social_security_number`, `birth_date`, `phone_number`, `emergency_contact`, `emergency_number`) 
values ('Brandon', 'Shells', '10/25/2002', 80000, 'Y', '358-65-2686', '12/10/1985', '717-684-5398', 'Jake Lin', '717-683-2684') ;

insert into `employeeTB`(`first_name`, `last_name`, `start_date`, `start_salary`, `employee_contract_signed`, 
`social_security_number`, `birth_date`, `phone_number`, `emergency_contact`, `emergency_number`) 
values ('John', 'Wick', '06/21/2005', 100000, 'Y', '384-24-7395', '04/17/1995', '717-359-1485', 'William Rake', '717-384-4875') ;

insert into `employeeTB`(`first_name`, `last_name`, `start_date`, `start_salary`, `employee_contract_signed`, 
`social_security_number`, `birth_date`, `phone_number`, `emergency_contact`, `emergency_number`) 
values ('Nick', 'Lance', '09/04/2001', 60000, 'Y', '861-76-0403', '10/17/1987', '717-725-1683', 'Jake Williams', '717-204-7156') ;

insert into `employeeTB`(`first_name`, `last_name`, `start_date`, `start_salary`, `employee_contract_signed`, 
`social_security_number`, `birth_date`, `phone_number`, `emergency_contact`, `emergency_number`) 
values ('Nancy', 'Shick', '08/05/1994', 95000, 'Y', '067-73-8276', '12/20/1992', '717-188-4328', 'Lincy Mitch', '707-593-5727') ;

DELIMITER $$
DROP PROCEDURE IF EXISTS `get_employee`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employee`(IN fir_nam VARCHAR(64), las_nam varchar(64))
BEGIN

	SELECT * from employeeTB where first_name=fir_nam and last_name=las_nam;

END$$
DELIMITER ;