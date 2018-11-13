CREATE TABLE `posts_comments` (
  `post_post_id` bigint(20) NOT NULL,
  `comments_comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`post_post_id`,`comments_comment_id`),
  UNIQUE KEY `UK_61985v4tur31hxia7328wkkqu` (`comments_comment_id`),
  CONSTRAINT `FK608b8p55kej4ce02qk9uim6be` FOREIGN KEY (`comments_comment_id`) REFERENCES `comments` (`comment_id`),
  CONSTRAINT `FKiq8a7nqb171ojc9xk99lxoisd` FOREIGN KEY (`post_post_id`) REFERENCES `posts` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
