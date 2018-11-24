-- todo add users, logins & related tables

SET foreign_key_checks = 0;
commit;

DROP TABLE IF EXISTS `attachment_type`;
CREATE TABLE `attachment_type` (
  `attachment_type_id` INTEGER(1) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`attachment_type_id`)
);
INSERT INTO buoy_test.attachment_type(attachment_type_id, name) VALUES(1, 'EDITOR');
INSERT INTO buoy_test.attachment_type(attachment_type_id, name) VALUES(2, 'FILE');
INSERT INTO buoy_test.attachment_type(attachment_type_id, name) VALUES(3, 'IMAGE');


DROP TABLE IF EXISTS `prog_lang`;
CREATE TABLE `prog_lang` (
  `prog_lang_id` INTEGER(1) NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`prog_lang_id`)
);
INSERT INTO buoy_test.prog_lang(prog_lang_id, name) VALUES(1, 'Java');
INSERT INTO buoy_test.prog_lang(prog_lang_id, name) VALUES(2, 'Python');
INSERT INTO buoy_test.prog_lang(prog_lang_id, name) VALUES(3, 'JavaScript');


-- post can have many attachments
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `attachment_id` char(36) NOT NULL,
  `attachment_type_id` INTEGER(1) NOT NULL,
  `post_id` char(36) NOT NULL,
  `version` float NOT NULL DEFAULT 1.0,
  `text` varchar(5000) DEFAULT NULL,
  `prog_lang_id` INTEGER(1) DEFAULT NULL,
  `image_uri` varchar(64) DEFAULT NULL,
  `file_uri` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `index_attachment_post_id` (`post_id`),
  KEY `index_attachment_type_id` (`attachment_type_id`),
  CONSTRAINT `FK_attachment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_prog_lang_id` FOREIGN KEY (`prog_lang_id`) REFERENCES `prog_lang` (`prog_lang_id`),
  CONSTRAINT `FK_attachment_attachment_type_id` FOREIGN KEY (`attachment_type_id`) REFERENCES `attachment_type` (`attachment_type_id`)
);


DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` char(36) NOT NULL,
  `author` varchar(255) NOT NULL,
  `created` datetime NOT NULL DEFAULT NOW(),
  `last_modified` datetime DEFAULT NULL,
  `version` float NOT NULL DEFAULT 1.0,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(64) NOT NULL,
  `last_modified_by` char(36),
  PRIMARY KEY (`post_id`)
);


DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` char(36) NOT NULL,
  `author` varchar(64) NOT NULL,
  `created` datetime NOT NULL DEFAULT NOW(),
  `last_modified` datetime DEFAULT NULL,
  `text` varchar(5000) NOT NULL,
  `post_id` char(36) NOT NULL,
  `reply_to_comment_id` char(36) DEFAULT NULL,
  `reply_to_attachment_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK2dgdoh41vnobahepwimydikli` (`post_id`),
--   KEY `FK9cnyv7g9gt5qgsci8uy6sc7d6` (`reply_to_comment_id`),
  CONSTRAINT `FK2dgdoh41vnobahepwimydikli` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK9cnyv7g9gt5qgsci8uy6sc7d6` FOREIGN KEY (`reply_to_comment_id`) REFERENCES `comment` (`comment_id`),
  CONSTRAINT `FK_comment_reply_to_attachment_id` FOREIGN KEY (`reply_to_attachment_id`) REFERENCES `attachment` (`attachment_id`)
);


-- DROP TABLE IF EXISTS `post_comment`;
-- CREATE TABLE `post_comment` (
--  `post_id` char(36) NOT NULL,
--  `comment_id` char(36) NOT NULL,
--  PRIMARY KEY (`post_id`,`comment_id`),
--  CONSTRAINT `FK608b8p55kej4ce02qk9uim6be` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`comment_id`),
--  CONSTRAINT `FKiq8a7nqb171ojc9xk99lxoisd` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
-- );


SET foreign_key_checks = 1;
