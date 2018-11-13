CREATE TABLE `comments` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `post_post_id` bigint(20) DEFAULT NULL,
  `reply_to_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK2dgdoh41vnobahepwimydikli` (`post_post_id`),
  KEY `FK9cnyv7g9gt5qgsci8uy6sc7d6` (`reply_to_comment_id`),
  CONSTRAINT `FK2dgdoh41vnobahepwimydikli` FOREIGN KEY (`post_post_id`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `FK9cnyv7g9gt5qgsci8uy6sc7d6` FOREIGN KEY (`reply_to_comment_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
