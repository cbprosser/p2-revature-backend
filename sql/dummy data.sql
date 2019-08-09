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
	   ('hatebear', crypt('password', gen_salt('bf',7)), 'deathntaxes@tempodeck.com', 'Thalia', '', (SELECT role_id FROM td_role WHERE role='Moderator')),
	   ('no.x.spells', crypt('password', gen_salt('bf',7)), 'fairmagiconly@tempodeck.com', 'Gaddock', 'Teeg', (SELECT role_id FROM td_role WHERE role='Moderator')),
	   ('monoUDelver', crypt('password', gen_salt('bf',7)), 'delver.of.secrets@tempodeck.com', '', '', (SELECT role_id FROM td_role WHERE role='User')),
	   ('simicrulezzz', crypt('password', gen_salt('bf',7)), 'pszegana@tempodeck.com', '', '', (SELECT role_id FROM td_role WHERE role='User'));

INSERT INTO td_formats (format)
VALUES ('Commander'), ('Standard'), ('Modern'), ('Pauper'), ('Brawl'), ('Legacy'), ('Vintage');

INSERT INTO td_card (card_scryfall_uri, card_fallback_uri)
VALUES ('https://api.scryfall.com/cards/de2de2bd-9ba7-4b6f-94c2-dafb2011a48e', 'no fallback yet'),
('https://api.scryfall.com/cards/1e8a43c1-42d1-45ef-8a63-4b87775a6e88', 'no fallback yet'),
('https://api.scryfall.com/cards/f8bacb12-da46-4b00-804f-9ff6bff452bc', 'no fallback yet'),
('https://api.scryfall.com/cards/a46a65e0-66a3-4896-8acc-0ad5e9927c40', 'no fallback yet'),
('https://api.scryfall.com/cards/20234668-53ce-4cc8-892f-30ee3ecfc34c', 'no fallback yet'),
('https://api.scryfall.com/cards/3dfb8817-ca3c-44ba-92f2-e9d6294cd25d', 'no fallback yet'),
('https://api.scryfall.com/cards/21c950d7-b4f6-4902-8c9a-98f2933f9fa5', 'no fallback yet'),
('https://api.scryfall.com/cards/b3c950e2-a43b-47f8-9ad6-1909ccc7acbf', 'no fallback yet'),
('https://api.scryfall.com/cards/9854c2ec-0e53-4ac7-b417-554a9331c5e0', 'no fallback yet'),
('https://api.scryfall.com/cards/80dc0310-afd9-49b4-b58f-a0e91120c38c', 'no fallback yet'),
('https://api.scryfall.com/cards/f426c92c-6e71-49f0-9a91-0d529bf8c17d', 'no fallback yet'),
('https://api.scryfall.com/cards/0503c55d-74bb-4165-9273-127c01bb2214', 'no fallback yet'),
('https://api.scryfall.com/cards/64d9c182-cbb3-4791-90dd-0e533ddeebda', 'no fallback yet'),
('https://api.scryfall.com/cards/5314bae2-4930-4f8a-8a52-853bc3feb88f', 'no fallback yet'),
('https://api.scryfall.com/cards/671c94c1-df52-4985-ad78-3e1c9c78df18', 'no fallback yet'),
('https://api.scryfall.com/cards/25afd4a7-4253-4a4e-a105-e92f64460faa', 'no fallback yet'),
('https://api.scryfall.com/cards/783da808-6698-4e55-9fac-430a6effe2b1', 'no fallback yet'),
('https://api.scryfall.com/cards/226f7c45-db9f-4d48-b575-4d2f1904c963', 'no fallback yet'),
('https://api.scryfall.com/cards/5d6178ed-6353-4733-81e1-7b3dc592c3bd', 'no fallback yet'),
('https://api.scryfall.com/cards/b4c8ddc1-d95c-499f-b1d1-f608f8f07b02', 'no fallback yet'),
('https://api.scryfall.com/cards/cc6686e6-4535-49be-b0b3-e76464656cd2', 'no fallback yet'),
('https://api.scryfall.com/cards/4e68b3bd-996e-4383-b134-f529bcb768de', 'no fallback yet'),
('https://api.scryfall.com/cards/e38252c3-fcc1-4bb7-8dd7-ba0e54ade6b7', 'no fallback yet'),
('https://api.scryfall.com/cards/0d8efd95-1c2f-4dd1-b70b-3cfb10ff3a28', 'no fallback yet'),
('https://api.scryfall.com/cards/2dea2466-5c7f-40ce-b749-100ae89d2c90', 'no fallback yet'),
('https://api.scryfall.com/cards/5f8bc010-f1af-42a2-9009-2039cf3d8f0a', 'no fallback yet'),
('https://api.scryfall.com/cards/a27efec0-40c4-48bc-a21a-3af28a6529b5', 'no fallback yet'),
('https://api.scryfall.com/cards/4c565076-5db2-47ea-8ee0-4a4fd7bb353d', 'no fallback yet'),
('https://api.scryfall.com/cards/dba41e5f-66b8-4459-8a07-bbe893216f1e', 'no fallback yet'),
('https://api.scryfall.com/cards/d38ad178-35a5-44ad-b80a-d746f3e6a45c', 'no fallback yet'),
('https://api.scryfall.com/cards/1aebbb57-31b3-4289-815e-4f529e29f3ea', 'no fallback yet'),
('https://api.scryfall.com/cards/b3656310-093d-4724-a399-7f7010843b1f', 'no fallback yet'),
('https://api.scryfall.com/cards/79883468-a37c-4894-8d05-6a4d150b7d59', 'no fallback yet'),
('https://api.scryfall.com/cards/c9045fcb-b633-4c35-8058-6234311551ae', 'no fallback yet'),
('https://api.scryfall.com/cards/6cc78151-8cb0-4521-9674-fb0c67e24a17', 'no fallback yet'),
('https://api.scryfall.com/cards/b4a22ecc-3a5c-44a1-bb52-bbeffa2dbf17', 'no fallback yet'),
('https://api.scryfall.com/cards/2435c810-2baf-4e3b-80ce-542b94694901', 'no fallback yet'),
('https://api.scryfall.com/cards/73b49065-0a46-4813-a721-71d718e73d18', 'no fallback yet'),
('https://api.scryfall.com/cards/7a58c77f-7c15-44f4-8011-6046482a2d08', 'no fallback yet'),
('https://api.scryfall.com/cards/d45ac8dc-281f-4257-9658-80af65a0295b', 'no fallback yet'),
('https://api.scryfall.com/cards/2ba18114-af6c-48cd-82c9-eb6541d566bf', 'no fallback yet'),
('https://api.scryfall.com/cards/b6a3b223-b232-4da3-9431-ccb4688d5941', 'no fallback yet'),
('https://api.scryfall.com/cards/39421ce8-86d5-4739-b6fd-78d63c0bb258', 'no fallback yet'),
('https://api.scryfall.com/cards/ee7b5ae5-0f6b-4f45-8597-4e6212d2ad0e', 'no fallback yet'),
('https://api.scryfall.com/cards/35100197-7914-450e-9205-57cb4fc345d6', 'no fallback yet'),
('https://api.scryfall.com/cards/e058dc51-b200-42ee-a345-a63cfaa397fe', 'no fallback yet'),
('https://api.scryfall.com/cards/80164e61-3e94-4e10-9bd1-518b8dc7fc4c', 'no fallback yet'),
('https://api.scryfall.com/cards/f4bae0e4-1143-4dc4-afb1-e6b4201ff101', 'no fallback yet'),
('https://api.scryfall.com/cards/e2f39777-b80a-4618-9310-a9e5b91bb2a2', 'no fallback yet'),
('https://api.scryfall.com/cards/f23899be-5551-4690-ac29-8f382484c227', 'no fallback yet'),
('https://api.scryfall.com/cards/aa3a4cb5-945e-4caf-8a18-1ef977879fe8', 'no fallback yet'),
('https://api.scryfall.com/cards/f17103dd-f31b-4f6e-b2ea-4ea91815bdd6', 'no fallback yet'),
('https://api.scryfall.com/cards/48181263-11fd-444e-9fef-02ccfe016c8c', 'no fallback yet'),
('https://api.scryfall.com/cards/708006ba-d494-4093-b108-8249b110831e', 'no fallback yet'),
('https://api.scryfall.com/cards/f0bfc9e0-14e8-43ce-8fca-773b7f2387dc', 'no fallback yet'),
('https://api.scryfall.com/cards/f5a39379-7313-463a-9a02-e5157b9557f4', 'no fallback yet'),
('https://api.scryfall.com/cards/a4d76d03-4fcf-42f8-8c2e-ad6b03d58677', 'no fallback yet'),
('https://api.scryfall.com/cards/9bae5e75-0048-41fd-8bbb-89607ab96d6e', 'no fallback yet'),
('https://api.scryfall.com/cards/2080556a-6c76-4819-9eb2-c4dd4c9dbd67', 'no fallback yet'),
('https://api.scryfall.com/cards/aa6d7bbe-0418-4a58-a97b-13b50eb0b642', 'no fallback yet'),
('https://api.scryfall.com/cards/c827bccf-38f9-4a7c-bd0e-038594a9f63b', 'no fallback yet'),
('https://api.scryfall.com/cards/62f69602-b5fd-46d6-8dae-d77df35e378c', 'no fallback yet'),
('https://api.scryfall.com/cards/f91ed618-7b0b-4a70-95ad-d9ed46e28692', 'no fallback yet'),
('https://api.scryfall.com/cards/56cb8fa2-337b-4596-9c31-01f0c0b171b7', 'no fallback yet'),
('https://api.scryfall.com/cards/bceb365c-5de6-47ae-b42d-7fbce7781f8e', 'no fallback yet'),
('https://api.scryfall.com/cards/c4a5f86f-44a8-4735-909a-770586d33a15', 'no fallback yet'),
('https://api.scryfall.com/cards/6f4400bf-134b-4011-985d-eed4e5ba1de8', 'no fallback yet'),
('https://api.scryfall.com/cards/1e90c638-d4b2-4243-bbc4-1cc10516c40f', 'no fallback yet'),
('https://api.scryfall.com/cards/bf3edaaf-cf63-4e17-94ae-9d9991d9fb5f', 'no fallback yet'),
('https://api.scryfall.com/cards/0ce4702d-f65b-413e-99da-112f632a0a63', 'no fallback yet'),
('https://api.scryfall.com/cards/09fbb1c0-ba57-4a5a-8ad6-77fbc6aeeec9', 'no fallback yet'),
('https://api.scryfall.com/cards/c6d47162-749b-47d5-9589-8f1dbf60b9f3', 'no fallback yet'),
('https://api.scryfall.com/cards/b209d219-b946-4226-a8b4-65a5f3837fac', 'no fallback yet'),
('https://api.scryfall.com/cards/787de9ce-02c5-4a17-a88b-d38e83dbeb0b', 'no fallback yet'),
('https://api.scryfall.com/cards/c4ac7570-e74e-4081-ac53-cf41e695b7eb', 'no fallback yet'),
('https://api.scryfall.com/cards/e39502fd-193c-4526-8dd8-0cd8c822552c', 'no fallback yet'),
('https://api.scryfall.com/cards/747223a5-c669-4d0e-a062-265eb47710cd', 'no fallback yet'),
('https://api.scryfall.com/cards/5391d2f1-e8d3-4d39-98cf-367888e10534', 'no fallback yet'),
('https://api.scryfall.com/cards/637c2d6a-e6b8-4dc5-81aa-da1b7384e006', 'no fallback yet'),
('https://api.scryfall.com/cards/b075a6f2-5196-45e9-b062-f131f7b1a347', 'no fallback yet'),
('https://api.scryfall.com/cards/13c41212-9f16-48e0-8c4b-985ce331164b', 'no fallback yet'),
('https://api.scryfall.com/cards/4c9e8f24-af62-4d13-bfed-a8b3294b64c3', 'no fallback yet'),
('https://api.scryfall.com/cards/deadf867-b999-49b2-88d8-91da975a3cc5', 'no fallback yet'),
('https://api.scryfall.com/cards/e811f37a-f381-42e7-9b4a-15b2241eb10d', 'no fallback yet'),
('https://api.scryfall.com/cards/58e2981b-bf25-4d9d-8811-5a1ff0b4d5d2', 'no fallback yet'),
('https://api.scryfall.com/cards/4e8eb264-dadb-440c-af85-273e755f1db6', 'no fallback yet'),
('https://api.scryfall.com/cards/f2df3258-c053-48a8-974f-d80899b2cd93', 'no fallback yet'),
('https://api.scryfall.com/cards/7dd5b0a1-104d-4e0f-82de-65487fbf01ff', 'no fallback yet'),
('https://api.scryfall.com/cards/906b6e99-128f-4c11-8daf-16099d35b0d4', 'no fallback yet'),
('https://api.scryfall.com/cards/ad454e7a-06c9-4694-ae68-7b1431e00077', 'no fallback yet'),
('https://api.scryfall.com/cards/b8f527d1-25c0-49b9-83d5-1278ca72d009', 'no fallback yet'),
('https://api.scryfall.com/cards/7f4840f1-3db3-4ba6-b75b-bbd87251a3af', 'no fallback yet'),
('https://api.scryfall.com/cards/3e214e0d-bd3d-467b-83fb-d8a232e166e4', 'no fallback yet'),
('https://api.scryfall.com/cards/fe84b3c0-bca2-42d3-a82c-540644e59625', 'no fallback yet'),
('https://api.scryfall.com/cards/e1ff5e66-4718-43c3-8a58-1a0a8e788f83', 'no fallback yet'),
('https://api.scryfall.com/cards/d19fbfe6-69bb-452a-be3c-b9c625e23193', 'no fallback yet'),
('https://api.scryfall.com/cards/a8e9f4d2-bba5-4061-8ae7-a68b912f2c11', 'no fallback yet'),
('https://api.scryfall.com/cards/fe479484-a827-4d3c-8e35-e905e4f6664a', 'no fallback yet'),
('https://api.scryfall.com/cards/bf49e5bf-07fb-44b0-8e74-092088d9019f', 'no fallback yet'),
('https://api.scryfall.com/cards/1b12dfc1-81f2-44b2-baa2-73cc21363978', 'no fallback yet'),
('https://api.scryfall.com/cards/e6966738-b4fc-4854-81b0-09de305854f2', 'no fallback yet'),
('https://api.scryfall.com/cards/d24b14ea-04f0-4d4b-970e-efc65d64945e', 'no fallback yet'),
('https://api.scryfall.com/cards/b42b2fb4-8016-462c-8764-2639adec931f', 'no fallback yet'),
('https://api.scryfall.com/cards/ea166114-2f9b-4ca6-b573-1f49f7485580', 'no fallback yet'),
('https://api.scryfall.com/cards/2f420b35-1f73-41c8-a15f-1aee4af0999c', 'no fallback yet'),
('https://api.scryfall.com/cards/3b9d72a8-8acf-42c6-8adf-cbaecb4a985a', 'no fallback yet'),
('https://api.scryfall.com/cards/93cf5412-c711-41b4-ab3b-7788a0a22228', 'no fallback yet'),
('https://api.scryfall.com/cards/60befc28-2ab8-4b59-a33f-0328c5d2f995', 'no fallback yet'),
('https://api.scryfall.com/cards/13aed078-9e29-48e7-b145-5252362031a0', 'no fallback yet'),
('https://api.scryfall.com/cards/c2fddecd-e660-43de-bccc-52f60a089052', 'no fallback yet'),
('https://api.scryfall.com/cards/303d51ab-b9c4-4647-950f-291daabe7b81', 'no fallback yet'),
('https://api.scryfall.com/cards/30dc237e-b28a-4b65-9790-6b434828bf2e', 'no fallback yet'),
('https://api.scryfall.com/cards/208c6f51-3c00-4fc6-8579-8f57444d0e97', 'no fallback yet'),
('https://api.scryfall.com/cards/49283832-54f2-4619-b4a9-750493c93292', 'no fallback yet'),
('https://api.scryfall.com/cards/d1a9594e-edc9-42b2-ba8a-8298da9441fb', 'no fallback yet'),
('https://api.scryfall.com/cards/504090bb-d183-4833-aea5-d4193b5c57a1', 'no fallback yet'),
('https://api.scryfall.com/cards/4d1d8aa1-d742-477c-819a-0113912d5011', 'no fallback yet'),
('https://api.scryfall.com/cards/5b012532-1186-4dc8-9d42-867e418b0280', 'no fallback yet'),
('https://api.scryfall.com/cards/b9e03567-c95a-40b8-a75a-971076093f57', 'no fallback yet'),
('https://api.scryfall.com/cards/e635c433-0398-442a-856e-1869f6bf2cfd', 'no fallback yet'),
('https://api.scryfall.com/cards/52a54ebc-008a-4180-a023-2e1d5318780c', 'no fallback yet'),
('https://api.scryfall.com/cards/0cfd2e76-ad64-42ff-a6a2-c4cf1bec5932', 'no fallback yet'),
('https://api.scryfall.com/cards/b0130d04-05f2-44f5-bd6c-8b11f798b69e', 'no fallback yet'),
('https://api.scryfall.com/cards/28129aaf-aaff-47f4-8dd2-8c576c55052c', 'no fallback yet'),
('https://api.scryfall.com/cards/445e41d8-317d-46ce-b858-54df716e0214', 'no fallback yet'),
('https://api.scryfall.com/cards/e72f4329-db6f-4284-b63e-55f22a0a0f6e', 'no fallback yet'),
('https://api.scryfall.com/cards/7cbf17a0-2dbc-4e79-9cfa-ea49b1605105', 'no fallback yet'),
('https://api.scryfall.com/cards/1db6393a-88b0-4973-9832-1482f69917ff', 'no fallback yet'),
('https://api.scryfall.com/cards/89f8e970-b90c-4829-8970-0a3364027bbb', 'no fallback yet'),
('https://api.scryfall.com/cards/39c9e4b5-364b-4c0b-bb47-266563a6abf2', 'no fallback yet'),
('https://api.scryfall.com/cards/8f1801f2-ea6e-4196-858e-2afc456cf6a0', 'no fallback yet'),
('https://api.scryfall.com/cards/9da6f595-41b2-4e52-b15a-6ad18e4232c7', 'no fallback yet'),
('https://api.scryfall.com/cards/b5873efa-d573-4435-81ad-48df2ca5c7f2', 'no fallback yet'),
('https://api.scryfall.com/cards/81e2b96b-ecf2-4dd9-bc9d-3c46ee8c59e6', 'no fallback yet'),
('https://api.scryfall.com/cards/1414939c-7f15-4eb3-abeb-6e75c52d2b52', 'no fallback yet'),
('https://api.scryfall.com/cards/39bab75a-2c1f-4ff1-a26e-134e52f61d39', 'no fallback yet'),
('https://api.scryfall.com/cards/c3e3b6c5-fd30-4d45-a122-ce60d5707357', 'no fallback yet'),
('https://api.scryfall.com/cards/73a5ecf1-2063-4cb3-a4ab-a0601b28256a', 'no fallback yet'),
('https://api.scryfall.com/cards/6dd1a7fc-5dbd-4ed2-9b02-9fd5c55bb629', 'no fallback yet'),
('https://api.scryfall.com/cards/7b0cb4d6-350b-4e66-b035-dac7b3ba77cc', 'no fallback yet'),
('https://api.scryfall.com/cards/fb01a161-49a1-407c-8410-6933bc8f7b6a', 'no fallback yet'),
('https://api.scryfall.com/cards/6f11029a-b24d-4248-834f-b852b56857f6', 'no fallback yet'),
('https://api.scryfall.com/cards/0dd73fb2-453f-40b9-8beb-dfa99e6a706e', 'no fallback yet'),
('https://api.scryfall.com/cards/d38c9891-36d1-4565-9c4a-1cd9dbf8c048', 'no fallback yet'),
('https://api.scryfall.com/cards/aaadceb3-8a57-45de-b3f1-aca94f20de18', 'no fallback yet'),
('https://api.scryfall.com/cards/3d654bec-4eb2-4df6-b71e-ce59a718f903', 'no fallback yet'),
('https://api.scryfall.com/cards/373131be-bd66-49f5-80fa-836ced03fedf', 'no fallback yet'),
('https://api.scryfall.com/cards/ca42da05-330d-4050-a580-dc00f6faff24', 'no fallback yet'),
('https://api.scryfall.com/cards/d954677c-2de6-440a-90d0-bab2e0c8b4af', 'no fallback yet'),
('https://api.scryfall.com/cards/862d38d1-e3d0-47e1-a535-ff445b1c55c6', 'no fallback yet'),
('https://api.scryfall.com/cards/af78f76e-30cd-4939-9577-5a4bd1f93e63', 'no fallback yet'),
('https://api.scryfall.com/cards/9b1c7f07-8d39-425b-8ae9-b3ab317cc0fe', 'no fallback yet'),
('https://api.scryfall.com/cards/099bc474-e656-4167-b105-3a3a36c0b23e', 'no fallback yet'),
('https://api.scryfall.com/cards/1b01d243-9f68-47c7-980b-1418e5f2f3e9', 'no fallback yet'),
('https://api.scryfall.com/cards/ea6bc7d5-e8f6-4103-920c-9f7ec5cd6c28', 'no fallback yet'),
('https://api.scryfall.com/cards/b6beac36-e12f-408d-a37b-a70e0e6c52d8', 'no fallback yet'),
('https://api.scryfall.com/cards/ac82422c-8ac8-4fbb-b9b9-d0aa23dded61', 'no fallback yet'),
('https://api.scryfall.com/cards/4cf3eb65-0f52-49c1-8243-14ce05de9f3b', 'no fallback yet'),
('https://api.scryfall.com/cards/d224940c-d87c-4317-9ca3-b704ef894a7b', 'no fallback yet'),
('https://api.scryfall.com/cards/bd43d44b-de27-4139-9cb8-b1f4c04fb87e', 'no fallback yet'),
('https://api.scryfall.com/cards/3bcabe2d-82d2-4c1b-8f28-21dc29c9dbf2', 'no fallback yet'),
('https://api.scryfall.com/cards/1d49637b-2255-4f16-8f24-140145966bfa', 'no fallback yet'),
('https://api.scryfall.com/cards/bae7d501-72a1-43c2-9f72-d768ac5e9320', 'no fallback yet'),
('https://api.scryfall.com/cards/ab830392-4d7e-4b45-93cf-35ed1e935228', 'no fallback yet'),
('https://api.scryfall.com/cards/01efd5af-ed6d-4132-8f33-37f6a9fa55d0', 'no fallback yet'),
('https://api.scryfall.com/cards/3e2160e9-14a0-4c75-b7b0-fb09051ddb32', 'no fallback yet'),
('https://api.scryfall.com/cards/5faba6c8-3463-47c1-ba01-09eb87fcb2d5', 'no fallback yet'),
('https://api.scryfall.com/cards/6fe72bd9-825e-4451-9314-826882f75c85', 'no fallback yet'),
('https://api.scryfall.com/cards/9b7ec5a3-3c40-4090-b9e3-11fb5b06fb8f', 'no fallback yet'),
('https://api.scryfall.com/cards/7892b516-0dce-491f-8b42-031d64397e26', 'no fallback yet'),
('https://api.scryfall.com/cards/909489a9-2678-4b6f-9d5e-2c04bb4cbd66', 'no fallback yet'),
('https://api.scryfall.com/cards/000ac9e5-3c95-4e87-9424-109e2eea6b45', 'no fallback yet'),
('https://api.scryfall.com/cards/b9169431-a5b1-42ea-bcf4-6c750d2a2dbb', 'no fallback yet'),
('https://api.scryfall.com/cards/31adbfd4-56aa-4137-aeba-8720233260be', 'no fallback yet'),
('https://api.scryfall.com/cards/875832f4-e541-4c87-8479-731e0eab2cc6', 'no fallback yet'),
('https://api.scryfall.com/cards/42352899-f2f2-4dea-863b-8d685e63b454', 'no fallback yet'),
('https://api.scryfall.com/cards/fe7f4393-38f9-43b5-873b-58246183b874', 'no fallback yet'),
('https://api.scryfall.com/cards/399f7531-e137-463b-bec3-e86756b6ed71', 'no fallback yet'),
('https://api.scryfall.com/cards/73fb4a34-c11e-45e9-a986-43c0a0ae5424', 'no fallback yet'),
('https://api.scryfall.com/cards/184a196e-8604-49d2-a66a-6f7c0eafd5de', 'no fallback yet');

INSERT INTO td_deck (deck_author, deck_name, deck_description, deck_format, deck_featured_card)
VALUES ((SELECT user_id FROM td_user WHERE username = 'cbprosser'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard'), (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'hatebear'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard'), (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'monoUDelver'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard'), (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'no.x.spells'), 'deck1', 'no description', (SELECT format_id FROM td_formats WHERE format='Standard'), (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1));

INSERT INTO td_collection (collection_author, collection_name, collection_description, collection_featured_card)
VALUES ((SELECT user_id FROM td_user WHERE username = 'cbprosser'), 'collection1', 'no description', (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'hatebear'), 'collection1', 'no description', (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'monoUDelver'), 'collection1', 'no description', (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1)),
	   ((SELECT user_id FROM td_user WHERE username = 'no.x.spells'), 'collection1', 'no description', (SELECT card_id FROM td_card ORDER BY RANDOM() LIMIT 1));

INSERT INTO td_deck_cards (deck_id, card_id, card_amount)
VALUES ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 3, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 5, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 7, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 9, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 11, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 13, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 15, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 17, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 19, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 21, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 23, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 25, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 27, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 29, 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 31, 4),
       ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (3*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (5*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (7*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (9*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (11*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (13*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (15*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (17*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (19*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (21*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (23*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (25*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (27*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (29*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (31*2), 4)
       ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (3+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (5+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (7+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (9+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (11+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (13+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (15+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (17+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (19+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (21+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (23+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (25+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (27+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (29+1), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (31+1), 4),
       ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((3+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((5+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((7+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((9+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((11+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((13+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((15+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((17+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((19+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((21+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((23+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((25+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((27+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((29+1)*2), 4),
	   ((SELECT deck_id FROM td_deck WHERE deck_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((31+1)*2), 4);

INSERT INTO td_collection_cards (collection_id, card_id, card_amount)
VALUES ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 3, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 5, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 7, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 9, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 11, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 13, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 15, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 17, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 19, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 21, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 23, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 25, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 27, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 29, 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'cbprosser')), 31, 4),
       ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (3*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (5*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (7*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (9*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (11*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (13*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (15*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (17*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (19*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (21*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (23*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (25*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (27*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (29*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'hatebear')), (31*2), 4)
       ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (3+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (5+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (7+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (9+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (11+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (13+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (15+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (17+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (19+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (21+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (23+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (25+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (27+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (29+1), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'monoUDelver')), (31+1), 4),
       ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((3+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((5+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((7+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((9+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((11+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((13+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((15+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((17+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((19+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((21+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((23+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((25+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((27+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((29+1)*2), 4),
	   ((SELECT collection_id FROM td_collection WHERE collection_author = (SELECT user_id FROM td_user WHERE username = 'no.x.spells')), ((31+1)*2), 4);