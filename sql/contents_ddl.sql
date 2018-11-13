CREATE TABLE `contents` (
  `content_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `version` float DEFAULT NULL,
  `editor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  KEY `FKlgx2r4pavvk23v7ae2imlh860` (`editor_id`),
  CONSTRAINT `FKlgx2r4pavvk23v7ae2imlh860` FOREIGN KEY (`editor_id`) REFERENCES `editors` (`attachment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
