-- This hashes data. To use, call function crypt('stringToBeHashedSuchAsPassword', gen_salt('bf',7)) --
-- useful pgcrypto resource: https://til.hashrocket.com/posts/a2170ba6b0-salt-and-hash-a-password-with-postgres-pgcrypto --
-- Also maybe read up on password hashing. We're using the BlowFish algorithm hashed 7 times, which is pretty hard to beat --
CREATE EXTENSION pgcrypto;

INSERT INTO td_role (role)
    VALUES ('Administrator'), ('Moderator'), ('User');

INSERT INTO td_user (username, password, email, first_name, last_name, role_id)
VALUES ('cbprosser', crypt('password', gen_salt('bf',7)), 'cbprosser@tempodeck.com', 'chris', 'prosser', (SELECT role_id FROM td_role WHERE role='Administrator')),
	   ('mjarsenault', crypt('password', gen_salt('bf',7)), 'mjarsenault@tempodeck.com', 'matt', 'arsenault', (SELECT role_id FROM td_role WHERE role='Administrator')),
	   ('nbray', crypt('password', gen_salt('bf',7)), 'nbray@tempodeck.com', 'nicholas', 'bray', (SELECT role_id FROM td_role WHERE role='Administrator')),
	   ('lescobosasainz', crypt('password', gen_salt('bf',7)), 'lescobosa@tempodeck.com', 'luis', 'escobosa', (SELECT role_id FROM td_role WHERE role='Administrator')),
	   ('hatebear', crypt('password', gen_salt('bf',7)), 'deathntaxes@tempodeck.com', 'Thalia', '', (SELECT role_id FROM td_role WHERE role='Moderator')),
	   ('no.x.spells', crypt('password', gen_salt('bf',7)), 'fairmagiconly@tempodeck.com', 'Gaddock', 'Teeg', (SELECT role_id FROM td_role WHERE role='Moderator')),
	   ('monoUDelver', crypt('password', gen_salt('bf',7)), 'delver.of.secrets@tempodeck.com', '', '', (SELECT role_id FROM td_role WHERE role='User')),
	   ('simicrulezzz', crypt('password', gen_salt('bf',7)), 'pszegana@tempodeck.com', '', '', (SELECT role_id FROM td_role WHERE role='User'));

INSERT INTO td_formats (format)
VALUES ('Commander'), ('Standard'), ('Modern'), ('Pauper'), ('Brawl'), ('Legacy'), ('Vintage');



INSERT INTO td_deck (deck_author, deck_name, deck_description, deck_format)
VALUES ((SELECT user_id FROM td_user WHERE username = 'cbprosser'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard')),
	   ((SELECT user_id FROM td_user WHERE username = 'hatebear'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard')),
	   ((SELECT user_id FROM td_user WHERE username = 'monoUDelver'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard')),
	   ((SELECT user_id FROM td_user WHERE username = 'no.x.spells'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard'));

INSERT INTO td_collection (collection_author, collection_name, collection_description)
VALUES ((SELECT user_id FROM td_user WHERE username = 'cbprosser'), 'collection1', 'no description'),
	   ((SELECT user_id FROM td_user WHERE username = 'hatebear'), 'collection1', 'no description'),
	   ((SELECT user_id FROM td_user WHERE username = 'monoUDelver'), 'collection1', 'no description'),
	   ((SELECT user_id FROM td_user WHERE username = 'no.x.spells'), 'collection1', 'no description');

INSERT INTO td_deck_cards (deck_id, deck_card_name, deck_card_amount)
VALUES ()