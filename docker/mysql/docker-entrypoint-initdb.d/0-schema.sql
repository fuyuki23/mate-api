CREATE TABLE `user` (
                        `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                        `email` varchar(255) UNIQUE NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `first_name` varchar(32),
                        `last_name` varchar(32) NOT NULL,
                        `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                        `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE `workspace` (
                             `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                             `name` varchar(255) UNIQUE NOT NULL,
                             `slug` varchar(255) UNIQUE NOT NULL,
                             `size` integer NOT NULL DEFAULT 10,
                             `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                             `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                             `create_by` binary(16),
                             `update_by` binary(16)
);

CREATE TABLE `workspace_user` (
                                  `workspace_id` binary(16) NOT NULL,
                                  `user_id` binary(16) NOT NULL,
                                  `role` smallint NOT NULL,
                                  `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                  `create_by` binary(16),
                                  PRIMARY KEY (`workspace_id`, `user_id`)
);

CREATE TABLE `project` (
                           `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                           `workspace_id` binary(16) NOT NULL,
                           `leader_id` binary(16) NOT NULL,
                           `identifier` varchar(100) NOT NULL,
                           `name` varchar(255) NOT NULL,
                           `description` text,
                           `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                           `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                           `create_by` binary(16),
                           `update_by` binary(16)
);

CREATE TABLE `project_user` (
                                `workspace_id` binary(16) NOT NULL,
                                `project_id` binary(16) NOT NULL,
                                `user_id` binary(16) NOT NULL,
                                `role` smallint NOT NULL,
                                `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                `create_by` binary(16),
                                `update_by` binary(16),
                                PRIMARY KEY (`workspace_id`, `project_id`, `user_id`)
);

CREATE TABLE `state` (
                         `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                         `workspace_id` binary(16),
                         `project_id` binary(16),
                         `group` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `description` varchar(255),
                         `color` varchar(7) NOT NULL,
                         `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                         `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                         `create_by` binary(16),
                         `update_by` binary(16)
);

CREATE TABLE `issue` (
                         `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                         `workspace_id` binary(16) NOT NULL,
                         `project_id` binary(16) NOT NULL,
                         `parent_id` binary(16),
                         `sequence_id` bigint NOT NULL DEFAULT 1,
                         `state_id` binary(16) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `description` text,
                         `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                         `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                         `create_by` binary(16),
                         `update_by` binary(16)
);

CREATE TABLE `issue_attachment` (
                                    `issue_id` binary(16),
                                    `attachment_id` binary(16),
                                    PRIMARY KEY (`issue_id`, `attachment_id`)
);

CREATE TABLE `issue_assign` (
                                `issue_id` binary(16) NOT NULL,
                                `user_id` binary(16) NOT NULL,
                                `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                `create_by` binary(16),
                                `update_by` binary(16),
                                PRIMARY KEY (`issue_id`, `user_id`)
);

CREATE TABLE `issue_comment` (
                                 `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                                 `issue_id` binary(16) NOT NULL,
                                 `user_id` binary(16) NOT NULL,
                                 `content` text NOT NULL,
                                 `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                 `update_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                                 `create_by` binary(16),
                                 `update_by` binary(16)
);

CREATE TABLE `issue_comment_attachment` (
                                            `comment_id` binary(16),
                                            `attachment_id` binary(16),
                                            PRIMARY KEY (`comment_id`, `attachment_id`)
);

CREATE TABLE `attachment` (
                              `id` binary(16) PRIMARY KEY DEFAULT (UUID()),
                              `workspace_id` binary(16) NOT NULL,
                              `link` text NOT NULL,
                              `create_at` timestamp NOT NULL DEFAULT (CURRENT_TIMESTAMP),
                              `create_by` binary(16)
);

CREATE UNIQUE INDEX `project_index_0` ON `project` (`workspace_id`, `identifier`);

CREATE UNIQUE INDEX `project_index_1` ON `project` (`workspace_id`, `name`);

CREATE UNIQUE INDEX `state_index_2` ON `state` (`workspace_id`, `project_id`, `name`);

ALTER TABLE `workspace` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `workspace` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `workspace_user` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `workspace_user` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `workspace_user` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`leader_id`) REFERENCES `user` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `project` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `project_user` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `project_user` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);

ALTER TABLE `project_user` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `project_user` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `project_user` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `state` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `state` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);

ALTER TABLE `state` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `state` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`project_id`) REFERENCES `project` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`parent_id`) REFERENCES `issue` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`state_id`) REFERENCES `state` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue_attachment` ADD FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`);

ALTER TABLE `issue_attachment` ADD FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`id`);

ALTER TABLE `issue_assign` ADD FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`);

ALTER TABLE `issue_assign` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `issue_assign` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue_assign` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue_comment` ADD FOREIGN KEY (`issue_id`) REFERENCES `issue` (`id`);

ALTER TABLE `issue_comment` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `issue_comment` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue_comment` ADD FOREIGN KEY (`update_by`) REFERENCES `user` (`id`);

ALTER TABLE `issue_comment_attachment` ADD FOREIGN KEY (`comment_id`) REFERENCES `issue_comment` (`id`);

ALTER TABLE `issue_comment_attachment` ADD FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`id`);

ALTER TABLE `attachment` ADD FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`);

ALTER TABLE `attachment` ADD FOREIGN KEY (`create_by`) REFERENCES `user` (`id`);
