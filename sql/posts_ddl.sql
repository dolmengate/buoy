CREATE TABLE `posts` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_modified` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKnqdwsyau6lq0rsljmg57tv8tk` (`content_id`),
  CONSTRAINT `FKnqdwsyau6lq0rsljmg57tv8tk` FOREIGN KEY (`content_id`) REFERENCES `contents` (`content_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
