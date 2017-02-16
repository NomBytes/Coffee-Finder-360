/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  zwingo
 * Created: Feb 4, 2017
 */

create table users (
    userid serial primary key,
    username varchar(40),
    email varchar(40),
    password char(64)
);

create table reviews (
    reviewid serial primary key,
    userid integer,
    shopid integer,
    coffeescore integer,
    burritoscore integer,
    dollarscore integer,
    review text,
    helpfulcount integer,
    unhelpfulcount integer,
    dateadded timestamp
);

create table shops (
    shopid serial primary key,
    shopname varchar(255),
    shopaddress varchar(255),
    shopphone varchar(12)
);



