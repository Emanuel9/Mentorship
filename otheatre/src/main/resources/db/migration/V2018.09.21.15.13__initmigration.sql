-- Database: `theatredb`
--

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL,
  `user_comment` varchar(255) DEFAULT NULL,
  `comment_creator_user_profile_id` int(11) DEFAULT NULL,
  `event_event_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `event`
--

CREATE TABLE `event` (
  `event_id` bigint(20) NOT NULL,
  `event_description` varchar(255) NOT NULL,
  `event_end_date` datetime NOT NULL,
  `event_start_date` datetime NOT NULL,
  `event_title` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `event_hall`
--

CREATE TABLE `event_hall` (
  `event_id` bigint(20) NOT NULL,
  `hall_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `event_participants`
--

CREATE TABLE `event_participants` (
  `events_attended_event_id` bigint(20) NOT NULL,
  `participants_user_profile_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `hall`
--

CREATE TABLE `hall` (
  `hall_id` int(11) NOT NULL,
  `hall_name` varchar(255) DEFAULT NULL,
  `number_of_rows` int(11) DEFAULT NULL,
  `number_of_seats_per_row` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `hall`
--

INSERT INTO `hall` (`hall_id`, `hall_name`, `number_of_rows`, `number_of_seats_per_row`) VALUES
(1, 'Hall1', 5, 10),
(2, 'Hall2', 7, 10),
(3, 'Hall3', 8, 10);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(7),
(7),
(7),
(7),
(7),
(7);

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `user_score` int(11) DEFAULT NULL,
  `written_review` varchar(255) DEFAULT NULL,
  `event_event_id` bigint(20) DEFAULT NULL,
  `review_creator_user_profile_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `seat`
--

CREATE TABLE `seat` (
  `seat_id` int(11) NOT NULL,
  `row_number` int(11) DEFAULT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `hall_hall_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Salvarea datelor din tabel `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `role`) VALUES
(1, 'admin@otheatre.com', '$2a$10$t78crzwCky.5nrjjhtKyUO/bbtwyvjTbuE68JnScFO7DI.erLUZQC', 'ADMIN');

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user_profile`
--

CREATE TABLE `user_profile` (
  `user_profile_id` int(11) NOT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `link_to_profile_picture` varchar(255) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user_profile_comments`
--

CREATE TABLE `user_profile_comments` (
  `user_profile_user_profile_id` int(11) NOT NULL,
  `comments_comment_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structura de tabel pentru tabelul `user_profile_reviews`
--

CREATE TABLE `user_profile_reviews` (
  `user_profile_user_profile_id` int(11) NOT NULL,
  `reviews_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `FKr1fx6djnxesfiigk1qimgso74` (`comment_creator_user_profile_id`),
  ADD KEY `FKm93vrnbg2rbpyq9l3inxfg7lh` (`event_event_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FKi8bsvlthqr8lngsyshiqsodak` (`user_id`);

--
-- Indexes for table `event_hall`
--
ALTER TABLE `event_hall`
  ADD KEY `FKch37gph9caqbsbmhd3k6xrgeb` (`hall_id`),
  ADD KEY `FKk4xl89r5qkjrunsxqvb0pc6ka` (`event_id`);

--
-- Indexes for table `event_participants`
--
ALTER TABLE `event_participants`
  ADD KEY `FKr91ylpp40u64q7slyjnaciyko` (`participants_user_profile_id`),
  ADD KEY `FKhv3rypycicq4pundli4axvbay` (`events_attended_event_id`);

--
-- Indexes for table `hall`
--
ALTER TABLE `hall`
  ADD PRIMARY KEY (`hall_id`),
  ADD UNIQUE KEY `UK_imjhebpr8q8ymvdtb2v1abnyi` (`hall_name`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKohljaj26wnop5icex6g55s5v4` (`event_event_id`),
  ADD KEY `FK656dca1rhi75440vefxjq88wa` (`review_creator_user_profile_id`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`seat_id`),
  ADD KEY `FK5a9kj7se8io4iafh0t22ax4px` (`hall_hall_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Indexes for table `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`user_profile_id`),
  ADD KEY `FKjnk5mnkxgvtmckfv2u87ehxyi` (`user_user_id`);

--
-- Indexes for table `user_profile_comments`
--
ALTER TABLE `user_profile_comments`
  ADD UNIQUE KEY `UK_row0hlrgc5mh8sapufh20348p` (`comments_comment_id`),
  ADD KEY `FKrt0xriv3f1nypxlocf7i1d3tq` (`user_profile_user_profile_id`);

--
-- Indexes for table `user_profile_reviews`
--
ALTER TABLE `user_profile_reviews`
  ADD UNIQUE KEY `UK_gmqvx47romieam5ymoe5lfnus` (`reviews_id`),
  ADD KEY `FK5g1o7m2yuv7archmk5c8nmh6h` (`user_profile_user_profile_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;COMMIT;

