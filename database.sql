CREATE TABLE `files` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `filename` varchar(100) NOT NULL,
  `notes` varchar(100) default NULL,
  `type` varchar(40) default NULL,
  `file` longblob default NULL,
  PRIMARY KEY  (`id`)
);