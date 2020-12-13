drop table if exists museum_item_sets;
drop table if exists museum_funds;
drop table if exists persons;
drop table if exists museum_items;
drop table if exists museum_item_movements;

CREATE TABLE museum_item_sets
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE museum_funds
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);


CREATE TABLE persons
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName  varchar(100) NOT NULL,
    secondName varchar(100) NOT NULL,
    middleName varchar(100) NOT NULL
);

CREATE TABLE museum_items
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventoryNumber varchar(100) not null UNIQUE,
    name             varchar(100) NOT NULL UNIQUE,
    author_id        BIGINT  NOT NULL REFERENCES persons (id),
    set_id           BIGINT  NOT NULL REFERENCES museum_item_sets (id),
    fund_id          BIGINT  NOT NULL REFERENCES museum_funds (id),
    creationDate    DATE,
    annotation       varchar(100)
);

CREATE TABLE museum_item_movements
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acceptDate           timestamp,
    exhibitTransferDate timestamp,
    exhibitReturnDate   timestamp,
    item_id               BIGINT NOT NULL REFERENCES museum_items (id),
    responsible_person_id BIGINT NOT NULL REFERENCES persons (id)
);