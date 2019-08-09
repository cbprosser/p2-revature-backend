CREATE TABLE td_role (
    role_id SERIAL PRIMARY KEY,
    role TEXT NOT NULL
);

CREATE TABLE td_formats (
	format_id SERIAL PRIMARY KEY,
	format TEXT NOT NULL
);

CREATE TABLE td_user (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    first_name TEXT,
    last_name TEXT, 
    role_id INTEGER REFERENCES td_role(role_id) NOT NULL
);

CREATE TABLE td_deck (
    deck_id SERIAL PRIMARY KEY,
    deck_author INTEGER REFERENCES td_user(user_id) NOT NULL,
    deck_name TEXT NOT NULL,
    deck_description TEXT NOT NULL,
    deck_private BOOLEAN NOT NULL DEFAULT false,
    deck_prototype BOOLEAN NOT NULL DEFAULT true,
    deck_creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    deck_last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    deck_format INTEGER REFERENCES td_formats(format_id) NOT NULL,
    deck_featured_card TEXT
);

CREATE TABLE td_deck_cards (
    deck_cards_id SERIAL PRIMARY KEY,
	deck_id INTEGER REFERENCES td_deck(deck_id) NOT NULL,
	deck_card TEXT NOT NULL,
	deck_card_amount INTEGER NOT NULL,
    UNIQUE (deck_id, deck_card)
);

CREATE TABLE td_collection (
    collection_id SERIAL PRIMARY KEY,
    collection_author INTEGER REFERENCES td_user(user_id) NOT NULL,
    collection_name TEXT NOT NULL, 
    collection_description  TEXT NOT NULL,
    collection_private BOOLEAN NOT NULL DEFAULT false,
    collection_prototype BOOLEAN NOT NULL DEFAULT true,
    collection_creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    collection_last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    collection_featured_card TEXT
);

CREATE TABLE td_collection_cards (
    collection_cards_id SERIAL PRIMARY KEY,
	collection_id INTEGER REFERENCES td_collection(collection_id) NOT NULL,
	collection_card TEXT NOT NULL,
	collection_card_amount INTEGER NOT NULL,
    UNIQUE (collection_id, collection_card)
);
