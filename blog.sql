SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '博客标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客内容',
  `firstPicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '首图地址',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章类型',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客描述',
  `views` int(11) NULL DEFAULT 0 COMMENT '浏览次数',
  `appreciationSwitch` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否开启赞赏',
  `copyRightSwitch` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否版权认证',
  `commentSwitch` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否开启评论',
  `publish` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否发布',
  `recommend` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `type_id` bigint(20) NOT NULL COMMENT '博客类型编号',
  `user_id` bigint(20) NOT NULL COMMENT '作者编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_blog_FK`(`type_id`) USING BTREE,
  INDEX `user_blog_FK`(`user_id`) USING BTREE,
  CONSTRAINT `type_blog_FK` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_blog_FK` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_comments`;
CREATE TABLE `t_blog_comments`  (
  `blog_id` bigint(20) NOT NULL COMMENT '博客编号',
  `comment_id` bigint(20) NOT NULL COMMENT '评论编号',
  PRIMARY KEY (`blog_id`, `comment_id`) USING BTREE,
  INDEX `blog_comment_comment_id`(`comment_id`, `blog_id`) USING BTREE,
  CONSTRAINT `blog_comment_blod_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_comment_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blog_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`blog_id`, `tag_id`) USING BTREE,
  UNIQUE INDEX `blog_tags_index`(`blog_id`, `tag_id`) USING BTREE,
  INDEX `blog_tags_tag_id_FK`(`tag_id`) USING BTREE,
  CONSTRAINT `blog_tags_blog_id_FK` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `blog_tags_tag_id_FK` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论者邮箱',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者头像',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论时间',
  `adminComment` tinyint(4) NULL DEFAULT NULL COMMENT '是否为博主评论',
  `parentCommentId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_parent_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_parent_comments`;
CREATE TABLE `t_parent_comments`  (
  `parent_id` bigint(20) NOT NULL COMMENT '父评论编号',
  `child_id` bigint(20) NOT NULL COMMENT '子评论编号',
  PRIMARY KEY (`parent_id`, `child_id`) USING BTREE,
  UNIQUE INDEX `multi_comment_index`(`parent_id`, `child_id`) USING BTREE,
  INDEX `parent_comment_child_id`(`child_id`) USING BTREE,
  CONSTRAINT `parent_comment_child_id` FOREIGN KEY (`child_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parent_comment_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类型编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `type` int(255) NULL DEFAULT NULL COMMENT '用户类型',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
