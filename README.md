# Deckbuilding API

This API will manage the creation of decks, collections, and posts for an application. Users will be able to log in and create/view decks, manage a collection of cards, and post comments on their decks and other users' decks.

## Models

The models will be as follows:

### User

Javascript/JSON/Java Class:
``` js
{
    userID: number,
    username: number,
    password: string,
    email: string,
    firstName: string,
    lastName: string,
    role: Role
}
```

Database:
``` sql
CREATE TABLE td_user (
    user_id SERIAL PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    first_name TEXT,
    last_name TEXT, 
    role_id INTEGER REFERENCES td_role(role_id) NOT NULL
);
```

### Role

Javascript/JSON/Java Class:
``` js
{
    roleID: number,
    role: string
}
```

js:
```sql
CREATE TABLE td_role (
    role_id SERIAL PRIMARY KEY,
    role TEXT NOT NULL
);
```

### Card

Javascript/JSON/Java Class:
```js
{
    cardID: number,
    scryfallURI: string,
    fallbackURI: string
}
```

Database:
```sql
CREATE TABLE td_card (
    card_id SERIAL PRIMARY KEY,
    card_scryfall_uri TEXT NOT NULL,
    card_fallback_uri TEXT NOT NULL
);
```

### Deck

Database:
```sql
CREATE TABLE td_formats (
	format_id SERIAL PRIMARY KEY,
	format TEXT NOT NULL
);

CREATE TABLE td_deck_cards (
    deck_cards_id SERIAL PRIMARY KEY,
	deck_id INTEGER REFERENCES td_deck(deck_id) NOT NULL,
	card_id INTEGER REFERENCES td_card(card_id) NOT NULL,
	card_amount INTEGER NOT NULL,
    UNIQUE (deck_id, card_id)
);

CREATE TABLE td_deck (
    deck_id SERIAL PRIMARY KEY,
    deck_author INTEGER REFERENCES td_user(user_id) NOT NULL,
    deck_name TEXT NOT NULL,
    deck_description TEXT NOT NULL,
    deck_private BOOLEAN NOT NULL DEFAULT false,
    deck_prototype BOOLEAN NOT NULL DEFAULT false,
    deck_creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    deck_last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    deck_format INTEGER REFERENCES td_formats(format_id) NOT NULL,
    deck_featured_card_id INTEGER REFERENCES td_card(card_id) NOT NULL
);
```
### Collection

Database:
```sql
CREATE TABLE td_collection_cards (
    collection_cards_id SERIAL PRIMARY KEY,
	collection_id INTEGER REFERENCES td_collection(collection_id) NOT NULL,
	card_id INTEGER REFERENCES td_card(card_id) NOT NULL,
	card_amount INTEGER NOT NULL,
    UNIQUE (collection_id, card_id)
);

CREATE TABLE td_collection (
    collection_id SERIAL PRIMARY KEY,
    collection_author INTEGER REFERENCES td_user(user_id) NOT NULL,
    collection_name TEXT NOT NULL, 
    collection_description  TEXT NOT NULL,
    collection_private BOOLEAN NOT NULL DEFAULT false,
    collection_prototype BOOLEAN NOT NULL DEFAULT false,
    collection_creation_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    collection_last_updated TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT current_timestamp,
    collection_featured_card_id INTEGER REFERENCES td_card(card_id) NOT NULL
);
```

## Security

Security will be handled with JSON Web Tokens. Information on them exists at [https://medium.com/dev-bits/a-guide-for-adding-jwt-token-based-authentication-to-your-single-page-nodejs-applications-c403f7cf04f4](https://medium.com/dev-bits/a-guide-for-adding-jwt-token-based-authentication-to-your-single-page-nodejs-applications-c403f7cf04f4), and [https://jwt.io/](https://jwt.io/).

## Endpoints

The endpoints will be as follows:

- Login
  - POST /login
  - GET /login
- User
  - POST /user
  - PATCH /user/userid
- Deck
  - POST /deck
  - GET /deck?deckid:id&userid:id
  - PATCH /deck/:deckid
- Collection
  - POST /collection
  - GET /collection?userid=:id&collectionid=:id
  - PATCH /collection/:collectionid

### POST /login

- Description
  - This endpoint is for user login. It will send back a user JWT if the credentials are correct. On failure, it informs the client of the reason.
- URL:
  - /login
- Method:
  - POST
- Headers:
  - NONE
- Body:
  - ```json 
    {
        "username": "string",
        "password": "string"
    }
- Response:
  - Status Code 200 OK
    - ```json
        {
            "token": JWT valid for 3 days
        }
- ERROR RESPONSE(S):
  - Status Code 404 NOT FOUND
    - ```json 
        {
            "message": "Invalid Username"
        }
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Invalid Password"
        }

### GET /login

- Description
  - This endpoint is for updating the user JWT. It takes in a JWT and returns a valid JWT if the supplied JWT is valid. Informs the client of failure.
- URL:
  - /login
- Method:
  - GET
- Headers:
  - ```json
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - None
- Response:
  - Status Code 200 OK
    - ```json
        {
            "token": JWT valid for 3 days
        }
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Invalid JWT Supplied"
        }

### POST /user

- Description
  - This endpoint is for user sign-up. It will return a status code informing the client of success, with a message. User will be required to log in after.
- URL:
  - /user
- Method:
  - POST
- Headers:
  - none
- Body:
  - ```json 
        {
            "username": "string",
            "password": "string",
            "email": "string"
        }
- Response:
  - Status Code 201 CREATED
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Username is taken"
        }
    - ```json 
        {
            "message": "Email already exists in our system."
        }

### PATCH /user

- Description
  - This endpoint is for updating users.
- URL:
  - /user
- Method:
  - PATCH
- Headers:
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - ```json 
        {
            "username": "string",
            "password": "string",
            "firstName": "string",
            "lastName": "string",
            "email": "string"
        }
- Response:
  - Status Code 200 OK
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Username is taken"
        }
    - ```json 
        {
            "message": "Email already exists in our system."
        }

### POST /deck

- Description
  - This endpoint is for posting decks
- URL:
  - /deck
- Method:
  - POST
- Headers:
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - ```json 
        {
            "deckName": "string",
            "deckDescription": "string",
            "deckFormat":  "string",
            "deckPrivacy": "boolean",
            "deckPrototype": "boolean",
            "deckFeaturedCard": "string",
            "cardIDs": [
                    "cid1": "number",
                    "cid2": "number",
                    … etc
            ]
        }
- Response:
  - Status Code 201 Created
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Deck must have a name"
        }
    - ```json 
        {
            "message": "Deck must have a description"
        }
    - ```json 
        {
            "message": "Deck must have a name"
        }
    - ```json 
        {
            "message": "Deck must have at least one card in it"
        }

### GET /deck

- Description
  - This endpoint is for retrieving decks
- URL:
  - /deck
- Method:
  - GET
- Headers:
  - Headers will be optional on this endpoint. Anyone should be able to view any public decks.
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Parameters:
  - userid=:id
    - returns decks by a specific user. If currently logged in user matches this, will show them their private decks too.
  - deckid=:id
    - returns the deck with the specified ID if it is not private.
- Body:
  - None
- Response:
  - Status Code 200 OK
    - ```json 
        {
            "decks": [
                    deck Object 1,
                    deck Object 2
                    … etc
            ]
        }
- ERROR RESPONSE(S):
  - Status Code 404 DOESN'T EXIST
    - ```json 
        {
            "message": "No decks exist matching those criteria."
        }
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Deck is private"
        }
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Not valid search parameters"
        }

### PATCH /deck

- Description
  - This endpoint is for updating decks
- URL:
  - /deck/:deckid
- Method:
  - PATCH
- Headers:
  - ```json
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - ```json 
        {
            "deckName": "string",
            "deckDescription": "string",
            "deckFormat":  "string",
            "deckPrivacy": "boolean",
            "deckPrototype": "boolean",
            "deckFeaturedCard": "string",
            "cardIDs": [
                    "cid1": "number",
                    "cid2": "number",
                    … etc
            ]
        }
- Response:
  - Status Code 200 OK
    - ```json 
        {
            "message": "Deck updated successfully."
        }
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Deck not updated."
        }

### POST /collection

- Description
  - This endpoint is for posting collections
- URL:
  - /collection
- Method:
  - POST
- Headers:
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - ```json 
        {
            "collectionName": "string",
            "collectionDescription": "string",
            "collectionPrivacy": "boolean",
            "collectionFeaturedCard": "string",
            "cardIDs": [
                    "cid1": "number",
                    "cid2": "number",
                    … etc
            ]
        }
- Response:
  - Status Code 201 Created
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Collection must have a name"
        }
    - ```json 
        {
            "message": "Collection must have a description"
        }
    - ```json 
        {
            "message": "Collection must have a name"
        }
    - ```json 
        {
            "message": "Collection must have at least one card in it"
        }

### GET /collection

- Description
  - This endpoint is for retrieving collections
- URL:
  - /collection
- Method:
  - GET
- Headers:
  - Headers will be optional on this endpoint. Anyone should be able to view any public collections.
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Parameters:
  - userid=:id
    - returns collections by a specific user. If currently logged in user matches this, will show them their private collections too.
  - collectionid=:id
    - returns the collection with the specified ID if it is not private.
- Body:
  - None
- Response:
  - Status Code 200 OK
    - ```json 
        {
            "collections": [
                    collection Object 1,
                    collection Object 2
                    … etc
            ]
        }
- ERROR RESPONSE(S):
  - Status Code 404 DOESN'T EXIST
    - ```json 
        {
            "message": "No collections exist matching those criteria."
        }
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Collection is private"
        }
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Not valid search parameters"
        }

### PATCH /collection

- Description
  - This endpoint is for updating collections
- URL:
  - /collection/:collectionid
- Method:
  - PATCH
- Headers:
  - ```json 
        {
            "authentication": "Bearer " + JWT
        }
- Body:
  - ```json 
        {
            "collectionName": "string",
            "collectionDescription": "string",
            "collectionPrivacy": "boolean",
            "collectionFeaturedCard": "string",
            "cardIDs": [
                    "cid1": "number",
                    "cid2": "number",
                    … etc
            ]
        }
- Response:
  - Status Code 200 OK
    - ```json 
        {
            "message": "Collection updated successfully."
        }
- ERROR RESPONSE(S):
  - Status Code 400 BAD REQUEST
    - ```json 
        {
            "message": "Collection not updated."
        }

# Stretch Goals

These are things that will be implemented as stretch goals after primary functionality is implemented.

### Social

The social aspect of the program. Users will be able to comment/vote on decks. Will be restricted to system users. Only upvote, never downvote.?